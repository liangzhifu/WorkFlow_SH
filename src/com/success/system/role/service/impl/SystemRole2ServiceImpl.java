package com.success.system.role.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.role.dao.SystemRole2Dao;
import com.success.system.role.domain.SystemRole;
import com.success.system.role.query.SystemRoleQuery;
import com.success.system.role.service.SystemRoleMenu2Service;
import com.success.system.role.service.SystemRolePermission2Service;
import com.success.system.role.service.SystemRole2Service;
import com.success.system.user.query.SystemUserRoleQuery;
import com.success.system.user.service.SystemUserRole2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemRole2Service")
public class SystemRole2ServiceImpl implements SystemRole2Service {

    @Resource(name = "systemRole2Dao")
    private SystemRole2Dao systemRoleDao;

    @Resource(name = "systemUserRole2Service")
    private SystemUserRole2Service systemUserRoleService;

    @Resource(name = "systemRoleMenu2Service")
    private SystemRoleMenu2Service systemRoleMenuService;

    @Resource(name = "systemRolePermission2Service")
    private SystemRolePermission2Service systemRolePermissionService;

    @Override
    public Integer addSystemRole(SystemRole systemRole, User user) throws Exception {
        systemRole.setCreateBy(user.getUserId());
        systemRole.setCreateTime(new Date());
        systemRole.setUpdateBy(user.getUserId());
        systemRole.setUpdateTime(new Date());
        systemRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemRoleDao.insertSystemRole(systemRole);
        if (count != 1){
            throw new Exception("添加角色异常！");
        }
        return count;
    }

    @Override
    public Integer editSystemRole(SystemRole systemRole, User user) throws Exception {
        systemRole.setUpdateBy(user.getUserId());
        systemRole.setUpdateTime(new Date());
        Integer count = this.systemRoleDao.updateSystemRole(systemRole);
        if (count != 1){
            throw new Exception("修改角色异常！");
        }
        return count;
    }

    @Override
    public Integer deleteSystemRole(SystemRole systemRole, User user) throws Exception {
        systemRole.setUpdateBy(user.getUserId());
        systemRole.setUpdateTime(new Date());
        systemRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemRoleDao.updateSystemRole(systemRole);
        if (count != 1){
            throw new Exception("删除角色异常！");
        }

        //删除角色菜单关联
        this.systemRoleMenuService.deleteSystemRoleMenuByRole(systemRole.getId(), user);

        //删除角色权限关联
        this.systemRolePermissionService.deleteSystemPermissionByRole(systemRole.getId(), user);

        //删除用户角色关联
        this.systemUserRoleService.deleteSystemUserRoleByRole(systemRole.getId(), user);

        return count;
    }

    @Override
    public List<SystemRole> listSystemRolePage(SystemRoleQuery systemRoleQuery) throws Exception {
        return this.systemRoleDao.selectSystemRolePageList(systemRoleQuery);
    }

    @Override
    public Integer countSystemRole(SystemRoleQuery systemRoleQuery) throws Exception {
        return this.systemRoleDao.selectSystemRoleCount(systemRoleQuery);
    }

    @Override
    public List<SystemRole> listAddRole(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        return this.systemRoleDao.selectAddSystemRoleList(systemUserRoleQuery);
    }

}
