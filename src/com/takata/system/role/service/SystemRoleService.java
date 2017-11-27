package com.takata.system.role.service;

import com.takata.system.role.domain.SystemRole;
import com.takata.system.role.query.SystemRoleQuery;

import java.util.List;
import java.util.Map;

public interface SystemRoleService {

    /**
     * 新增角色
     * @param systemRole 角色实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer addSystemRole(SystemRole systemRole) throws Exception;

    /**
     * 修改角色信息
     * @param systemRole 角色实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer editSystemRole(SystemRole systemRole) throws Exception;

    /**
     * 删除角色
     * @param systemRole 角色实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer deleteSystemRole(SystemRole systemRole) throws Exception;

    /**
     * 查询角色管理页面列表
     * @param systemRoleQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemRolePageList(SystemRoleQuery systemRoleQuery) throws Exception;

    /**
     * 查询角色管理页面总数
     * @param systemRoleQuery 查询条件
     * @return 返回总数
     * @throws Exception 异常
     */
    Integer querySystemRoleCount(SystemRoleQuery systemRoleQuery) throws Exception;
    
}
