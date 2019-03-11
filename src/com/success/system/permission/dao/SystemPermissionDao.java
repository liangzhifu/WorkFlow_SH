package com.success.system.permission.dao;

import com.success.system.permission.domain.SystemPermission;
import com.success.system.role.query.SystemRolePermissionQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class SystemPermissionDao extends BaseDao {

    /**
     * 查询可以添加的权限列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回结果
     */
    public List<SystemPermission> selectAddSystemPermissionList(SystemRolePermissionQuery systemRolePermissionQuery){
        return this.sqlSession.selectList("SystemPermissionMapper.selectAddSystemPermissionList", systemRolePermissionQuery);
    }
}
