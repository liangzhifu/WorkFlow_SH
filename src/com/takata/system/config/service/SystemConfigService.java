package com.takata.system.config.service;

import com.takata.system.config.domain.SystemConfig;
import com.takata.system.config.query.SystemConfigQuery;

import javax.xml.rpc.ServiceException;
import java.util.List;
import java.util.Map;

public interface SystemConfigService {

    /**
     * 删除下拉菜单选项
     * @param systemConfig 拉菜单选项
     * @throws Exception 异常
     */
    void deleteSystemConfig(SystemConfig systemConfig) throws Exception;

    /**
     * 添加下拉菜单选项
     * @param systemConfig 下拉菜单选项
     * @throws Exception 异常
     */
    void addSystemConfig(SystemConfig systemConfig) throws Exception ;

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
