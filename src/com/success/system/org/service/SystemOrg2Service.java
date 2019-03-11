package com.success.system.org.service;

import com.success.sys.user.domain.User;
import com.success.system.org.domain.SystemOrg;
import com.success.system.org.query.SystemOrgQuery;

import java.util.List;
import java.util.Map;

public interface SystemOrg2Service {

    /**
     * 新增组织
     * @param systemOrg 组织实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    void addSystemOrg(SystemOrg systemOrg, User user) throws Exception;

    /**
     * 修改组织信息
     * @param systemOrg 组织实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    void editSystemOrg(SystemOrg systemOrg, User user) throws Exception;

    /**
     * 删除组织
     * @param systemOrg 组织实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    void deleteSystemOrg(SystemOrg systemOrg, User user) throws Exception;

    /**
     * 查询组织列表
     * @param systemOrgQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<SystemOrg> listSystemOrg(SystemOrgQuery systemOrgQuery) throws Exception;

}
