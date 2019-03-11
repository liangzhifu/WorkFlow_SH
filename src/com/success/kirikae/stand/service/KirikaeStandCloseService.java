package com.success.kirikae.stand.service;

import com.success.kirikae.stand.domain.KirikaeStandClose;
import com.success.sys.user.domain.User;

import java.util.List;

public interface KirikaeStandCloseService {

    /**
     * 增加切替单立合
     * @param orderId 切替单ID
     * @param orgIds 组织ID数组
     * @param userNames 用户姓名数组
     * @throws Exception 异常
     */
    void addKirikaeStandCloseList(Integer orderId, String[] orgIds, String[] userNames, User user) throws Exception;

    /**
     * 查询切替单的立合人员
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<KirikaeStandClose> listKirikaeStandCloseByOrderId(Integer orderId) throws Exception;

    /**
     * 填写立合结果
     * @param orderId 切替单ID
     * @param woOrderAttrIds 确认内容ID数组
     * @param standCloseResults 立合结果
     * @param user 用户
     * @throws Exception 异常
     */
    void editResultStandClose(Integer orderId, String[] woOrderAttrIds, String[] standCloseResults, User user) throws Exception;

    /**
     * 立合结果确认
     * @param orderId 切替单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editCheckedStandClose(Integer orderId, User user) throws Exception;

    /**
     * 立合结果承认
     * @param orderId 切替单ID
     * @param user 用户
     * @throws Exception 异常
     */
    void editApprovedStandClose(Integer orderId, User user) throws Exception;

}
