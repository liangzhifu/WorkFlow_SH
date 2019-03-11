package com.success.system.role.service;

import com.success.sys.user.domain.User;
import com.success.system.role.domain.SystemRoleMenu;
import com.success.system.role.query.SystemRoleMenuQuery;

import java.util.List;
import java.util.Map;

public interface SystemRoleMenu2Service {

    /**
     * 新增角色菜单关联
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @throws Exception 异常
     */
    void addSystemRoleMenu(Integer roleId, String[] menuIds, User user) throws Exception;

    /**
     * 修改角色菜单关联信息
     * @param systemRoleMenu 角色菜单关联实体信息
     * @throws Exception 异常
     */
    void deleteSystemRoleMenu(SystemRoleMenu systemRoleMenu, User user) throws Exception;

    /**
     * 删除角色所对应的角色菜单关联
     * @param roleId 角色ID
     * @throws Exception 异常
     */
    void deleteSystemRoleMenuByRole(Integer roleId, User user) throws Exception;

    /**
     * 删除菜单所对应的角色菜单关联
     * @param menuId 菜单ID
     * @throws Exception 异常
     */
    void deleteSystemRoleMenuByMenu(Integer menuId, User user) throws Exception;

    /**
     * 查询角色对应的所有菜单
     * @param systemRoleMenuQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<SystemRoleMenu> listSystemRoleMenu(SystemRoleMenuQuery systemRoleMenuQuery)throws Exception;

}
