package com.success.kirikae.order.service;

import com.success.kirikae.order.query.KirikaeOrderAgencyQuery;

import java.util.List;
import java.util.Map;

public interface KirikaeOrderAgencyService {

    /**
     * 切替单待办列表--分页
     * @param kirikaeOrderAgencyQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listKirikaeOrderAgencyPage(KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery) throws Exception;

    /**
     * 切替单待办数量
     * @param kirikaeOrderAgencyQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer countKirikaeOrderAgency(KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery) throws Exception;

}
