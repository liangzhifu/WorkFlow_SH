package com.success.four.order.service;

import com.success.four.order.domain.FourOrderAttr;

import java.util.List;

public interface FourOrderAttrService {

    /**
     * 查询订单属性列表--通过订单ID
     * @param orderId 订单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<FourOrderAttr> listFourOrderAttrByOrderId(Integer orderId) throws Exception;

}
