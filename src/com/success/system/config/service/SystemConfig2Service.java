package com.success.system.config.service;

import com.success.sys.user.domain.User;
import com.success.system.config.domain.SystemConfig;
import com.success.system.config.query.SystemConfigQuery;

import java.util.List;
import java.util.Map;

public interface SystemConfig2Service {

    /**
     * 删除下拉菜单选项
     * @param systemConfig 拉菜单选项
     * @throws Exception 异常
     */
    void deleteSystemConfig(SystemConfig systemConfig, User user) throws Exception;

    /**
     * 添加下拉菜单选项
     * @param systemConfig 下拉菜单选项
     * @throws Exception 异常
     */
    void addSystemConfig(SystemConfig systemConfig, User user) throws Exception ;

    /**
     * 获取下拉菜单选项列表--分页
     * @param systemConfigQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> querySystemConfigPageList(SystemConfigQuery systemConfigQuery) throws Exception;

    /**
     * 获取下拉菜单选项数量
     * @param systemConfigQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer querySystemConfigCount(SystemConfigQuery systemConfigQuery) throws Exception;

    /**
     * 获取下拉菜单选项列表
     * @param systemConfigQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> querySystemConfigList(SystemConfigQuery systemConfigQuery) throws Exception;

    /**
     * 获取下拉菜单类型列表
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> querySystemConfigCodeList() throws Exception;
    
}
