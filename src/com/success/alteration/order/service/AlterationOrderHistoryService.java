package com.success.alteration.order.service;

import com.success.alteration.order.domain.AlterationOrderHistory;

public interface AlterationOrderHistoryService {

    /**
     * 获取变更单历史信息
     * @param alterationOrderHistory 变更单历史实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    AlterationOrderHistory getAlterationOrderHistory(AlterationOrderHistory alterationOrderHistory) throws Exception;

}
