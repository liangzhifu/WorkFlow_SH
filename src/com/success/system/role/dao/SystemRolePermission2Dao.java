package com.success.system.role.dao;

import com.success.system.role.domain.SystemRolePermission;
import com.success.system.role.query.SystemRolePermissionQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemRolePermission2Dao extends BaseDao {

    /**
     * 新增角色权限关联
     * @param systemRolePermission 角色权限关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer insertSystemRolePermission(SystemRolePermission systemRolePermission) {
        return this.sqlSession.insert("SystemRolePermissionMapper2.insertSystemRolePermission", systemRolePermission);
    }

    /**
     * 修改角色权限关联信息
     * @param systemRolePermission 角色权限关联实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateSystemRolePermission(SystemRolePermission systemRolePermission) {
        return this.sqlSession.update("SystemRolePermissionMapper2.updateSystemRolePermission", systemRolePermission);
    }

    /**
     * 查询角色权限关联列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     */
    public List<SystemRolePermission> selectSystemRolePermissionList(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.sqlSession.selectList("SystemRolePermissionMapper2.selectSystemRolePermissionList", systemRolePermissionQuery);
    }
    
}
