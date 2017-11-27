package com.takata.system.org.service;

import com.takata.system.org.domain.SystemOrg;
import com.takata.system.org.query.SystemOrgQuery;

import java.util.List;
import java.util.Map;

public interface SystemOrgService {

    /**
     * 新增组织
     * @param systemOrg 组织实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer addSystemOrg(SystemOrg systemOrg) throws Exception;

    /**
     * 修改组织信息
     * @param systemOrg 组织实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer editSystemOrg(SystemOrg systemOrg) throws Exception;

    /**
     * 删除组织
     * @param systemOrg 组织实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer deleteSystemOrg(SystemOrg systemOrg) throws Exception;

    /**
     * 查询当前用户分公司下的所有组织
     * @param systemOrgQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryCompanyOrgTree(SystemOrgQuery systemOrgQuery) throws Exception;

    /**
     * 查询所有组织
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryAllSystemOrgList() throws Exception;
}
