package com.success.four.order.service.impl;

import com.success.four.order.dao.FourOrderDao;
import com.success.four.order.domain.FourOrder;
import com.success.four.order.service.FourOrderService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lzf
 **/
@Service("fourOrderService")
public class FourOrderServiceImpl implements FourOrderService {

    @Resource(name = "fourOrderDao")
    private FourOrderDao fourOrderDao;

    @Override
    public void addFourOrder(FourOrder fourOrder) throws Exception {
        this.fourOrderDao.insertSelective(fourOrder);
    }

    @Override
    public void editFourOrder(FourOrder fourOrder) throws Exception {
        this.fourOrderDao.updateByPrimaryKeySelective(fourOrder);
    }

    @Override
    public FourOrder getFourOrder(FourOrder fourOrder) throws Exception {
        return this.fourOrderDao.selectByPrimaryKey(fourOrder);
    }
}
