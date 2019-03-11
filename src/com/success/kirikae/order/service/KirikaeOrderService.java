package com.success.kirikae.order.service;

import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.query.KirikaeOrderQuery;
import com.success.sys.user.domain.User;

import java.util.List;
import java.util.Map;

public interface KirikaeOrderService {

    /**
     * 新增切替变更单
     * @param kirikaeOrder 切替变更单实体
     * @throws Exception 返回异常
     */
    void addKirikaeOrder(KirikaeOrder kirikaeOrder, User user) throws Exception;

    /**
     * 修改切替变更单
     * @param kirikaeOrder 切替变更单实体
     * @throws Exception 返回异常
     */
    void editKirikaeOrder(KirikaeOrder kirikaeOrder, User user) throws Exception;

    /**
     * 获取切替变更单
     * @param kirikaeOrder 切替变更单实体
     * @return 返回结果
     * @throws Exception 异常
     */
    KirikaeOrder getKirikaeOrder(KirikaeOrder kirikaeOrder) throws Exception;

    /**
     * 获取切替变更单--通过变更单ID
     * @param alterationOrderId 变更单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    KirikaeOrder getKirikaeOrderByAlterationOrderId(Integer alterationOrderId) throws Exception;

    /**
     * 获取切替单列表--分页
     * @param kirikaeOrderQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listKirikaeOrderPage(KirikaeOrderQuery kirikaeOrderQuery) throws Exception;

    /**
     * 获取切替单列表--屏幕展示
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listKirikaeOrderScreenShow() throws Exception;

    /**
     * 获取切替单数量
     * @param kirikaeOrderQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer countKirikaeOrder(KirikaeOrderQuery kirikaeOrderQuery) throws Exception;

    /**
     * 验证是否可以重走流程
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    Boolean validOrderRepeat(Integer orderId) throws Exception;

    /**
     * 验证切替时间
     * @param orderId 切替单ID
     * @param designChangeTiming 切替时间
     * @param user 用户
     */
    void editValidDesignChangeTiming(Integer orderId, String designChangeTiming, User user) throws Exception;

    /**
     * 生成4M单完成
     * @param orderId 切替单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editGenerateFourOrderProcedure(Integer orderId, User user) throws Exception;

}
