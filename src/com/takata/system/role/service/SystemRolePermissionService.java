package com.takata.system.role.service;

import com.takata.system.role.domain.SystemRolePermission;
import com.takata.system.role.query.SystemRolePermissionQuery;

import java.util.List;
import java.util.Map;

public interface SystemRolePermissionService {

    /**
     * 新增角色权限关联
     * @param roleId 角色ID
     * @param permissionIds 权限ID数组
     * @throws Exception 异常
     */
    void addSystemRolePermission(Integer roleId, String[] permissionIds) throws Exception;

    /**
     * 修改角色权限关联信息
     * @param systemRolePermission 角色权限关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer editSystemRolePermission(SystemRolePermission systemRolePermission) throws Exception;

    /**
     * 删除角色关联
     * @param systemRolePermission 角色权限关联实体
     * @return 返回结果
     * @throws Exception 异常
     */
    void deleteSystemRolePermission(SystemRolePermission systemRolePermission) throws Exception;

    /**
     * 删除角色--删除对应角色权限关联
     * @param systemRolePermissionQuery 角色ID
     * @return 返回结果
     * @throws Exception 异常
     */
    void deleteSystemPermissionByRole(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;

    /**
     * 查询角色权限关联管理页面列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemRolePermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;

    /**
     * 查询角色权限关联管理页面总数
     * @param systemRolePermissionQuery 查询条件
     * @return 返回总数
     * @throws Exception 异常
     */
    Integer querySystemRolePermissionCount(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;

    /**
     * 查询可以关联的权限列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryAddPermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;

    /**
     * 查询可以关联的权限列表总数
     * @param systemRolePermissionQuery 查询条件
     * @return 返回总数
     * @throws Exception 异常
     */
    Integer queryAddPermissionCount(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;
    
}
