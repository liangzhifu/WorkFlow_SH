package com.success.kirikae.order.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.order.dao.KirikaeOrderPartsNumberDao;
import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.query.KirikaeOrderPartsNumberQuery;
import com.success.kirikae.order.service.KirikaeOrderPartsNumberService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("kirikaeOrderPartsNumberService")
public class KirikaeOrderPartsNumberServiceImpl implements KirikaeOrderPartsNumberService {

    @Resource(name = "kirikaeOrderPartsNumberDao")
    private KirikaeOrderPartsNumberDao kirikaeOrderPartsNumberDao;

    @Override
    public void addKirikaeOrderPartsNumberList(List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList, User user) throws Exception {
        for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : kirikaeOrderPartsNumberList){
            kirikaeOrderPartsNumber.setCreateBy(user.getUserId());
            kirikaeOrderPartsNumber.setCreateTime(new Date());
            kirikaeOrderPartsNumber.setUpdateBy(user.getUserId());
            kirikaeOrderPartsNumber.setUpdateTime(new Date());
            kirikaeOrderPartsNumber.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            Integer num = this.kirikaeOrderPartsNumberDao.insertSelective(kirikaeOrderPartsNumber);
            if(num == 0){
                throw new Exception("新增品号变革异常！");
            }
        }
    }

    @Override
    public void deleteKirikaeOrderPartsNumberByOrderId(Integer orderId, User user) throws Exception {
        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = this.kirikaeOrderPartsNumberDao.selectKirikaeOrderPartsNumberListByOrderId(orderId);
        for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : kirikaeOrderPartsNumberList){
            kirikaeOrderPartsNumber.setUpdateBy(user.getUserId());
            kirikaeOrderPartsNumber.setUpdateTime(new Date());
            kirikaeOrderPartsNumber.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
            Integer num = this.kirikaeOrderPartsNumberDao.updateByPrimaryKeySelective(kirikaeOrderPartsNumber);
            if(num == 0){
                throw new Exception("更新品号变革异常！");
            }
        }
    }

    @Override
    public List<KirikaeOrderPartsNumber> listKirikaeOrderPartsNumberByOrderId(Integer orderId) throws Exception {
        return this.kirikaeOrderPartsNumberDao.selectKirikaeOrderPartsNumberListByOrderId(orderId);
    }
}
