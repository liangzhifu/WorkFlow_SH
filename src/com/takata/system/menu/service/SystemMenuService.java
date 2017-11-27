package com.takata.system.menu.service;

import com.takata.system.menu.query.SystemMenuQuery;

import java.util.List;
import java.util.Map;

public interface SystemMenuService {

    /**
     * 查询树形菜单列表
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryTreeMenuList() throws Exception;

    /**
     * 查询当前用户的模块
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryUserModule(SystemMenuQuery systemMenuQuery) throws Exception;

    /**
     * 查询用户菜单
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryUserMenuByModule(SystemMenuQuery systemMenuQuery) throws Exception;

}
