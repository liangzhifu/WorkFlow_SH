package com.success.kirikae.procedure.service;

import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.sys.user.domain.User;

import java.util.List;

/**
 * @author lzf
 **/

public interface KirikaeOrderProcedureService {

    /**
     * 开启下一流程步骤
     * @param orderId 切替单ID
     * @param procedureSeq 步骤顺序
     * @throws Exception 异常
     */
    void editStartNextProcedure(Integer orderId, Integer procedureSeq, User user) throws Exception;

    /**
     * 处理当前流程步骤--开始
     * @param kirikaeOrderProcedure 订单流程
     * @throws Exception 异常
     */
    void editStartKirikaeOrderProcedure(KirikaeOrderProcedure kirikaeOrderProcedure, User user) throws Exception;

    /**
     * 处理当前流程步骤--完结
     * @param kirikaeOrderProcedure 订单流程
     * @throws Exception 异常
     */
    void editCompleteKirikaeOrderProcedure(KirikaeOrderProcedure kirikaeOrderProcedure, User user) throws Exception;

    /**
     * 生成切替的流程
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void editStartFirstKirikaeOrderProcedure(Integer orderId, User user) throws Exception;

    /**
     * 查询切替单的所有流程步骤
     * @param orderId 切替单实体ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<KirikaeOrderProcedure> listKirikaeOrderProcedureByOrderId(Integer orderId) throws Exception;

    /**
     * 确认项目都确认后，启动下一流程
     * @param orderId 订单ID
     * @throws Exception 异常
     */
    void editWoOrderConfirm(Integer orderId, User user) throws Exception;

    /**
     * 所有属性都填写立合结果，启动下一流程
     * @param orderId 订单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editWoOrderAttrStandClose(Integer orderId, User user) throws Exception;

    /**
     * 所有文件上传后，启动下一流程
     * @param orderId 订单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editWoOrderAttrUpload(Integer orderId, User user) throws Exception;

    /**
     * 修改拒绝流程状态
     * @param orderId 订单ID
     * @throws Exception 异常
     */
    void editRefuseOrderProcedure(Integer orderId) throws Exception;

}
