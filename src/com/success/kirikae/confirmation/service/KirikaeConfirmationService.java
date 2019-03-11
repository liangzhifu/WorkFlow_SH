package com.success.kirikae.confirmation.service;

import com.success.kirikae.confirmation.domain.KirikaeConfirmation;
import com.success.sys.user.domain.User;

public interface KirikaeConfirmationService {

    /**
     * 新增切替确认书
     * @param kirikaeConfirmation 切替确认书实体
     * @throws Exception 异常
     */
    void addKirikaeConfirmation(KirikaeConfirmation kirikaeConfirmation, User user) throws Exception;

    /**
     * 获取切替确认书--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    KirikaeConfirmation getKirikaeConfirmationByOrderId(Integer orderId) throws Exception;

    /**
     * 确认书确认
     * @param orderId 切替单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editCheckedKirikaeConfirmation(Integer orderId, User user) throws Exception;

    /**
     * 确认书承认
     * @param orderId 切替单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editApprovedKirikaeConfirmation(Integer orderId, User user) throws Exception;
}
