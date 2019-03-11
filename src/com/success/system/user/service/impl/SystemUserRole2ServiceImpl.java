package com.success.system.user.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.user.dao.SystemUserRole2Dao;
import com.success.system.user.domain.SystemUserRole;
import com.success.system.user.query.SystemUserRoleQuery;
import com.success.system.user.service.SystemUserRole2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemUserRole2Service")
public class SystemUserRole2ServiceImpl implements SystemUserRole2Service {

    @Resource(name = "systemUserRole2Dao")
    private SystemUserRole2Dao systemUserRoleDao;
    
    @Override
    public void addSystemUserRole(Integer userId, String[] roleIds, User user) throws Exception {
        for (String roleId : roleIds){
            SystemUserRole systemUserRole = new SystemUserRole();
            systemUserRole.setUserId(userId);
            systemUserRole.setRoleId(Integer.valueOf(roleId));
            systemUserRole.setCreateBy(user.getUserId());
            systemUserRole.setCreateTime(new Date());
            systemUserRole.setUpdateBy(user.getUserId());
            systemUserRole.setUpdateTime(new Date());
            systemUserRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            Integer count = this.systemUserRoleDao.insertSystemUserRole(systemUserRole);
            if (count != 1){
                throw new Exception("添加用户角色关联异常！");
            }
        }
    }

    @Override
    public void deleteSystemUserRole(SystemUserRole systemUserRole, User user) throws Exception {
        systemUserRole.setUpdateBy(user.getUserId());
        systemUserRole.setUpdateTime(new Date());
        systemUserRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemUserRoleDao.updateSystemUserRole(systemUserRole);
        if (count != 1){
            throw new Exception("删除用户角色关联异常！");
        }
    }

    @Override
    public void deleteSystemUserRoleByUser(Integer userId, User user) throws Exception {
        SystemUserRoleQuery systemUserRoleQuery = new SystemUserRoleQuery();
        systemUserRoleQuery.setUserId(userId);
        List<SystemUserRole> systemUserRoleList = this.listSystemUserRole(systemUserRoleQuery);
        for (SystemUserRole systemUserRole : systemUserRoleList){
            this.deleteSystemUserRole(systemUserRole, user);
        }
    }

    @Override
    public void deleteSystemUserRoleByRole(Integer roleId, User user) throws Exception {
        SystemUserRoleQuery systemUserRoleQuery = new SystemUserRoleQuery();
        systemUserRoleQuery.setRoleId(roleId);
        List<SystemUserRole> systemUserRoleList = this.listSystemUserRole(systemUserRoleQuery);
        for (SystemUserRole systemUserRole : systemUserRoleList){
            this.deleteSystemUserRole(systemUserRole, user);
        }
    }

    @Override
    public List<SystemUserRole> listSystemUserRole(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        return this.systemUserRoleDao.selectSystemUserRoleList(systemUserRoleQuery);
    }

}
