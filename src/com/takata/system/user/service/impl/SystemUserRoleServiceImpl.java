package com.takata.system.user.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.user.dao.SystemUserRoleDao;
import com.takata.system.user.domain.SystemUserRole;
import com.takata.system.user.query.SystemUserRoleQuery;
import com.takata.system.user.service.SystemUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemUserRoleService")
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

    @Resource(name = "systemUserRoleDao")
    private SystemUserRoleDao systemUserRoleDao;
    
    @Override
    public void addSystemUserRole(Integer userId, String[] roleIds) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        for (String roleId : roleIds){
            SystemUserRole systemUserRole = new SystemUserRole();
            systemUserRole.setUserId(userId);
            systemUserRole.setRoleId(Integer.valueOf(roleId));
            systemUserRole.setCreateBy(principal.getId());
            systemUserRole.setCreateTime(new Date());
            systemUserRole.setUpdateBy(principal.getId());
            systemUserRole.setUpdateTime(new Date());
            systemUserRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            Integer count = this.systemUserRoleDao.insertSystemUserRole(systemUserRole);
            if (count != 1){
                throw new Exception("添加用户角色关联异常！");
            }
        }
    }

    @Override
    public void editSystemUserRole(SystemUserRole systemUserRole) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemUserRole.setUpdateBy(principal.getId());
        systemUserRole.setUpdateTime(new Date());
        Integer count = this.systemUserRoleDao.updateSystemUserRole(systemUserRole);
        if (count != 1){
            throw new Exception("修改用户角色关联异常！");
        }
    }

    @Override
    public void deleteSystemUserRole(SystemUserRole systemUserRole) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemUserRole.setUpdateBy(principal.getId());
        systemUserRole.setUpdateTime(new Date());
        systemUserRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemUserRoleDao.updateSystemUserRole(systemUserRole);
        if (count != 1){
            throw new Exception("删除用户角色关联异常！");
        }
    }

    @Override
    public void deleteSystemUserRoleByUser(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        List<Map<String, Object>> mapList = this.querySystemUserRoleList(systemUserRoleQuery);
        for (Map<String, Object> map : mapList){
            SystemUserRole systemUserRole = new SystemUserRole();
            systemUserRole.setId(Integer.valueOf(map.get("id").toString()));
            this.deleteSystemUserRole(systemUserRole);
        }
    }

    @Override
    public List<Map<String, Object>> querySystemUserRolePageList(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        return this.systemUserRoleDao.selectSystemUserRolePageList(systemUserRoleQuery);
    }

    @Override
    public Integer querySystemUserRoleCount(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        return this.systemUserRoleDao.selectSystemUserRoleCount(systemUserRoleQuery);
    }

    @Override
    public List<Map<String, Object>> querySystemUserRoleList(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        return this.systemUserRoleDao.selectSystemUserRoleList(systemUserRoleQuery);
    }

    @Override
    public List<Map<String, Object>> queryAddRoleList(SystemUserRoleQuery systemUserRoleQuery) throws Exception {
        return this.systemUserRoleDao.selectAddRoleList(systemUserRoleQuery);
    }

}
