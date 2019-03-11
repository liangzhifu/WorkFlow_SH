package com.success.kirikae.wo.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.kirikae.wo.constant.KirikaeWoOrderEnum;
import com.success.kirikae.wo.dao.KirikaeWoOrderAttrDao;
import com.success.kirikae.wo.dao.KirikaeWoOrderDao;
import com.success.kirikae.wo.domain.KirikaeWoOrder;
import com.success.kirikae.wo.domain.KirikaeWoOrderAttr;
import com.success.kirikae.wo.service.KirikaeWoOrderAttrService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("kirikaeWoOrderAttrService")
public class KirikaeWoOrderAttrServiceImpl implements KirikaeWoOrderAttrService {

    @Resource(name = "kirikaeWoOrderAttrDao")
    private KirikaeWoOrderAttrDao kirikaeWoOrderAttrDao;

    @Resource(name = "kirikaeWoOrderDao")
    private KirikaeWoOrderDao kirikaeWoOrderDao;

    @Override
    public List<Map<String, Object>> listKirikaeWoOrderAttrAdd(Integer orderId, Integer userId) throws Exception {
        return this.kirikaeWoOrderAttrDao.selectKirikaeWoOrderAttrAddList(orderId, userId);
    }

    @Override
    public List<Map<String, Object>> listKirikaeWoOrderAttrMapByOrderId(Integer orderId) throws Exception {
        return this.kirikaeWoOrderAttrDao.selectKirikaeWoOrderAttrMapListByOrderId(orderId);
    }

    @Override
    public List<Map<String, Object>> listKirikaeWoOrderAttrByUserId(Integer orderId, Integer userId, String stateType) throws Exception {
        return this.kirikaeWoOrderAttrDao.selectKirikaeWoOrderAttrListByUserId(orderId, userId, stateType);
    }

    @Override
    public void addKirikaeWoOrderAttr(String[] woOrderIds, String[] questionIds, String[] preparedUsers, String[] changeCompleteTimes, User user) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < woOrderIds.length; i++){
            Integer woOrderId = Integer.valueOf(woOrderIds[i]);
            if(!list.contains(woOrderId)){
                list.add(woOrderId);
            }
        }

        //删除工单的所有属性
        for(Integer woOrderId : list){
            this.kirikaeWoOrderAttrDao.deleteWoOrderAttrByWoOrderId(woOrderId);
        }

        //更改工单状态
        for(Integer woOrderId : list){
            KirikaeWoOrder kirikaeWoOrder = new KirikaeWoOrder();
            kirikaeWoOrder.setId(woOrderId);
            kirikaeWoOrder.setWoOrderState(KirikaeWoOrderEnum.WoOrderStateEnum.ORDER_STATE_THREE.getCode());
            kirikaeWoOrder.setConfirmTime(new Date());
            kirikaeWoOrder.setConfirmBy(user.getUserId());
            this.kirikaeWoOrderDao.updateByPrimaryKeySelective(kirikaeWoOrder);
        }

        //添加工单属性
        for(int i = 0; i < woOrderIds.length; i++){
            KirikaeWoOrderAttr kirikaeWoOrderAttr = new KirikaeWoOrderAttr();
            kirikaeWoOrderAttr.setWoOrderId(Integer.valueOf(woOrderIds[i]));
            Integer questionId = Integer.valueOf(questionIds[i]);
            kirikaeWoOrderAttr.setQuestionId(questionId);
            Integer preparedUser = Integer.valueOf(preparedUsers[i]);
            if(preparedUser.intValue() == 0){
                preparedUser = null;
            }
            kirikaeWoOrderAttr.setPreparedUser(preparedUser);
            Date changeCompleteTime = null;
            String changeCompleteTimeStr = changeCompleteTimes[i];
            if(!(changeCompleteTimeStr == null || "1".equals(changeCompleteTimeStr))){
                changeCompleteTime = sdf.parse(changeCompleteTimeStr);
            }
            kirikaeWoOrderAttr.setChangeCompleteTime(changeCompleteTime);
            if(questionId.intValue() == 1){
                kirikaeWoOrderAttr.setPreparedState(1);
                kirikaeWoOrderAttr.setFileState(1);
                kirikaeWoOrderAttr.setAgreementState(1);
                kirikaeWoOrderAttr.setAgreementValidState(1);
            }else {
                kirikaeWoOrderAttr.setPreparedState(0);
                kirikaeWoOrderAttr.setFileState(0);
                kirikaeWoOrderAttr.setAgreementState(0);
                kirikaeWoOrderAttr.setAgreementValidState(0);
            }
            kirikaeWoOrderAttr.setCreateBy(user.getUserId());
            kirikaeWoOrderAttr.setCreateTime(new Date());
            kirikaeWoOrderAttr.setUpdateBy(user.getUserId());
            kirikaeWoOrderAttr.setUpdateTime(new Date());
            kirikaeWoOrderAttr.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            this.kirikaeWoOrderAttrDao.insertSelective(kirikaeWoOrderAttr);
        }
    }

    @Override
    public void editKirikaeWoOrderAttrConfrim(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception {
        for(KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
            kirikaeWoOrderAttr.setPreparedTime(new Date());
            kirikaeWoOrderAttr.setPreparedState(1);
            this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
        }

    }

    @Override
    public void editKirikaeWoOrderAttrReview(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception {
        for(KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
            Integer questionId = kirikaeWoOrderAttr.getQuestionId();
            if (questionId != 1) {
                kirikaeWoOrderAttr.setReviewTime(new Date());
            }
            this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
        }

    }

    @Override
    public void editKirikaeWoOrderAttrUpload(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception {
        for(KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
            kirikaeWoOrderAttr.setFileState(1);
            kirikaeWoOrderAttr.setFileConfirmTime(new Date());
            this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
        }
    }

    @Override
    public void editKirikaeWoOrderAttrStandClose(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception {
        for(KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
            kirikaeWoOrderAttr.setAgreementState(1);
            kirikaeWoOrderAttr.setAgreementTime(new Date());
            this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
        }
    }

    @Override
    public void deleteKirikaeWoOrderAttr(int id, User user) throws Exception {
        KirikaeWoOrderAttr kirikaeWoOrderAttr = new KirikaeWoOrderAttr();
        kirikaeWoOrderAttr.setId(id);
        kirikaeWoOrderAttr.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        kirikaeWoOrderAttr.setUpdateBy(user.getUserId());
        kirikaeWoOrderAttr.setUpdateTime(new Date());
        this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
    }

    @Override
    public void addKirikaeWoOrderAttr(KirikaeWoOrderAttr kirikaeWoOrderAttr, User user) throws Exception {
       Integer questionId = kirikaeWoOrderAttr.getQuestionId();
        if(questionId.intValue() == 1){
            kirikaeWoOrderAttr.setPreparedState(1);
            kirikaeWoOrderAttr.setFileState(1);
            kirikaeWoOrderAttr.setAgreementState(1);
            kirikaeWoOrderAttr.setAgreementValidState(1);
        }else {
            kirikaeWoOrderAttr.setPreparedState(1);
            kirikaeWoOrderAttr.setFileState(0);
            kirikaeWoOrderAttr.setAgreementState(0);
            kirikaeWoOrderAttr.setAgreementValidState(0);
        }
        kirikaeWoOrderAttr.setCreateBy(user.getUserId());
        kirikaeWoOrderAttr.setCreateTime(new Date());
        kirikaeWoOrderAttr.setUpdateBy(user.getUserId());
        kirikaeWoOrderAttr.setUpdateTime(new Date());
        kirikaeWoOrderAttr.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        this.kirikaeWoOrderAttrDao.insertSelective(kirikaeWoOrderAttr);
    }

    @Override
    public List<Map<String, Object>> listKirikaeAgreement(Integer orderId) throws Exception {
        return this.kirikaeWoOrderAttrDao.selectKirikaeAgreementList(orderId);
    }

    @Override
    public void editKirikaeWoOrderAttrStandCloseValid(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception {
        for(KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
            kirikaeWoOrderAttr.setAgreementValidState(1);
            kirikaeWoOrderAttr.setAgreementValidTime(new Date());
            this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
        }
    }

    @Override
    public void editRefuseWoOrderAttr(String[] woOrderIds, String refuse) throws Exception {
        for (String woOrderId : woOrderIds){
            List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = this.kirikaeWoOrderAttrDao.selectKirikaeWoOrderAttrListByWoOrderId(Integer.valueOf(woOrderId));
            for (KirikaeWoOrderAttr kirikaeWoOrderAttr : kirikaeWoOrderAttrList){
                Integer questionId = kirikaeWoOrderAttr.getQuestionId();
                if (questionId != 1) {
                    kirikaeWoOrderAttr.setFileState(0);
                    kirikaeWoOrderAttr.setFileId(0);
                    kirikaeWoOrderAttr.setAgreementState(0);
                    kirikaeWoOrderAttr.setAgreementValidState(0);
                    kirikaeWoOrderAttr.setRefuseReason(refuse);
                    this.kirikaeWoOrderAttrDao.updateByPrimaryKeySelective(kirikaeWoOrderAttr);
                }
            }
        }
    }

}
