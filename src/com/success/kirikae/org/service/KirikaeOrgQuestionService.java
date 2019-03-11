package com.success.kirikae.org.service;

import com.success.kirikae.org.domain.KirikaeOrgQuestion;
import com.success.kirikae.org.query.KirikaeOrgQuestionQuery;
import com.success.sys.user.domain.User;

import java.util.List;
import java.util.Map;

public interface KirikaeOrgQuestionService {

    /**
     * 新增组织切替问题点关联
     * @param orgId 组织Id
     * @param questionIds 确认问题点ID数组
     * @throws Exception 异常
     */
    void addKirikaeOrgQuestion(Integer orgId, String[] questionIds, User user) throws Exception;

    /**
     * 删除组织切替问题点关联
     * @param kirikaeOrgQuestion 组织切替问题点关联实体
     * @throws Exception 异常
     */
    void deleteKirikaeOrgQuestion(KirikaeOrgQuestion kirikaeOrgQuestion, User user) throws Exception;

    /**
     * 删除组织--删除组织切替问题点关联
     * @param orgId 组织ID
     * @throws Exception 异常
     */
    void deleteKirikaeOrgQuestionByOrg(Integer orgId, User user) throws Exception;

    /**
     * 删除切替问题点--删除组织切替问题点关联
     * @param kirikaeOrgQuestionQuery 查询条件
     * @throws Exception 异常
     */
    void deleteKirikaeOrgQuestionByQuestion(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery, User user) throws Exception;

    /**
     * 查询组织切替问题点关联列表
     * @param kirikaeOrgQuestionQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listKirikaeOrgQuestion(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery) throws Exception;

    /**
     * 查询可以添加的确认内容列表
     * @param kirikaeOrgQuestionQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listAddQuestion(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery) throws Exception;

    /**
     * 查询可以添加的确认内容列表
     * @param woOrderId 工单ID
     * @param orgId 组织ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> listWoOrderAddQuestion(Integer woOrderId, Integer orgId) throws Exception;

}
