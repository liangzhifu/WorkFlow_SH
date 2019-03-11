package com.success.kirikae.procedure.service.impl;

import com.dpcoi.holiday.dao.HolidayDao;
import com.success.alteration.order.constant.AlterationOrderEnum;
import com.success.alteration.order.dao.AlterationOrderDao;
import com.success.alteration.order.domain.AlterationOrder;
import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.order.constant.KirikaeOrderEnum;
import com.success.kirikae.order.dao.KirikaeOrderDao;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.util.KirikaeOrderUtil;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.dao.KirikaeOrderProcedureDao;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.kirikae.wo.constant.KirikaeWoOrderEnum;
import com.success.kirikae.wo.dao.KirikaeWoOrderAttrDao;
import com.success.kirikae.wo.dao.KirikaeWoOrderDao;
import com.success.kirikae.wo.domain.KirikaeWoOrder;
import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.domain.User;
import com.success.system.org.constant.SystemOrgEnum;
import com.success.system.org.dao.SystemOrg2Dao;
import com.success.system.org.domain.SystemOrg;
import com.success.system.org.query.SystemOrgQuery;
import com.success.system.permission.constant.PermissionEnum;
import com.success.system.user.dao.SystemUser2Dao;
import com.success.system.user.domain.SystemUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lzf
 **/
@Service("kirikaeOrderProcedureService")
public class KirikaeOrderProcedureServiceImpl implements KirikaeOrderProcedureService {

    @Resource(name = "kirikaeOrderProcedureDao")
    private KirikaeOrderProcedureDao kirikaeOrderProcedureDao;

    @Resource(name = "systemOrg2Dao")
    private SystemOrg2Dao systemOrgDao;

    @Resource(name = "kirikaeWoOrderDao")
    private KirikaeWoOrderDao kirikaeWoOrderDao;

    @Resource(name = "holidayDao")
    private HolidayDao holidayDao;

    @Resource(name = "systemUser2Dao")
    private SystemUser2Dao systemUserDao;

    @Resource(name = "timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name = "kirikaeOrderDao")
    private KirikaeOrderDao kirikaeOrderDao;

    @Resource(name = "alterationOrderDao")
    private AlterationOrderDao alterationOrderDao;

    @Resource(name = "kirikaeWoOrderAttrDao")
    private KirikaeWoOrderAttrDao kirikaeWoOrderAttrDao;

    @Override
    public void editStartNextProcedure(Integer orderId, Integer procedureSeq, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        Integer minProcedureSeq = this.getMinProcedureSeq(kirikaeOrderProcedureList, procedureSeq);
        if(minProcedureSeq.intValue() == 999){
            AlterationOrder alterationOrder = new AlterationOrder();
            alterationOrder.setId(orderId);
            alterationOrder.setOrderState(AlterationOrderEnum.OrderStateEnum.ORDER_STATE_COMPLETE.getCode());
            this.alterationOrderDao.updateByPrimaryKeySelective(alterationOrder);
        }else {
            //下一个流程
            KirikaeOrderProcedure nextKirikaeOrderProcedure = this.getNextKirikaeOrderProcedure(kirikaeOrderProcedureList, minProcedureSeq);
            Integer procedureCode = nextKirikaeOrderProcedure.getProcedureCode();
            procedureSeq = nextKirikaeOrderProcedure.getProcedureSeq();
            if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_CHECKED.getCode().intValue() == procedureCode.intValue()){
                //流程（指示书--确认）
                //没有确认人员，跳过流程执行下一步
                List<SystemUser> systemUserList = this.systemUserDao.selectSystemUserListByPermissionId(PermissionEnum.ConstantEnum.PERMISSION_NINE.getCode());
                if (systemUserList == null || systemUserList.size() == 0) {
                    KirikaeOrderProcedure orderProcedure = new KirikaeOrderProcedure();
                    orderProcedure.setId(nextKirikaeOrderProcedure.getId());
                    orderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_THREE.getCode());
                    orderProcedure.setProcedureTime(new Date());
                    this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(orderProcedure);
                    minProcedureSeq = this.getMinProcedureSeq(kirikaeOrderProcedureList, procedureSeq);
                    nextKirikaeOrderProcedure = this.getNextKirikaeOrderProcedure(kirikaeOrderProcedureList, minProcedureSeq);
                }
            }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CHECKED.getCode().intValue() == procedureCode.intValue()){
                //流程（确认书--确认）
                //没有确认人员，跳过流程执行下一步
                List<SystemUser> systemUserList = this.systemUserDao.selectSystemUserListByPermissionId(PermissionEnum.ConstantEnum.PERMISSION_TWELVE.getCode());
                if (systemUserList == null || systemUserList.size() == 0) {
                    KirikaeOrderProcedure orderProcedure = new KirikaeOrderProcedure();
                    orderProcedure.setId(nextKirikaeOrderProcedure.getId());
                    orderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_THREE.getCode());
                    orderProcedure.setProcedureTime(new Date());
                    this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(orderProcedure);
                    minProcedureSeq = this.getMinProcedureSeq(kirikaeOrderProcedureList, procedureSeq);
                    nextKirikaeOrderProcedure = this.getNextKirikaeOrderProcedure(kirikaeOrderProcedureList, minProcedureSeq);
                }
            }
            this.editStartKirikaeOrderProcedure(nextKirikaeOrderProcedure, user);
        }
    }

    @Override
    public void editStartKirikaeOrderProcedure(KirikaeOrderProcedure kirikaeOrderProcedure, User user) throws Exception {
        kirikaeOrderProcedure.setStartTime(new Date());
        Integer procedureCode = kirikaeOrderProcedure.getProcedureCode();
        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(kirikaeOrderProcedure.getOrderId());
        if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_SWITCH_DATE.getCode().intValue() == procedureCode.intValue()){
            Integer kirikaeOrderType = kirikaeOrder.getKirikaeOrderType();
            if(kirikaeOrderType.intValue() == KirikaeOrderEnum.KirikaeOrderTypeEnum.ORDER_TYPE_TWO.getCode().intValue()){
                kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
                this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
                //邮件通知
                List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_FOURTEEN.getCode());
                List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "确认切替日期");
                this.editSendEmail(timeTaskList);
            }else {
                kirikaeOrderProcedure.setProcedureTime(new Date());
                kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_THREE.getCode());
                this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
                this.editStartNextProcedure(kirikaeOrderProcedure.getOrderId(), kirikaeOrderProcedure.getProcedureSeq(), user);
            }
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CONTENT.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
            systemOrgQuery.setOrgType(SystemOrgEnum.OrgTypeEnum.ORG_TYPE_THREE.getCode());
            List<SystemOrg> systemOrgList = this.systemOrgDao.selectSystemOrgList(systemOrgQuery);
            if(systemOrgList != null && systemOrgList.size() > 0){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                List<Date> dateList = this.holidayDao.selectHolidayListFromBeginDate(formatter.format(new Date()));
                List<Calendar> calendarList = new LinkedList<Calendar>();
                if(dateList != null && dateList.size() > 0){
                    for(Date date : dateList){
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        calendarList.add(cal);
                    }
                }
                Date date = this.calculationWorkingDay(new Date(), 3, calendarList);
                for(SystemOrg systemOrg : systemOrgList){
                    KirikaeWoOrder kirikaeWoOrder = new KirikaeWoOrder();
                    kirikaeWoOrder.setOrderId(kirikaeOrderProcedure.getOrderId());
                    kirikaeWoOrder.setParentOrgId(systemOrg.getParentId());
                    kirikaeWoOrder.setParentOrgName(systemOrg.getParentOrgName());
                    kirikaeWoOrder.setOrgId(systemOrg.getId());
                    kirikaeWoOrder.setOrgName(systemOrg.getOrgName());
                    kirikaeWoOrder.setWoOrderState(KirikaeWoOrderEnum.WoOrderStateEnum.ORDER_STATE_TWO.getCode());
                    kirikaeWoOrder.setCreateTime(new Date());
                    kirikaeWoOrder.setStartTime(new Date());
                    kirikaeWoOrder.setSendMailTime(date);
                    kirikaeWoOrder.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                    this.kirikaeWoOrderDao.insertSelective(kirikaeWoOrder);
                }
            }
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_FOUR.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "设变确认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_REVIEW.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            AlterationOrder alterationOrder = new AlterationOrder();
            alterationOrder.setId(kirikaeOrderProcedure.getOrderId());
            alterationOrder = this.alterationOrderDao.selectAlterationOrder(alterationOrder);
            SystemUser systemUser = new SystemUser();
            systemUser.setId(alterationOrder.getCreateBy());
            systemUser = this.systemUserDao.selectSystemUser(systemUser);
            List<SystemUser> systemUserList = new LinkedList<SystemUser>();
            systemUserList.add(systemUser);
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "评审");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_FILE_UPLOAD.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.systemUserDao.selectPreparedSystemUserListByOrderId(kirikaeOrderProcedure.getOrderId());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "文件上传");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_USER.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            AlterationOrder alterationOrder = new AlterationOrder();
            alterationOrder.setId(kirikaeOrderProcedure.getOrderId());
            alterationOrder = this.alterationOrderDao.selectAlterationOrder(alterationOrder);
            SystemUser systemUser = new SystemUser();
            systemUser.setId(alterationOrder.getCreateBy());
            systemUser = this.systemUserDao.selectSystemUser(systemUser);
            List<SystemUser> systemUserList = new LinkedList<SystemUser>();
            systemUserList.add(systemUser);
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "立合人员填写");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_RESULT_VALID.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            /*List<SystemUser> systemUserList = this.systemUserDao.selectPreparedSystemUserListByOrderId(kirikaeOrderProcedure.getOrderId());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "立合结果验证");
            this.editSendEmail(timeTaskList);*/
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_CHECKED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_SIX.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "立合确认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_APPROVED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_SEVEN.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "立合承认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_PREPARED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_EIGHT.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "《手配书》指示书内容填写");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_CHECKED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_NINE.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "《手配书》指示书确认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_APPROVED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_TEN.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "《手配书》指示书承认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_PREPARED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_ELEVEN.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "《手配书》确认书内容填写");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CHECKED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_TWELVE.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "《手配书》确认书确认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_APPROVED.getCode().intValue() == procedureCode.intValue()){
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            //邮件通知
            List<SystemUser> systemUserList = this.listSystemUserByPermission(PermissionEnum.ConstantEnum.PERMISSION_THIRTEEN.getCode());
            List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "《手配书》确认书承认");
            this.editSendEmail(timeTaskList);
        }else if(ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_RESULT.getCode().intValue() == procedureCode.intValue()){
            //判断是否需要建立4M变化单
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
        }else {
            kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode());
            this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
        }
    }

    @Override
    public void editCompleteKirikaeOrderProcedure(KirikaeOrderProcedure kirikaeOrderProcedure, User user) throws Exception {
        kirikaeOrderProcedure.setProcedureBy(user.getUserId());
        kirikaeOrderProcedure.setProcedureTime(new Date());
        kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_THREE.getCode());
        this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
        this.editStartNextProcedure(kirikaeOrderProcedure.getOrderId(), kirikaeOrderProcedure.getProcedureSeq(), user);
    }

    @Override
    public void editStartFirstKirikaeOrderProcedure(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        Integer minProcedureSeq = 999;
        for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureSeq().intValue() < minProcedureSeq.intValue()){
                minProcedureSeq = kirikaeOrderProcedure.getProcedureSeq();
            }
        }
        for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureSeq().intValue() == minProcedureSeq.intValue()){
                this.editStartKirikaeOrderProcedure(kirikaeOrderProcedure, user);
            }
        }
    }

    @Override
    public List<KirikaeOrderProcedure> listKirikaeOrderProcedureByOrderId(Integer orderId) throws Exception {
        return this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
    }

    @Override
    public void editWoOrderConfirm(Integer orderId, User user) throws Exception {
        Integer num = this.kirikaeWoOrderAttrDao.selectWoOrderAttrNoConfirmCount(orderId);
        if(num.intValue() == 0){
            List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CONTENT.getCode().intValue()){
                    this.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                    break;
                }
            }
        }
    }

    @Override
    public void editWoOrderAttrStandClose(Integer orderId, User user) throws Exception {
        Integer num = this.kirikaeWoOrderAttrDao.selectWoOrderAttrNoStandCloseCount(orderId);
        if(num.intValue() == 0){
            List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_RESULT.getCode().intValue()){
                    this.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                    break;
                }
            }
        }
    }

    @Override
    public void editWoOrderAttrUpload(Integer orderId, User user) throws Exception {
        Integer num = this.kirikaeWoOrderAttrDao.selectWoOrderAttrNoUploadCount(orderId);
        if(num.intValue() == 0){
            List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_FILE_UPLOAD.getCode().intValue()){
                    this.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                    break;
                }
            }
        }
    }

    @Override
    public void editRefuseOrderProcedure(Integer orderId) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        if (kirikaeOrderProcedureList != null){
            for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                Integer procedureCode = kirikaeOrderProcedure.getProcedureCode();
                if (procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_FILE_UPLOAD.getCode().intValue()) {
                    kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_CHILD.getCode());
                    this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
                } else if (procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_RESULT.getCode().intValue()
                    || procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_RESULT_VALID.getCode().intValue()
                    || procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_CHECKED.getCode().intValue()){
                    kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_ONE.getCode());
                    this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
                }
            }
        }
    }

    /**
     * 计算工作日
     * @param startDay 开始日期
     * @param IntervalDays 间隔天数
     */
    private Date calculationWorkingDay(Date startDay, Integer IntervalDays, List<Calendar> holidayList){
        Calendar src = Calendar.getInstance();
        src.setTime(startDay);

        boolean holidayFlag;
        for (int i = 0; i < IntervalDays; i++) {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag =checkHoliday(src, holidayList);
            if(holidayFlag) {
                i--;
            }
        }

        return src.getTime();
    }

    /**
     * 校验指定的日期是否在节日列表中
     * 具体节日包含哪些,可以在HolidayMap中修改
     * @param src 要检验的日期
     */
    private boolean checkHoliday(Calendar src, List<Calendar> holidayList){
        boolean result = false;

        if (holidayList == null) {
            return false;
        }

        for (Calendar c : holidayList) {
            if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH)
                    && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)
                    && src.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * 获取邮件发送人员列表
     * @param permissionId 权限ID
     * @return 返回结果
     */
    private List<SystemUser> listSystemUserByPermission(Integer permissionId){
        List<SystemUser> systemUserList = this.systemUserDao.selectSystemUserListByPermissionId(permissionId);
        if(systemUserList != null && systemUserList.size() > 0){
            List<Integer> userIdList = new LinkedList<Integer>();
            for(SystemUser systemUser : systemUserList){
                userIdList.add(systemUser.getId());
            }
            systemUserList = this.systemUserDao.selectFiterSystemUserListByPermissionId(userIdList, PermissionEnum.ConstantEnum.PERMISSION_FOUR.getCode());
        }
        return systemUserList;
    }

    /**
     * 发送邮件
     * @param timeTaskList 邮件列表
     */
    private void editSendEmail(List<TimeTask> timeTaskList){
        if(timeTaskList != null && timeTaskList.size() > 0){
            for(TimeTask timeTask : timeTaskList){
                this.timeTaskDao.insertTimeTask(timeTask);
            }
        }
    }

    /**
     * 获取下一流程序号
     * @param kirikaeOrderProcedureList 流程列表
     * @param procedureSeq 当前流程序号
     * @return 返回结果
     */
    private Integer getMinProcedureSeq(List<KirikaeOrderProcedure> kirikaeOrderProcedureList, Integer procedureSeq){
        Integer minProcedureSeq = 999;
        for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureSeq() > procedureSeq
                    && kirikaeOrderProcedure.getProcedureSeq() < minProcedureSeq
                    && (kirikaeOrderProcedure.getProcedureState().intValue() == ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_ONE.getCode())){
                minProcedureSeq = kirikaeOrderProcedure.getProcedureSeq();
            }
        }
        return minProcedureSeq;
    }

    /**
     * 根据流程序号获取流程
     * @param kirikaeOrderProcedureList 流程列表
     * @param minProcedureSeq 流程序号
     * @return 返回结果
     */
    private KirikaeOrderProcedure getNextKirikaeOrderProcedure(List<KirikaeOrderProcedure> kirikaeOrderProcedureList, Integer minProcedureSeq){
        KirikaeOrderProcedure nextKirikaeOrderProcedure = null;
        for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureSeq().intValue() == minProcedureSeq.intValue()){
                nextKirikaeOrderProcedure = kirikaeOrderProcedure;
            }
        }
        return nextKirikaeOrderProcedure;
    }

}
