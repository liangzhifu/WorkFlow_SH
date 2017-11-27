package com.takata.system.role.service;

import com.takata.system.role.domain.SystemRoleMenu;
import com.takata.system.role.query.SystemRoleMenuQuery;

import java.util.List;
import java.util.Map;

public interface SystemRoleMenuService {

    /**
     * 新增角色菜单关联
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @throws Exception 异常
     */
    void addSystemRoleMenu(Integer roleId, String[] menuIds) throws Exception;

    /**
     * 修改角色菜单关联信息
     * @param systemRoleMenu 角色菜单关联实体信息
     * @throws Exception 异常
     */
    void deleteSystemRoleMenu(SystemRoleMenu systemRoleMenu) throws Exception;

    /**
     * 删除角色所对应的角色菜单关联
     * @param systemRoleMenuQuery 查询条件
     * @throws Exception 异常
     */
    void deleteSystemRoleMenuByRole(SystemRoleMenuQuery systemRoleMenuQuery) throws Exception;

    /**
     * 删除菜单所对应的角色菜单关联
     * @param systemRoleMenuQuery 查询条件
     * @throws Exception 异常
     */
    void deleteSystemRoleMenuByMenu(SystemRoleMenuQuery systemRoleMenuQuery) throws Exception;

    /**
     * 查询角色对应的所有菜单
     * @param systemRoleMenuQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemRoleMenuList(SystemRoleMenuQuery systemRoleMenuQuery)throws Exception;

}
