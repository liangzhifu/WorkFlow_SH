package com.success.kirikae.wo.service.impl;

import com.success.kirikae.wo.dao.KirikaeWoOrderDao;
import com.success.kirikae.wo.domain.KirikaeWoOrder;
import com.success.kirikae.wo.service.KirikaeWoOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("kirikaeWoOrderService")
                public class KirikaeWoOrderServiceImpl implements KirikaeWoOrderService {

    @Resource(name = "kirikaeWoOrderDao")
    private KirikaeWoOrderDao kirikaeWoOrderDao;

    @Override
    public void addKirikaeWoOrderList(Integer orderId) throws Exception {

    }

    @Override
    public List<KirikaeWoOrder> listKirikaeWoOrderByOrderId(Integer orderId) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> listStandCloseOrg(Integer orderId) throws Exception {
        return this.kirikaeWoOrderDao.selectStandCloseOrgList(orderId);
    }

    @Override
    public void editWoOrderStateAuto(Integer orderId) throws Exception {
        this.kirikaeWoOrderDao.updateWoOrderStateAuto(orderId);
    }
}
