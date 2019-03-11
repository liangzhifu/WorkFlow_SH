package com.success.kirikae.order.service.impl;

import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.order.domain.DpcoiOrder;
import com.success.kirikae.order.dao.KirikaeOrderDao;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.query.KirikaeOrderQuery;
import com.success.kirikae.order.service.KirikaeOrderChangeContentService;
import com.success.kirikae.order.service.KirikaeOrderPartsNumberService;
import com.success.kirikae.order.service.KirikaeOrderService;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.dao.KirikaeOrderProcedureDao;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("kirikaeOrderService")
public class KirikaeOrderServiceImpl implements KirikaeOrderService {

    @Resource(name = "kirikaeOrderDao")
    private KirikaeOrderDao kirikaeOrderDao;

    @Resource(name = "kirikaeOrderProcedureDao")
    private KirikaeOrderProcedureDao kirikaeOrderProcedureDao;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    @Resource(name = "dpcoiOrderDao")
    private DpcoiOrderDao dpcoiOrderDao;

    @Override
    public void addKirikaeOrder(KirikaeOrder kirikaeOrder, User user) throws Exception {
        Integer num = this.kirikaeOrderDao.insertSelective(kirikaeOrder);
        if(num == 0){
            throw new Exception("新增切替变更单异常！");
        }

    }

    @Override
    public void editKirikaeOrder(KirikaeOrder kirikaeOrder, User user) throws Exception {
        this.kirikaeOrderDao.updateByPrimaryKeySelective(kirikaeOrder);
    }

    @Override
    public KirikaeOrder getKirikaeOrder(KirikaeOrder kirikaeOrder) throws Exception {
        return this.kirikaeOrderDao.selectByPrimaryKey(kirikaeOrder);
    }

    @Override
    public KirikaeOrder getKirikaeOrderByAlterationOrderId(Integer orderId) throws Exception {
        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(orderId);
        return kirikaeOrder;
    }

    @Override
    public List<Map<String, Object>> listKirikaeOrderPage(KirikaeOrderQuery kirikaeOrderQuery) throws Exception {
        return this.kirikaeOrderDao.selectKirikaeOrderPageList(kirikaeOrderQuery);
    }

    @Override
    public List<Map<String, Object>> listKirikaeOrderScreenShow() throws Exception {
        return this.kirikaeOrderDao.selectKirikaeOrderScreenShowList();
    }

    @Override
    public Integer countKirikaeOrder(KirikaeOrderQuery kirikaeOrderQuery) throws Exception {
        return this.kirikaeOrderDao.selectKirikaeOrderCount(kirikaeOrderQuery);
    }

    @Override
    public Boolean validOrderRepeat(Integer orderId) throws Exception {
        boolean flag = false;
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureListByOrderId(orderId);
        if(kirikaeOrderProcedureList != null){
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                Integer procedureState = kirikaeOrderProcedure.getProcedureState();
                if(procedureState.intValue() == ProcedureEnum.ProcedureStateEnum.PROCEDURE_STATE_TWO.getCode().intValue()){
                    Integer procedureCode = kirikaeOrderProcedure.getProcedureCode();
                    if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_SWITCH_DATE.getCode().intValue()){
                        flag = true;
                    }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_SWITCH_DATE.getCode().intValue()){
                        flag = true;
                    }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CONTENT.getCode().intValue()){
                        flag = true;
                    }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_REVIEW.getCode().intValue()){
                        flag = true;
                    }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_FILE_UPLOAD.getCode().intValue()){
                        flag = true;
                    }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_USER.getCode().intValue()){
                        flag = true;
                    }
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public void editValidDesignChangeTiming(Integer orderId, String designChangeTiming, User user) throws Exception {
        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(orderId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        kirikaeOrder.setDesignChangeTiming(sdf.parse(designChangeTiming));
        this.kirikaeOrderDao.updateByPrimaryKeySelective(kirikaeOrder);

        Integer depcoiOrderId = kirikaeOrder.getDpcoiOrderId();
        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(depcoiOrderId);
        dpcoiOrder = this.dpcoiOrderDao.selectBySelf(dpcoiOrder);
        dpcoiOrder.setUpdateBy(user.getUserId());
        dpcoiOrder.setUpdateDate(new Date());
        dpcoiOrder.setVehicleName(kirikaeOrder.getVehicleName());
        dpcoiOrder.setHopeCuttingDate(designChangeTiming);
        this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);

        //确认切替日期，启动下一流程（选择确认项目）
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_SWITCH_DATE.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public void editGenerateFourOrderProcedure(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){

        }
    }
}
