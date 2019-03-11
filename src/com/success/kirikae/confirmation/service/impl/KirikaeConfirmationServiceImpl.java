package com.success.kirikae.confirmation.service.impl;

import com.success.kirikae.confirmation.dao.KirikaeConfirmationDao;
import com.success.kirikae.confirmation.domain.KirikaeConfirmation;
import com.success.kirikae.confirmation.service.KirikaeConfirmationService;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("kirikaeConfirmationService")
public class KirikaeConfirmationServiceImpl implements KirikaeConfirmationService {

    @Resource(name = "kirikaeConfirmationDao")
    private KirikaeConfirmationDao kirikaeConfirmationDao;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    @Override
    public void addKirikaeConfirmation(KirikaeConfirmation kirikaeConfirmation, User user) throws Exception {
        kirikaeConfirmation.setCreateTime(new Date());
        kirikaeConfirmation.setCreateBy(user.getUserId());
        this.kirikaeConfirmationDao.insertSelective(kirikaeConfirmation);
        //切替指示书已填写，启动下一流程（切替指示书确认）
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(kirikaeConfirmation.getOrderId());
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_PREPARED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public KirikaeConfirmation getKirikaeConfirmationByOrderId(Integer orderId) throws Exception {
        return this.kirikaeConfirmationDao.selectKirikaeConfirmationByOrderId(orderId);
    }

    @Override
    public void editCheckedKirikaeConfirmation(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CHECKED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public void editApprovedKirikaeConfirmation(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_APPROVED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

}
