package com.success.system.role.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.role.dao.SystemRolePermission2Dao;
import com.success.system.role.domain.SystemRolePermission;
import com.success.system.role.query.SystemRolePermissionQuery;
import com.success.system.role.service.SystemRolePermission2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemRolePermission2Service")
public class SystemRolePermission2ServiceImpl implements SystemRolePermission2Service {

    @Resource(name = "systemRolePermission2Dao")
    private SystemRolePermission2Dao systemRolePermissionDao;

    @Override
    public void addSystemRolePermission(Integer roleId, String[] permissionIds, User user) throws Exception {
        SystemRolePermission systemRolePermission = new SystemRolePermission();
        systemRolePermission.setCreateBy(user.getUserId());
        systemRolePermission.setCreateTime(new Date());
        systemRolePermission.setUpdateBy(user.getUserId());
        systemRolePermission.setUpdateTime(new Date());
        systemRolePermission.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        for (String permissionId : permissionIds){
            systemRolePermission.setRoleId(roleId);
            systemRolePermission.setPermisssionId(Integer.valueOf(permissionId));
            systemRolePermission.setId(null);
            Integer count = this.systemRolePermissionDao.insertSystemRolePermission(systemRolePermission);
            if (count != 1){
                throw new Exception("添加角色权限关联异常！");
            }
        }
    }

    @Override
    public void deleteSystemRolePermission(SystemRolePermission systemRolePermission, User user) throws Exception {
        systemRolePermission.setUpdateBy(user.getUserId());
        systemRolePermission.setUpdateTime(new Date());
        systemRolePermission.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemRolePermissionDao.updateSystemRolePermission(systemRolePermission);
        if (count != 1){
            throw new Exception("删除角色权限关联异常！");
        }
    }

    @Override
    public void deleteSystemPermissionByRole(Integer roleId, User user) throws Exception {
        SystemRolePermissionQuery systemRolePermissionQuery = new SystemRolePermissionQuery();
        systemRolePermissionQuery.setRoleId(roleId);
        List<SystemRolePermission> systemRolePermissionList = this.systemRolePermissionDao.selectSystemRolePermissionList(systemRolePermissionQuery);
        for (SystemRolePermission systemRolePermission : systemRolePermissionList){
            this.deleteSystemRolePermission(systemRolePermission, user);
        }
    }

    @Override
    public List<SystemRolePermission> listSystemRolePermission(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        return this.systemRolePermissionDao.selectSystemRolePermissionList(systemRolePermissionQuery);
    }

}
