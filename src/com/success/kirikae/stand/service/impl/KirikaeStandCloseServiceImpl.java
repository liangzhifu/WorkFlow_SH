package com.success.kirikae.stand.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.kirikae.stand.dao.KirikaeStandCloseDao;
import com.success.kirikae.stand.domain.KirikaeStandClose;
import com.success.kirikae.stand.service.KirikaeStandCloseService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/
@Service("kirikaeStandCloseService")
public class KirikaeStandCloseServiceImpl implements KirikaeStandCloseService {

    @Resource(name = "kirikaeStandCloseDao")
    private KirikaeStandCloseDao kirikaeStandCloseDao;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    @Override
    public void addKirikaeStandCloseList(Integer orderId, String[] orgIds, String[] userNames, User user) throws Exception {
        for (int i = 0; i < orgIds.length; i++){
            KirikaeStandClose kirikaeStandClose = new KirikaeStandClose();
            kirikaeStandClose.setOrderId(orderId);
            kirikaeStandClose.setOrgId(Integer.valueOf(orgIds[i]));
            if(userNames[i] != null){
                if(!"0".equals(userNames[i])){
                    kirikaeStandClose.setUserName(userNames[i]);
                }
            }
            kirikaeStandClose.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            this.kirikaeStandCloseDao.insertSelective(kirikaeStandClose);
        }
        //立合人员已填写，启动下一流程（立合结果确认）
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_USER.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public List<KirikaeStandClose> listKirikaeStandCloseByOrderId(Integer orderId) throws Exception {
        return this.kirikaeStandCloseDao.selectKirikaeStandCloseListByOrderId(orderId);
    }

    @Override
    public void editResultStandClose(Integer orderId, String[] woOrderAttrIds, String[] standCloseResults, User user) throws Exception {

    }

    @Override
    public void editCheckedStandClose(Integer orderId, User user) throws Exception {
        //立合确认
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_CHECKED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

    @Override
    public void editApprovedStandClose(Integer orderId, User user) throws Exception {
        //立合承认
        List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
        for (KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
            if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_APPROVED.getCode().intValue()){
                this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                break;
            }
        }
    }

}
