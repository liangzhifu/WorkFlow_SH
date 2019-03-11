package com.success.kirikae.order.service;

import com.success.kirikae.order.query.KirikaeOrderHistoryQuery;
import com.success.kirikae.order.query.KirikaeOrderQuery;

import java.util.List;
import java.util.Map;

public interface KirikaeOrderHistoryService {

    /**
     * 获取切替单列表--分页
     * @param kirikaeOrderHistoryQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listKirikaeOrderHistoryPage(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery) throws Exception;

    /**
     * 获取切替单数量
     * @param kirikaeOrderHistoryQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer countKirikaeOrderHistory(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery) throws Exception;

}
