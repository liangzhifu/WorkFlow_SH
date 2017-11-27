package com.takata.system.user.service;

import com.takata.system.user.domain.SystemUser;
import com.takata.system.user.query.SystemUserQuery;

import java.util.List;
import java.util.Map;

public interface SystemUserService {

    /**
     * 新增用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer addSystemUser(SystemUser systemUser) throws Exception;

    /**
     * 修改用户信息
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer editSystemUser(SystemUser systemUser) throws Exception;

    /**
     * 删除用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    Integer deleteSystemUser(SystemUser systemUser) throws Exception;

    /**
     * 查询用户管理页面列表
     * @param systemUserQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemUserPageList(SystemUserQuery systemUserQuery) throws Exception;

    /**
     * 查询用户管理页面列表
     * @param systemUserQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<Map<String, Object>> querySystemUserCompleteList(SystemUserQuery systemUserQuery) throws Exception;

    /**
     * 查询用户管理页面总数
     * @param systemUserQuery 查询条件
     * @return 返回总数
     * @throws Exception 异常
     */
    Integer querySystemUserCount(SystemUserQuery systemUserQuery) throws Exception;

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
    SystemUser querySystemUserByUserCode(SystemUser systemUser);

    /**
     * 修改用户密码
     * @param id 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws Exception 异常
     */
    void editUserPassworkd(Integer id, String oldPassword, String newPassword) throws Exception;
}
