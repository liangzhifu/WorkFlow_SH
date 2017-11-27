package com.takata.system.role.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.role.domain.SystemRole;
import com.takata.system.role.query.SystemRoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemRoleDao extends SqlDao {

    /**
     * 新增角色
     * @param systemRole 角色实体信息
     * @return 返回结果
     */
    public Integer insertSystemRole(SystemRole systemRole) {
        return this.writerSqlSession.insert("SystemRoleMapper.insertSystemRole", systemRole);
    }

    /**
     * 修改角色信息
     * @param systemRole 角色实体信息
     * @return 返回结果
     */
    public Integer updateSystemRole(SystemRole systemRole) {
        return this.writerSqlSession.update("SystemRoleMapper.updateSystemRole", systemRole);
    }

    /**
     * 查询角色管理页面列表
     * @param systemRoleQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemRolePageList(SystemRoleQuery systemRoleQuery){
        return this.readSqlSession.selectList("SystemRoleMapper.selectSystemRolePageList", systemRoleQuery);
    }

    /**
     * 查询角色管理页面总数
     * @param systemRoleQuery 查询条件
     * @return 返回总数
     */
    public Integer selectSystemRoleCount(SystemRoleQuery systemRoleQuery){
        return this.readSqlSession.selectOne("SystemRoleMapper.selectSystemRoleCount", systemRoleQuery);
    }

}
