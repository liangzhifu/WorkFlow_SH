package com.success.system.user.service;

import com.success.sys.user.domain.User;
import com.success.system.user.domain.SystemUser;
import com.success.system.user.query.SystemUserQuery;

import java.util.List;
import java.util.Map;

public interface SystemUser2Service {

    /**
     * 新增用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer addSystemUser(SystemUser systemUser, User user) throws Exception;

    /**
     * 修改用户信息
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer editSystemUser(SystemUser systemUser, User user) throws Exception;

    /**
     * 删除用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer deleteSystemUser(SystemUser systemUser, User user) throws Exception;

    /**
     * 查询用户管理页面列表
     * @param systemUserQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> listSystemUserPage(SystemUserQuery systemUserQuery) throws Exception;

    /**
     * 查询用户管理页面总数
     * @param systemUserQuery 查询条件
     * @return 返回总数
     * @throws Exception 异常
     */
    Integer countSystemUser(SystemUserQuery systemUserQuery) throws Exception;

    /**
     * 检查用户是否存在
     * @param systemUser 用户实体数据
     * @return 返回结果
     * @throws Exception 异常
     */
    boolean checkUserCode(SystemUser systemUser) throws Exception;

    /**
     * 通过工号查找用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    SystemUser getSystemUserByUserCode(SystemUser systemUser);

    /**
     * 修改用户密码
     * @param id 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws Exception 异常
     */
    void editUserPassword(Integer id, String oldPassword, String newPassword) throws Exception;

    /**
     * 查询用户信息
     * @param systemUserQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<SystemUser> listSystemUser(SystemUserQuery systemUserQuery) throws Exception;

    /**
     * 查询拥有某一权限的用户列表
     * @param permissionId 权限ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<SystemUser> listSystemUserByPermissionId(Integer permissionId) throws Exception;

    /**
     * 查询一群用户中拥有某一权限的用户列表
     * @param userIds 用户数组
     * @param permissionId 权限ID
     * @return 返回结果
     * @throws Exception 异常
     */
    List<SystemUser> listSystemUserValidPermission(Integer[] userIds, Integer permissionId) throws Exception;

    /**
     * 验证用户是否拥有权限
     * @param userId 用户ID
     * @param permissionId 权限ID
     * @return 返回结果
     * @throws Exception 异常
     */
    boolean validSystemUserPermission(Integer userId, Integer permissionId) throws Exception;

}
