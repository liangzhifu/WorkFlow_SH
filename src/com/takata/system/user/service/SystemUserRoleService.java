package com.takata.system.user.service;

import com.takata.system.user.domain.SystemUserRole;
import com.takata.system.user.query.SystemUserRoleQuery;

import java.util.List;
import java.util.Map;

public interface SystemUserRoleService {

    /**
     * 新增用户角色关联
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     * @throws Exception 异常
     */
    void addSystemUserRole(Integer userId, String[] roleIds) throws Exception;

    /**
     * 修改用户角色关联信息
     * @param systemUserRole 用户角色关联实体信息
     * @throws Exception 异常
     */
    void editSystemUserRole(SystemUserRole systemUserRole) throws Exception;

    /**
     * 删除用户角色关联
     * @param systemUserRole 用户角色关联实体信息
     * @throws Exception 异常
     */
    void deleteSystemUserRole(SystemUserRole systemUserRole) throws Exception;

    /**
     * 删除用户--删除用户角色关联
     * @param systemUserRoleQuery 查询条件
     * @throws Exception 异常
     */
    void deleteSystemUserRoleByUser(SystemUserRoleQuery systemUserRoleQuery) throws Exception;

    /**
     * 查询用户角色关联管理页面列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemUserRolePageList(SystemUserRoleQuery systemUserRoleQuery) throws Exception;

    /**
     * 查询用户角色关联管理页面总数
     * @param systemUserRoleQuery 查询条件
     * @return 返回总数
     * @throws Exception 异常
     */
    Integer querySystemUserRoleCount(SystemUserRoleQuery systemUserRoleQuery) throws Exception;

    /**
     * 查询用户角色关联管理页面列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemUserRoleList(SystemUserRoleQuery systemUserRoleQuery) throws Exception;

    /**
     * 查询可以加入的角色列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryAddRoleList(SystemUserRoleQuery systemUserRoleQuery) throws Exception;

}
