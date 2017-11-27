package com.takata.system.user.service;

import com.takata.system.user.domain.SystemUserOrg;
import com.takata.system.user.query.SystemUserOrgQuery;

import java.util.List;
import java.util.Map;

public interface SystemUserOrgService {

    /**
     * 新增用户组织关联
     * @param userId 用户ID
     * @param orgIds 组织ID
     * @return 返回结果
     * @throws Exception 异常
     */
    void addSystemUserOrg(Integer userId, String[] orgIds) throws Exception;

    /**
     * 修改用户组织关联信息
     * @param userId 用户ID
     * @param orgIds 组织ID
     * @return 返回结果
     * @throws Exception 异常
     */
    void editSystemUserOrg(Integer userId, String[] orgIds) throws Exception;

    /**
     * 删除用户组织关联
     * @param systemUserOrg 用户组织关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer deleteSystemUserOrg(SystemUserOrg systemUserOrg) throws Exception;

    /**
     * 删除用户后，删除用户关联的组织信息
     * @param systemUserOrgQuery 查询条件
     * @throws Exception 返回异常
     */
    void deleteSystemUserOrgByUser(SystemUserOrgQuery systemUserOrgQuery) throws Exception;

    /**
     * 删除组织后，删除用户关联组织信息
     * @param systemUserOrgQuery 查询条件
     * @throws Exception 返回异常
     */
    void deleteSystemUserOrgByOrg(SystemUserOrgQuery systemUserOrgQuery) throws Exception;

    /**
     * 查询用户组织关联管理页面列表
     * @param systemUserOrgQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemUserOrgList(SystemUserOrgQuery systemUserOrgQuery) throws Exception;

}
