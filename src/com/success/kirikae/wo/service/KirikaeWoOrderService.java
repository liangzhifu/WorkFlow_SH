package com.success.kirikae.wo.service;

import com.success.kirikae.wo.domain.KirikaeWoOrder;

import java.util.List;
import java.util.Map;

public interface KirikaeWoOrderService {

    /**
     * 通过切替单生成工单
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void addKirikaeWoOrderList(Integer orderId) throws Exception;

    /**
     * 获取工单列表--切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<KirikaeWoOrder> listKirikaeWoOrderByOrderId(Integer orderId) throws Exception;

    /**
     * 获取需要厉害的组织列表
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listStandCloseOrg(Integer orderId) throws Exception;

    /**
     * 自动更新工单状态
     * @param orderId
     * @throws Exception
     */
    void editWoOrderStateAuto(Integer orderId) throws Exception;

}
