package com.takata.system.config.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.config.domain.SystemConfig;
import com.takata.system.config.query.SystemConfigQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemConfigDao extends SqlDao {

    /**
     * 插入一个下拉菜单选项
     * @param systemConfig 订单数据
     * @return 1成功，0不成功
     */
    public Integer insertSystemConfig(SystemConfig systemConfig){
        return this.writerSqlSession.insert("SystemConfigMapper.insertSelective", systemConfig);
    }

    /**
     * 更新一个下拉菜单选项
     * @param systemConfig 订单数据
     * @return 1成功，0不成功
     */
    public Integer updateSystemConfig(SystemConfig systemConfig){
        return this.writerSqlSession.delete("SystemConfigMapper.updateByPrimaryKeySelective", systemConfig);
    }


    /**
     * 获取下拉菜单选项数量
     * @param systemConfigQuery 查询条件
     * @return 返回结果
     */
    public Integer selectSystemConfigCount(SystemConfigQuery systemConfigQuery){
        return this.readSqlSession.selectOne("SystemConfigMapper.selectSystemConfigCount", systemConfigQuery);
    }

    /**
     * 获取下拉菜单选项列表--分页
     * @param systemConfigQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectSystemConfigPageList(SystemConfigQuery systemConfigQuery){
        return this.readSqlSession.selectList("SystemConfigMapper.selectSystemConfigPageList", systemConfigQuery);
    }

    /**
     * 查询下拉选项列表
     * @param systemConfigQuer 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectSystemConfigList(SystemConfigQuery systemConfigQuer){
        return this.readSqlSession.selectList("SystemConfigMapper.selectSystemConfigList", systemConfigQuer);
    }

    /**
     * 查询下拉菜单类型列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectSystemConfigCodeList(){
        return this.readSqlSession.selectList("SystemConfigMapper.selectSystemConfigCodeList");
    }
    
}
