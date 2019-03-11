package com.success.alteration.order.service;

import com.success.alteration.order.domain.AlterationOrder;
import com.success.sys.user.domain.User;

public interface AlterationOrderService {

    /**
     * 获取变更单信息
     * @param alterationOrder 变更单实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    AlterationOrder getAlterationOrder(AlterationOrder alterationOrder) throws Exception;

    /**
     * 新增变更单
     * @param alterationOrder 变更单实体信息
     * @throws Exception 异常
     */
    void addAlterationOrder(AlterationOrder alterationOrder, User user) throws Exception;

    /**
     * 修改变更单
     * @param alterationOrder 变更单实体信息
     * @throws Exception 异常
     */
    void editAlterationOrder(AlterationOrder alterationOrder, User user) throws Exception;

    /**
     * 作废变更单
     * @param orderId 变更单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editAlterationOrderToVoid(Integer orderId, User user) throws Exception;

    /**
     * 添加切替单历史
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void editCopyAlterationOrder(Integer orderId, User user) throws Exception;

    /**
     * 初始化切替单
     * @param orderId 切替单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editInitAlterationOrder(Integer orderId, User user) throws Exception;

    /**
     * 更新切替单
     * @param alterationOrder 切替单实体
     * @throws Exception 返回异常
     */
    void updateAlterationOrder(AlterationOrder alterationOrder) throws Exception;

}
