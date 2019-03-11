package com.success.system.permission.service.impl;

import com.success.system.permission.dao.SystemPermissionDao;
import com.success.system.permission.domain.SystemPermission;
import com.success.system.permission.service.SystemPermissionService;
import com.success.system.role.query.SystemRolePermissionQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemPermissionService")
public class SystemPermissionServiceImpl implements SystemPermissionService {

    @Resource(name = "systemPermissionDao")
    private SystemPermissionDao systemPermissionDao;

    @Override
    public List<SystemPermission> listAddSystemPermission(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        return this.systemPermissionDao.selectAddSystemPermissionList(systemRolePermissionQuery);
    }
}
