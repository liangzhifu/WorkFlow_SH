package com.success.kirikae.order.service;

import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.query.KirikaeOrderChangeContentQuery;
import com.success.sys.user.domain.User;

import java.util.List;

public interface KirikaeOrderChangeContentService {

    /**
     * 添加多个变更内容
     * @param kirikaeOrderChangeContentList 变更内容实体列表
     * @throws Exception 异常
     */
    void addKirikaeOrderChangeContentList(List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList, User user) throws Exception;

    /**
     * 删除切替单的所有变更内容
     * @param orderId 切替单ID
     * @throws Exception 异常
     */
    void deleteKirikaeOrderChangeContentByOrderId(Integer orderId, User user) throws Exception;

    /**
     * 查询变更内容列表
     * @param orderId 切替单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<KirikaeOrderChangeContent> listKirikaeOrderChangeContentByOrderId(Integer orderId) throws Exception;

}
