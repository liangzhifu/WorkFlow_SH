package com.takata.system.org.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.org.domain.SystemOrg;
import com.takata.system.org.query.SystemOrgQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemOrgDao extends SqlDao {

    /**
     * 根据组织ID，查询组织
     * @param systemOrg 组织实体信息
     * @return 返回结果
     */
    public SystemOrg selectByPrimaryKey(SystemOrg systemOrg) {
        return this.readSqlSession.selectOne("SystemOrgMapper.selectByPrimaryKey", systemOrg);
    }

    /**
     * 新增组织
     * @param systemOrg 组织实体信息
     * @return 返回结果
     */
    public Integer insertSystemOrg(SystemOrg systemOrg) {
        return this.writerSqlSession.insert("SystemOrgMapper.insertSystemOrg", systemOrg);
    }

    /**
     * 修改组织信息
     * @param systemOrg 组织实体信息
     * @return 返回结果
     */
    public Integer updateSystemOrg(SystemOrg systemOrg) {
        return this.writerSqlSession.update("SystemOrgMapper.updateSystemOrg", systemOrg);
    }

    /**
     * 查询所有组织
     * @return 返回结果
     */
    public List<Map<String, Object>> selectAllSystemOrgList(){
        return this.readSqlSession.selectList("SystemOrgMapper.selectAllSystemOrgList");
    }

    /**
     * 查询组织管理页面列表
     * @param systemOrgQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemOrgList(SystemOrgQuery systemOrgQuery){
        return this.readSqlSession.selectList("SystemOrgMapper.selectSystemOrgList", systemOrgQuery);
    }

}
