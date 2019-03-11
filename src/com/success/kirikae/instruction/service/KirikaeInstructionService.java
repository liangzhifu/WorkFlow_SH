package com.success.kirikae.instruction.service;

import com.success.kirikae.instruction.domain.KirikaeInstruction;
import com.success.sys.user.domain.User;

public interface KirikaeInstructionService {

    /**
     * 添加切替指示书
     * @param kirikaeInstruction 切替指示书实体
     * @return 返回结果
     * @throws Exception 异常
     */
    void addKirikaeInstruction(KirikaeInstruction kirikaeInstruction, User user) throws Exception;

    /**
     * 获取切替指示书--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    KirikaeInstruction getKirikaeInstructionByOrderId(Integer orderId) throws Exception;

    /**
     * 指示书确认
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void editCheckedKirikaeInstruction(Integer orderId, User user) throws Exception;

    /**
     * 指示书承认
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void editApprovedKirikaeInstruction(Integer orderId, User user) throws Exception;

}
