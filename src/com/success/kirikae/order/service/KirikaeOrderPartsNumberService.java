package com.success.kirikae.order.service;

import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.query.KirikaeOrderPartsNumberQuery;
import com.success.sys.user.domain.User;

import java.util.List;

public interface KirikaeOrderPartsNumberService {
    
    /**
     * 添加多个品号变更
     * @param alterationKirikaeOrderPartsNumberList 品号变更实体列表
     * @throws Exception 异常
     */
    void addKirikaeOrderPartsNumberList(List<KirikaeOrderPartsNumber> alterationKirikaeOrderPartsNumberList, User user) throws Exception;

    /**
     * 删除切替单的所有品号变更
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void deleteKirikaeOrderPartsNumberByOrderId(Integer orderId, User user) throws Exception;

    /**
     * 查询品号变更列表
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<KirikaeOrderPartsNumber> listKirikaeOrderPartsNumberByOrderId(Integer orderId) throws Exception;

}
