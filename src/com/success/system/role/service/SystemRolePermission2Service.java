package com.success.system.role.service;

import com.success.sys.user.domain.User;
import com.success.system.role.domain.SystemRolePermission;
import com.success.system.role.query.SystemRolePermissionQuery;

import java.util.List;
import java.util.Map;

public interface SystemRolePermission2Service {

    /**
     * 新增角色权限关联
     * @param roleId 角色ID
     * @param permissionIds 权限ID数组
     * @throws Exception 异常
     */
    void addSystemRolePermission(Integer roleId, String[] permissionIds, User user) throws Exception;

    /**
     * 删除角色关联
     * @param systemRolePermission 角色权限关联实体
     * @return 返回结果
     * @throws Exception 异常
     */
    void deleteSystemRolePermission(SystemRolePermission systemRolePermission, User user) throws Exception;

    /**
     * 删除角色--删除对应角色权限关联
     * @param roleId 角色ID
     * @return 返回结果
     * @throws Exception 异常
     */
    void deleteSystemPermissionByRole(Integer roleId, User user) throws Exception;

    /**
     * 查询角色权限关联管理页面列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<SystemRolePermission> listSystemRolePermission(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;
    
}
