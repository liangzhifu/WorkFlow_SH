package com.takata.system.role.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.role.domain.SystemRolePermission;
import com.takata.system.role.query.SystemRolePermissionQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemRolePermissionDao extends SqlDao {

    /**
     * 新增角色权限关联
     * @param systemRolePermission 角色权限关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer insertSystemRolePermission(SystemRolePermission systemRolePermission) {
        return this.writerSqlSession.insert("SystemRolePermissionMapper.insertSystemRolePermission", systemRolePermission);
    }

    /**
     * 修改角色权限关联信息
     * @param systemRolePermission 角色权限关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateSystemRolePermission(SystemRolePermission systemRolePermission) {
        return this.writerSqlSession.update("SystemRolePermissionMapper.updateSystemRolePermission", systemRolePermission);
    }

    /**
     * 查询角色权限关联管理页面列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemRolePermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.readSqlSession.selectList("SystemRolePermissionMapper.selectSystemRolePermissionPageList", systemRolePermissionQuery);
    }

    /**
     * 查询角色权限关联管理页面总数
     * @param systemRolePermissionQuery 查询条件
     * @return 返回总数
     */
    public Integer selectSystemRolePermissionCount(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.readSqlSession.selectOne("SystemRolePermissionMapper.selectSystemRolePermissionCount", systemRolePermissionQuery);
    }

    /**
     * 查询未加入的权限管理页面列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectAddPermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.readSqlSession.selectList("SystemRolePermissionMapper.selectAddPermissionPageList", systemRolePermissionQuery);
    }

    /**
     * 查询未加入的权限总数
     * @param systemRolePermissionQuery 查询条件
     * @return 返回总数
     */
    public Integer selectAddPermissionCount(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.readSqlSession.selectOne("SystemRolePermissionMapper.selectAddPermissionCount", systemRolePermissionQuery);
    }

    /**
     * 查询角色权限关联列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemRolePermissionList(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.readSqlSession.selectList("SystemRolePermissionMapper.selectSystemRolePermissionList", systemRolePermissionQuery);
    }
    
}
