package com.success.kirikae.instruction.service.impl;

import com.success.kirikae.instruction.dao.KirikaeInstructionDao;
import com.success.kirikae.instruction.domain.KirikaeInstruction;
import com.success.kirikae.instruction.service.KirikaeInstructionService;
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
@Service("kirikaeInstructionService")
public class KirikaeInstructionServiceImpl implements KirikaeInstructionService {

    @Resource(name = "kirikaeInstructionDao")
    private KirikaeInstructionDao kirikaeInstructionDao;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    @Override
    public void addKirikaeInstruction(KirikaeInstruction kirikaeInstruction, User user) throws Exception {
        kirikaeInstruction.setCreateBy(user.getUserId());
        kirikaeInstruction.setCreateTime(new Date());
        this.kirikaeInstructionDao.insertSelective(kirikaeInstruction);
        //切替指示书已填写，启动下一流程（切替指示书确认）
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(kirikaeInstruction.getOrderId());
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_PREPARED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public KirikaeInstruction getKirikaeInstructionByOrderId(Integer orderId) throws Exception {
        return this.kirikaeInstructionDao.selectKirikaeInstructionByOrderId(orderId);
    }

    @Override
    public void editCheckedKirikaeInstruction(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_CHECKED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public void editApprovedKirikaeInstruction(Integer orderId, User user) throws Exception {
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_APPROVED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }
}
