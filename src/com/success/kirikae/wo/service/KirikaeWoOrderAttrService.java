package com.success.kirikae.wo.service;

import com.success.kirikae.wo.domain.KirikaeWoOrderAttr;
import com.success.sys.user.domain.User;

import java.util.List;
import java.util.Map;

public interface KirikaeWoOrderAttrService {

    /**
     *
     * @param orderId
     * @param userId
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listKirikaeWoOrderAttrAdd(Integer orderId, Integer userId) throws Exception;

    /**
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listKirikaeWoOrderAttrMapByOrderId(Integer orderId) throws Exception;

    /**
     *
     * @param orderId
     * @param userId
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listKirikaeWoOrderAttrByUserId(Integer orderId, Integer userId, String stateType) throws Exception;

    /**
     * 添加工单属性
     * @param woOrderIds
     * @param questionIds
     * @param preparedUsers
     * @param changeCompleteTimes
     * @param user
     * @throws Exception
     */
    void addKirikaeWoOrderAttr(String[] woOrderIds, String[] questionIds, String[] preparedUsers, String[] changeCompleteTimes, User user) throws Exception;

    /**
     * 确认属性
     * @param kirikaeWoOrderAttrList
     * @param user
     * @throws Exception
     */
    void editKirikaeWoOrderAttrConfrim(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception;

    /**
     * 评审
     * @param kirikaeWoOrderAttrList
     * @param user
     * @throws Exception
     */
    void editKirikaeWoOrderAttrReview(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception;

    /**
     * 文件上传
     * @param kirikaeWoOrderAttrList
     * @param user
     * @throws Exception
     */
    void editKirikaeWoOrderAttrUpload(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception;

    /**
     * 立合结果
     * @param kirikaeWoOrderAttrList
     * @param user
     * @throws Exception
     */
    void editKirikaeWoOrderAttrStandClose(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception;

    /**
     * 删除工单属性
     * @param id
     * @param user
     * @throws Exception
     */
    void deleteKirikaeWoOrderAttr(int id, User user) throws Exception;

    /**
     * 新增工单属性
     * @param kirikaeWoOrderAttr
     * @param user
     * @throws Exception
     */
    void addKirikaeWoOrderAttr(KirikaeWoOrderAttr kirikaeWoOrderAttr, User user) throws Exception;

    /**
     * 获取立合列表
     * @param orderId 订单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listKirikaeAgreement(Integer orderId) throws Exception;

    /**
     * 立合结果验证
     * @param kirikaeWoOrderAttrList
     * @param user
     * @throws Exception
     */
    void editKirikaeWoOrderAttrStandCloseValid(List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList, User user) throws Exception;

    /**
     * 修改拒绝确认项目
     * @param woOrderIds 工单列表
     * @param refuse 拒绝原因
     * @throws Exception 异常
     */
    void editRefuseWoOrderAttr(String[] woOrderIds, String refuse) throws Exception;

}
