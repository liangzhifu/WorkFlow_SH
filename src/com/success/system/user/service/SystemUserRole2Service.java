package com.success.system.user.service;

import com.success.sys.user.domain.User;
import com.success.system.user.domain.SystemUserRole;
import com.success.system.user.query.SystemUserRoleQuery;

import java.util.List;
import java.util.Map;

public interface SystemUserRole2Service {

    /**
     * 新增用户角色关联
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     * @throws Exception 异常
     */
    void addSystemUserRole(Integer userId, String[] roleIds, User user) throws Exception;

    /**
     * 删除用户角色关联
     * @param systemUserRole 用户角色关联实体信息
     * @throws Exception 异常
     */
    void deleteSystemUserRole(SystemUserRole systemUserRole, User user) throws Exception;

    /**
     * 删除用户--删除用户角色关联
     * @param userId 用户ID
     * @throws Exception 异常
     */
    void deleteSystemUserRoleByUser(Integer userId, User user) throws Exception;

    /**
     * 删除角色--删除用户角色关联
     * @param roleId 用户ID
     * @throws Exception 异常
     */
    void deleteSystemUserRoleByRole(Integer roleId, User user) throws Exception;

    /**
     * 查询用户角色关联管理页面列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<SystemUserRole> listSystemUserRole(SystemUserRoleQuery systemUserRoleQuery) throws Exception;

}
