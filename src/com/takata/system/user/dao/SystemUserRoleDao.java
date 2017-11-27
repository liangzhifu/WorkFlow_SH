package com.takata.system.user.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.user.domain.SystemUserRole;
import com.takata.system.user.query.SystemUserRoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemUserRoleDao extends SqlDao {

    /**
     * 新增用户角色关联
     * @param systemUserRole 用户角色关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer insertSystemUserRole(SystemUserRole systemUserRole) {
        return this.writerSqlSession.insert("SystemUserRoleMapper.insertSystemUserRole", systemUserRole);
    }

    /**
     * 修改用户角色关联信息
     * @param systemUserRole 用户角色关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateSystemUserRole(SystemUserRole systemUserRole) {
        return this.writerSqlSession.update("SystemUserRoleMapper.updateSystemUserRole", systemUserRole);
    }

    /**
     * 查询用户角色关联管理页面列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemUserRolePageList(SystemUserRoleQuery systemUserRoleQuery){
        return this.readSqlSession.selectList("", systemUserRoleQuery);
    }

    /**
     * 查询用户角色关联管理页面总数
     * @param systemUserRoleQuery 查询条件
     * @return 返回总数
     */
    public Integer selectSystemUserRoleCount(SystemUserRoleQuery systemUserRoleQuery){
        return this.readSqlSession.selectOne("", systemUserRoleQuery);
    }

    /**
     * 查询用户角色关联管理页面列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemUserRoleList(SystemUserRoleQuery systemUserRoleQuery){
        return this.readSqlSession.selectList("SystemUserRoleMapper.selectSystemUserRoleList", systemUserRoleQuery);
    }

    /**
     * 查询可以加入的角色
     * @param systemUserRoleQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectAddRoleList(SystemUserRoleQuery systemUserRoleQuery){
        return this.readSqlSession.selectList("SystemUserRoleMapper.selectAddRoleList", systemUserRoleQuery);
    }
}
