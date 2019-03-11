package com.success.system.permission.service;

import com.success.system.permission.domain.SystemPermission;
import com.success.system.role.query.SystemRolePermissionQuery;

import java.util.List;

public interface SystemPermissionService {

    /**
     * 查询可以关联的权限列表
     * @param systemRolePermissionQuery 查询条件
     * @return 返回列表数据
     * @throws Exception 异常
     */
    List<SystemPermission> listAddSystemPermission(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception;

}
