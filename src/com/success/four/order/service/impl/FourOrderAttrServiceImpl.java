package com.success.four.order.service.impl;

import com.success.four.order.dao.FourOrderAttrDao;
import com.success.four.order.domain.FourOrderAttr;
import com.success.four.order.service.FourOrderAttrService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/
@Service("fourOrderAttrService")
public class FourOrderAttrServiceImpl implements FourOrderAttrService {

    @Resource(name = "fourOrderAttrDao")
    private FourOrderAttrDao fourOrderAttrDao;

    @Override
    public List<FourOrderAttr> listFourOrderAttrByOrderId(Integer orderId) throws Exception {
        return this.fourOrderAttrDao.selectFourOrderAttrListByOrderId(orderId);
    }
}
