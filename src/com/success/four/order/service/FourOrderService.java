package com.success.four.order.service;

import com.success.four.order.domain.FourOrder;

public interface FourOrderService {

    /**
     * 插入新的4M订单
     * @param fourOrder 4M订单实体
     * @throws Exception 异常
     */
    void addFourOrder(FourOrder fourOrder) throws Exception;

    /**
     * 更新4M订单实体
     * @param fourOrder 4M订单实体
     * @throws Exception 异常
     */
    void editFourOrder(FourOrder fourOrder) throws Exception;

    /**
     * 获取4M订单实体
     * @param fourOrder 4M订单实体
     * @return 返回结果
     * @throws Exception 异常
     */
    FourOrder getFourOrder(FourOrder fourOrder) throws Exception;

}
