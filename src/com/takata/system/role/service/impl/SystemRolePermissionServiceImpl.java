package com.takata.system.role.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.role.dao.SystemRolePermissionDao;
import com.takata.system.role.domain.SystemRolePermission;
import com.takata.system.role.query.SystemRolePermissionQuery;
import com.takata.system.role.service.SystemRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemRolePermissionService")
public class SystemRolePermissionServiceImpl implements SystemRolePermissionService {

    @Resource(name = "systemRolePermissionDao")
    private SystemRolePermissionDao systemRolePermissionDao;

    @Override
    public void addSystemRolePermission(Integer roleId, String[] permissionIds) throws Exception {
        SystemRolePermission systemRolePermission = new SystemRolePermission();
        Principal principal = PrincipalUtils.getPrincipal();
        systemRolePermission.setCreateBy(principal.getId());
        systemRolePermission.setCreateTime(new Date());
        systemRolePermission.setUpdateBy(principal.getId());
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
    public Integer editSystemRolePermission(SystemRolePermission systemRolePermission) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemRolePermission.setUpdateBy(principal.getId());
        systemRolePermission.setUpdateTime(new Date());
        Integer count = this.systemRolePermissionDao.updateSystemRolePermission(systemRolePermission);
        if (count != 1){
            throw new Exception("修改角色权限关联异常！");
        }
        return count;
    }

    @Override
    public void deleteSystemRolePermission(SystemRolePermission systemRolePermission) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemRolePermission.setUpdateBy(principal.getId());
        systemRolePermission.setUpdateTime(new Date());
        systemRolePermission.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemRolePermissionDao.updateSystemRolePermission(systemRolePermission);
        if (count != 1){
            throw new Exception("删除角色权限关联异常！");
        }
    }

    @Override
    public void deleteSystemPermissionByRole(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        List<Map<String, Object>> mapList = this.systemRolePermissionDao.selectSystemRolePermissionList(systemRolePermissionQuery);
        SystemRolePermission systemRolePermission = new SystemRolePermission();
        for (Map<String, Object> map : mapList){
            systemRolePermission.setId(Integer.valueOf(map.get("id").toString()));
            this.deleteSystemRolePermission(systemRolePermission);
        }
    }

    @Override
    public List<Map<String, Object>> querySystemRolePermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        return this.systemRolePermissionDao.selectSystemRolePermissionPageList(systemRolePermissionQuery);
    }

    @Override
    public Integer querySystemRolePermissionCount(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        return this.systemRolePermissionDao.selectSystemRolePermissionCount(systemRolePermissionQuery);
    }

    @Override
    public List<Map<String, Object>> queryAddPermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        return this.systemRolePermissionDao.selectAddPermissionPageList(systemRolePermissionQuery);
    }

    @Override
    public Integer queryAddPermissionCount(SystemRolePermissionQuery systemRolePermissionQuery) throws Exception {
        return this.systemRolePermissionDao.selectAddPermissionCount(systemRolePermissionQuery);
    }
}
