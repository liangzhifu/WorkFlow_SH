package com.success.alteration.order.service.impl;

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.service.DpcoiOrderService;
import com.success.alteration.order.constant.AlterationOrderEnum;
import com.success.alteration.order.dao.AlterationOrderDao;
import com.success.alteration.order.dao.AlterationOrderHistoryDao;
import com.success.alteration.order.domain.AlterationOrderHistory;
import com.success.alteration.order.domain.AlterationOrder;
import com.success.alteration.order.service.AlterationOrderService;
import com.success.four.order.dao.FourOrderAttrDao;
import com.success.four.order.dao.FourOrderAttrHistoryDao;
import com.success.four.order.dao.FourOrderDao;
import com.success.four.order.dao.FourOrderHistoryDao;
import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.order.dao.*;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.domain.KirikaeResume;
import com.success.kirikae.order.util.KirikaeOrderUtil;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.dao.KirikaeOrderProcedureDao;
import com.success.kirikae.procedure.dao.KirikaeProcedureDao;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.domain.KirikaeProcedure;
import com.success.kirikae.stand.dao.KirikaeStandCloseDao;
import com.success.kirikae.stand.domain.KirikaeStandClose;
import com.success.kirikae.wo.constant.KirikaeWoOrderEnum;
import com.success.kirikae.wo.dao.KirikaeWoOrderAttrDao;
import com.success.kirikae.wo.dao.KirikaeWoOrderDao;
import com.success.kirikae.wo.domain.KirikaeWoOrder;
import com.success.kirikae.wo.domain.KirikaeWoOrderAttr;
import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.domain.User;
import com.success.system.permission.constant.PermissionEnum;
import com.success.system.user.dao.SystemUser2Dao;
import com.success.system.user.domain.SystemUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("alterationOrderService")
public class AlterationOrderServiceImpl implements AlterationOrderService {

    @Resource(name = "alterationOrderDao")
    private AlterationOrderDao alterationOrderDao;

    @Resource(name = "fourOrderDao")
    private FourOrderDao fourOrderDao;

    @Resource(name = "fourOrderAttrDao")
    private FourOrderAttrDao fourOrderAttrDao;

    @Resource(name = "kirikaeOrderDao")
    private KirikaeOrderDao kirikaeOrderDao;

    @Resource(name = "kirikaeOrderChangeContentDao")
    private KirikaeOrderChangeContentDao kirikaeOrderChangeContentDao;

    @Resource(name = "kirikaeOrderPartsNumberDao")
    private KirikaeOrderPartsNumberDao kirikaeOrderPartsNumberDao;

    @Resource(name = "kirikaeResumeDao")
    private KirikaeResumeDao kirikaeResumeDao;

    @Resource(name = "kirikaeProcedureDao")
    private KirikaeProcedureDao kirikaeProcedureDao;

    @Resource(name = "kirikaeOrderProcedureDao")
    private KirikaeOrderProcedureDao kirikaeOrderProcedureDao;

    @Resource(name = "kirikaeWoOrderDao")
    private KirikaeWoOrderDao kirikaeWoOrderDao;

    @Resource(name = "systemUser2Dao")
    private SystemUser2Dao systemUserDao;

    @Resource(name = "timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name = "alterationOrderHistoryDao")
    private AlterationOrderHistoryDao alterationOrderHistoryDao;

    @Resource(name = "fourOrderHistoryDao")
    private FourOrderHistoryDao fourOrderHistoryDao;

    @Resource(name = "fourOrderAttrHistoryDao")
    private FourOrderAttrHistoryDao fourOrderAttrHistoryDao;

    @Resource(name = "kirikaeOrderHistoryDao")
    private KirikaeOrderHistoryDao kirikaeOrderHistoryDao;

    @Resource(name = "kirikaeOrderChangeContentHistoryDao")
    private KirikaeOrderChangeContentHistoryDao kirikaeOrderChangeContentHistoryDao;

    @Resource(name = "kirikaeOrderPartsNumberHistoryDao")
    private KirikaeOrderPartsNumberHistoryDao kirikaeOrderPartsNumberHistoryDao;

    @Resource(name = "kirikaeResumeHistoryDao")
    private KirikaeResumeHistoryDao kirikaeResumeHistoryDao;

    @Resource(name = "kirikaeWoOrderAttrDao")
    private KirikaeWoOrderAttrDao kirikaeWoOrderAttrDao;

    @Resource(name = "kirikaeStandCloseDao")
    private KirikaeStandCloseDao kirikaeStandCloseDao;

    @Resource(name = "dpcoiOrderService")
    private DpcoiOrderService dpcoiOrderService;

    @Override
    public AlterationOrder getAlterationOrder(AlterationOrder alterationOrder) throws Exception {
        alterationOrder = this.alterationOrderDao.selectAlterationOrder(alterationOrder);

        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(alterationOrder.getId());
        alterationOrder.setKirikaeOrder(kirikaeOrder);

        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(alterationOrder.getId());
        alterationOrder.setKirikaeOrderChangeContentList(kirikaeOrderChangeContentList);

        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = this.kirikaeOrderPartsNumberDao.selectKirikaeOrderPartsNumberListByOrderId(alterationOrder.getId());
        alterationOrder.setKirikaeOrderPartsNumberList(kirikaeOrderPartsNumberList);

        List<KirikaeResume> kirikaeResumeList = this.kirikaeResumeDao.selectKirikaeResumeListByOrderId(alterationOrder.getId());
        alterationOrder.setKirikaeResumeList(kirikaeResumeList);

        return alterationOrder;
    }

    @Override
    public void addAlterationOrder(AlterationOrder alterationOrder, User user) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        alterationOrder.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        alterationOrder.setOrderState(AlterationOrderEnum.OrderStateEnum.ORDER_STATE_PROCESS.getCode());
        alterationOrder.setCreateBy(user.getUserId());
        alterationOrder.setCreateTime(new Date());
        alterationOrder.setUpdateBy(user.getUserId());
        alterationOrder.setUpdateTime(new Date());
        this.alterationOrderDao.insertAlterationOrder(alterationOrder);

        KirikaeOrder kirikaeOrder = alterationOrder.getKirikaeOrder();
        //添加DPCOI
        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderState(1);
        dpcoiOrder.setDelFlag(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode().toString());
        dpcoiOrder.setCreateBy(user.getUserId());
        dpcoiOrder.setCreateDate(new Date());
        dpcoiOrder.setUpdateBy(user.getUserId());
        dpcoiOrder.setUpdateDate(new Date());
        dpcoiOrder.setDpcoiOrderType(4);
        dpcoiOrder.setDesignChangeNo(kirikaeOrder.getTkNo());
        dpcoiOrder.setVehicleName(kirikaeOrder.getVehicleName());
        Date designChangeTiming = kirikaeOrder.getDesignChangeTiming();
        if (designChangeTiming != null) {
            dpcoiOrder.setHopeCuttingDate(sdf.format(designChangeTiming));
        }
        this.dpcoiOrderService.addDpcoiOrder(dpcoiOrder);

        kirikaeOrder.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
        kirikaeOrder.setOrderId(alterationOrder.getId());
        this.kirikaeOrderDao.insertSelective(kirikaeOrder);

        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = alterationOrder.getKirikaeOrderChangeContentList();
        if(kirikaeOrderChangeContentList != null && kirikaeOrderChangeContentList.size() > 0){
            for(KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList){
                kirikaeOrderChangeContent.setOrderId(alterationOrder.getId());
                kirikaeOrderChangeContent.setCreateBy(user.getUserId());
                kirikaeOrderChangeContent.setCreateTime(new Date());
                kirikaeOrderChangeContent.setUpdateBy(user.getUserId());
                kirikaeOrderChangeContent.setUpdateTime(new Date());
                kirikaeOrderChangeContent.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                this.kirikaeOrderChangeContentDao.insertSelective(kirikaeOrderChangeContent);
            }
        }

        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = alterationOrder.getKirikaeOrderPartsNumberList();
        if(kirikaeOrderPartsNumberList != null && kirikaeOrderPartsNumberList.size() > 0){
            for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : kirikaeOrderPartsNumberList){
                kirikaeOrderPartsNumber.setOrderId(alterationOrder.getId());
                kirikaeOrderPartsNumber.setCreateBy(user.getUserId());
                kirikaeOrderPartsNumber.setCreateTime(new Date());
                kirikaeOrderPartsNumber.setUpdateBy(user.getUserId());
                kirikaeOrderPartsNumber.setUpdateTime(new Date());
                kirikaeOrderPartsNumber.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                this.kirikaeOrderPartsNumberDao.insertSelective(kirikaeOrderPartsNumber);
            }
        }

        List<KirikaeResume> kirikaeResumeList = alterationOrder.getKirikaeResumeList();
        if(kirikaeResumeList != null && kirikaeResumeList.size() > 0){
            for(KirikaeResume kirikaeResume : kirikaeResumeList){
                kirikaeResume.setOrderId(alterationOrder.getId());
                kirikaeResume.setCreateBy(user.getUserId());
                kirikaeResume.setCreateTime(new Date());
                kirikaeResume.setUpdateBy(user.getUserId());
                kirikaeResume.setUpdateTime(new Date());
                kirikaeResume.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                this.kirikaeResumeDao.insertSelective(kirikaeResume);
            }
        }

        List<KirikaeProcedure> kirikaeProcedureList = this.kirikaeProcedureDao.selectKirikaeProcedureList(1);
        if(kirikaeProcedureList != null && kirikaeProcedureList.size() > 0){
            for(KirikaeProcedure kirikaeProcedure : kirikaeProcedureList){
                KirikaeOrderProcedure kirikaeOrderProcedure = new KirikaeOrderProcedure();
                kirikaeOrderProcedure.setOrderId(alterationOrder.getId());
                kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_ONE.getCode());
                kirikaeOrderProcedure.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                kirikaeOrderProcedure.setProcedureSeq(kirikaeProcedure.getProcedureSeq());
                kirikaeOrderProcedure.setProcedureCode(kirikaeProcedure.getProcedureCode());
                kirikaeOrderProcedure.setProcedureType(kirikaeProcedure.getProcedureType());
                kirikaeOrderProcedure.setCreateTime(new Date());
                this.kirikaeOrderProcedureDao.insertSelective(kirikaeOrderProcedure);
            }
        }

    }

    @Override
    public void editAlterationOrder(AlterationOrder alterationOrder, User user) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        alterationOrder.setUpdateBy(user.getUserId());
        alterationOrder.setUpdateTime(new Date());
        this.alterationOrderDao.updateByPrimaryKeySelective(alterationOrder);

        KirikaeOrder kirikaeOrder = alterationOrder.getKirikaeOrder();
        kirikaeOrder.setOrderId(alterationOrder.getId());
        this.kirikaeOrderDao.updateByPrimaryKey(kirikaeOrder);

        KirikaeOrder oldKirikaeOrder = this.kirikaeOrderDao.selectByPrimaryKey(kirikaeOrder);
        Integer depcoiOrderId = oldKirikaeOrder.getDpcoiOrderId();
        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(depcoiOrderId);
        dpcoiOrder = this.dpcoiOrderService.selectBySelf(dpcoiOrder);
        dpcoiOrder.setUpdateBy(user.getUserId());
        dpcoiOrder.setUpdateDate(new Date());
        dpcoiOrder.setVehicleName(kirikaeOrder.getVehicleName());
        Date designChangeTiming = kirikaeOrder.getDesignChangeTiming();
        if (designChangeTiming != null) {
            dpcoiOrder.setHopeCuttingDate(sdf.format(designChangeTiming));
        }
        this.dpcoiOrderService.updateDpcoiOrder(dpcoiOrder);

        List<KirikaeOrderChangeContent> oldKirikaeOrderChangeContentList = this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(alterationOrder.getId());
        if(oldKirikaeOrderChangeContentList != null && oldKirikaeOrderChangeContentList.size() > 0){
            for(KirikaeOrderChangeContent kirikaeOrderChangeContent : oldKirikaeOrderChangeContentList){
                kirikaeOrderChangeContent.setUpdateBy(user.getUserId());
                kirikaeOrderChangeContent.setUpdateTime(new Date());
                kirikaeOrderChangeContent.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeOrderChangeContentDao.updateByPrimaryKeySelective(kirikaeOrderChangeContent);
            }
        }
        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = alterationOrder.getKirikaeOrderChangeContentList();
        if(kirikaeOrderChangeContentList != null && kirikaeOrderChangeContentList.size() > 0){
            for(KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList){
                kirikaeOrderChangeContent.setOrderId(alterationOrder.getId());
                kirikaeOrderChangeContent.setCreateBy(user.getUserId());
                kirikaeOrderChangeContent.setCreateTime(new Date());
                kirikaeOrderChangeContent.setUpdateBy(user.getUserId());
                kirikaeOrderChangeContent.setUpdateTime(new Date());
                kirikaeOrderChangeContent.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                this.kirikaeOrderChangeContentDao.insertSelective(kirikaeOrderChangeContent);
            }
        }

        List<KirikaeOrderPartsNumber> oldKirikaeOrderPartsNumberList = this.kirikaeOrderPartsNumberDao.selectKirikaeOrderPartsNumberListByOrderId(alterationOrder.getId());
        if(oldKirikaeOrderPartsNumberList != null && oldKirikaeOrderPartsNumberList.size() > 0){
            for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : oldKirikaeOrderPartsNumberList){
                kirikaeOrderPartsNumber.setUpdateBy(user.getUserId());
                kirikaeOrderPartsNumber.setUpdateTime(new Date());
                kirikaeOrderPartsNumber.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeOrderPartsNumberDao.updateByPrimaryKeySelective(kirikaeOrderPartsNumber);
            }
        }
        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = alterationOrder.getKirikaeOrderPartsNumberList();
        if(kirikaeOrderPartsNumberList != null && kirikaeOrderPartsNumberList.size() > 0){
            for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : kirikaeOrderPartsNumberList){
                kirikaeOrderPartsNumber.setOrderId(alterationOrder.getId());
                kirikaeOrderPartsNumber.setCreateBy(user.getUserId());
                kirikaeOrderPartsNumber.setCreateTime(new Date());
                kirikaeOrderPartsNumber.setUpdateBy(user.getUserId());
                kirikaeOrderPartsNumber.setUpdateTime(new Date());
                kirikaeOrderPartsNumber.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                this.kirikaeOrderPartsNumberDao.insertSelective(kirikaeOrderPartsNumber);
            }
        }

        List<KirikaeResume> oldKirikaeResumeList = this.kirikaeResumeDao.selectKirikaeResumeListByOrderId(alterationOrder.getId());
        if(oldKirikaeResumeList != null && oldKirikaeResumeList.size() > 0){
            for(KirikaeResume kirikaeResume : oldKirikaeResumeList){
                kirikaeResume.setUpdateBy(user.getUserId());
                kirikaeResume.setUpdateTime(new Date());
                kirikaeResume.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeResumeDao.updateByPrimaryKeySelective(kirikaeResume);
            }
        }
        List<KirikaeResume> kirikaeResumeList = alterationOrder.getKirikaeResumeList();
        if(kirikaeResumeList != null && kirikaeResumeList.size() > 0){
            for(KirikaeResume kirikaeResume : kirikaeResumeList){
                kirikaeResume.setOrderId(alterationOrder.getId());
                kirikaeResume.setCreateBy(user.getUserId());
                kirikaeResume.setCreateTime(new Date());
                kirikaeResume.setUpdateBy(user.getUserId());
                kirikaeResume.setUpdateTime(new Date());
                kirikaeResume.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
                this.kirikaeResumeDao.insertSelective(kirikaeResume);
            }
        }

    }

    @Override
    public void editAlterationOrderToVoid(Integer orderId, User user) throws Exception {
        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(orderId);

        AlterationOrder alterationOrder = new AlterationOrder();
        alterationOrder.setId(orderId);
        alterationOrder.setUpdateBy(user.getUserId());
        alterationOrder.setUpdateTime(new Date());
        alterationOrder.setOrderState(AlterationOrderEnum.OrderStateEnum.ORDER_STATE_VOID.getCode());
        this.alterationOrderDao.updateByPrimaryKeySelective(alterationOrder);

        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        if(kirikaeOrderProcedureList != null && kirikaeOrderProcedureList.size() > 0){
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_FOUR.getCode());
                this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            }
        }

        List<KirikaeWoOrder> kirikaeWoOrderList = this.kirikaeWoOrderDao.selectKirikaeWoOrderListByOrderId(orderId);
        if(kirikaeWoOrderList != null && kirikaeWoOrderList.size() > 0){
            for(KirikaeWoOrder kirikaeWoOrder : kirikaeWoOrderList){
                kirikaeWoOrder.setWoOrderState(KirikaeWoOrderEnum.WoOrderStateEnum.ORDER_STATE_FIVE.getCode());
                this.kirikaeWoOrderDao.updateByPrimaryKeySelective(kirikaeWoOrder);
            }
        }

        List<SystemUser> systemUserList = this.systemUserDao.selectSystemUserListByPermissionId(PermissionEnum.ConstantEnum.PERMISSION_FOUR.getCode());
        List<TimeTask> timeTaskList = KirikaeOrderUtil.generateMail(kirikaeOrder, systemUserList, "切替单作废");
        if(timeTaskList != null && timeTaskList.size() > 0){
            for(TimeTask timeTask : timeTaskList){
                this.timeTaskDao.insertTimeTask(timeTask);
            }
        }
    }

    @Override
    public void editCopyAlterationOrder(Integer orderId, User user) throws Exception {
        AlterationOrder alterationOrder = new AlterationOrder();
        alterationOrder.setId(orderId);
        alterationOrder = this.alterationOrderDao.selectAlterationOrder(alterationOrder);

        Integer orderVersion = this.alterationOrderHistoryDao.selectAlterationOrderVersion(orderId);
        AlterationOrderHistory alterationOrderHistory = new AlterationOrderHistory();
        alterationOrderHistory.setOrderId(orderId);
        alterationOrderHistory.setOrderVersion(orderVersion + 1);
        alterationOrderHistory.setOrderState(alterationOrder.getOrderState());
        alterationOrderHistory.setOrderChannel(alterationOrder.getOrderChannel());
        alterationOrderHistory.setInvalidateText(alterationOrder.getInvalidateText());
        alterationOrderHistory.setCreateTime(alterationOrder.getCreateTime());
        alterationOrderHistory.setCreateBy(alterationOrder.getCreateBy());
        alterationOrderHistory.setDeleteState(alterationOrder.getDeleteState());
        alterationOrderHistory.setUpdateTime(new Date());
        alterationOrderHistory.setUpdateBy(user.getUserId());
        alterationOrderHistory.setGenerateFour(alterationOrder.getGenerateFour());
        this.alterationOrderHistoryDao.insertAlterationOrder(alterationOrderHistory);

        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(orderId);
        kirikaeOrder.setId(null);
        kirikaeOrder.setOrderId(alterationOrderHistory.getId());
        this.kirikaeOrderHistoryDao.insertSelective(kirikaeOrder);

        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(orderId);
        if(kirikaeOrderChangeContentList != null && kirikaeOrderChangeContentList.size() > 0){
            for(KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList){
                kirikaeOrderChangeContent.setId(null);
                kirikaeOrderChangeContent.setOrderId(alterationOrderHistory.getId());
                this.kirikaeOrderChangeContentHistoryDao.insertSelective(kirikaeOrderChangeContent);
            }
        }

        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = this.kirikaeOrderPartsNumberDao.selectKirikaeOrderPartsNumberListByOrderId(orderId);
        if(kirikaeOrderChangeContentList != null && kirikaeOrderChangeContentList.size() > 0){
            for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : kirikaeOrderPartsNumberList){
                kirikaeOrderPartsNumber.setOrderId(alterationOrderHistory.getId());
                kirikaeOrderPartsNumber.setId(null);
                this.kirikaeOrderPartsNumberHistoryDao.insertSelective(kirikaeOrderPartsNumber);
            }
        }

        List<KirikaeResume> kirikaeResumeList = this.kirikaeResumeDao.selectKirikaeResumeListByOrderId(orderId);
        if(kirikaeResumeList != null && kirikaeResumeList.size() > 0){
            for(KirikaeResume kirikaeResume : kirikaeResumeList){
                kirikaeResume.setId(null);
                kirikaeResume.setOrderId(alterationOrderHistory.getId());
                this.kirikaeResumeHistoryDao.insertSelective(kirikaeResume);
            }
        }

        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        if(kirikaeOrderProcedureList != null && kirikaeOrderProcedureList.size() > 0){
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_ONE.getCode());
                this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            }
        }

        List<KirikaeWoOrder> kirikaeWoOrderList = this.kirikaeWoOrderDao.selectKirikaeWoOrderListByOrderId(orderId);
        if(kirikaeWoOrderList != null && kirikaeWoOrderList.size() > 0){
            for(KirikaeWoOrder kirikaeWoOrder : kirikaeWoOrderList){
                kirikaeWoOrder.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeWoOrderDao.updateByPrimaryKeySelective(kirikaeWoOrder);
            }
        }
    }

    @Override
    public void editInitAlterationOrder(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        if(kirikaeOrderProcedureList != null){
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                kirikaeOrderProcedure.setProcedureState(ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_ONE.getCode());
                this.kirikaeOrderProcedureDao.updateByPrimaryKeySelective(kirikaeOrderProcedure);
            }
        }
        List<KirikaeWoOrder> kirikaeWoOrderList = this.kirikaeWoOrderDao.selectKirikaeWoOrderListByOrderId(orderId);
        if(kirikaeWoOrderList != null){
            for(KirikaeWoOrder kirikaeWoOrder : kirikaeWoOrderList){
                kirikaeWoOrder.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeWoOrderDao.updateByPrimaryKeySelective(kirikaeWoOrder);
            }
        }
        List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = this.kirikaeWoOrderAttrDao.selectKirikaeWoOrderAttrListByOrderId(orderId);
        if(kirikaeWoOrderAttrList != null){
            for(KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
                kirikaeWoOrderAttr.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
            }
        }
        List<KirikaeStandClose> kirikaeStandCloseList = this.kirikaeStandCloseDao.selectKirikaeStandCloseListByOrderId(orderId);
        if(kirikaeStandCloseList != null){
            for(KirikaeStandClose kirikaeStandClose : kirikaeStandCloseList){
                kirikaeStandClose.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
                this.kirikaeStandCloseDao.updateByPrimaryKeySelective(kirikaeStandClose);
            }
        }
    }

    @Override
    public void updateAlterationOrder(AlterationOrder alterationOrder) throws Exception {
        this.alterationOrderDao.updateByPrimaryKeySelective(alterationOrder);
    }

}
