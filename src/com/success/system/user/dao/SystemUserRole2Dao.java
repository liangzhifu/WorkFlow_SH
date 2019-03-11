package com.success.system.user.dao;

import com.success.system.user.domain.SystemUserRole;
import com.success.system.user.query.SystemUserRoleQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemUserRole2Dao extends BaseDao {

    /**
     * 新增用户角色关联
     * @param systemUserRole 用户角色关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer insertSystemUserRole(SystemUserRole systemUserRole) {
        return this.sqlSession.insert("SystemUserRoleMapper2.insertSystemUserRole", systemUserRole);
    }

    /**
     * 修改用户角色关联信息
     * @param systemUserRole 用户角色关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateSystemUserRole(SystemUserRole systemUserRole) {
        return this.sqlSession.update("SystemUserRoleMapper2.updateSystemUserRole", systemUserRole);
    }

    /**
     * 查询用户角色关联管理页面列表
     * @param systemUserRoleQuery 查询条件
     * @return 返回列表数据
     */
    public List<SystemUserRole> selectSystemUserRoleList(SystemUserRoleQuery systemUserRoleQuery){
        return this.sqlSession.selectList("SystemUserRoleMapper2.selectSystemUserRoleList", systemUserRoleQuery);
    }

}
