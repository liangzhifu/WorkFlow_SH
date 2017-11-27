package com.takata.system.role.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.role.dao.SystemRoleDao;
import com.takata.system.role.domain.SystemRole;
import com.takata.system.role.query.SystemRoleMenuQuery;
import com.takata.system.role.query.SystemRolePermissionQuery;
import com.takata.system.role.query.SystemRoleQuery;
import com.takata.system.role.service.SystemRoleMenuService;
import com.takata.system.role.service.SystemRolePermissionService;
import com.takata.system.role.service.SystemRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {

    @Resource(name = "systemRoleDao")
    private SystemRoleDao systemRoleDao;

    @Resource(name = "systemRoleMenuService")
    private SystemRoleMenuService systemRoleMenuService;

    @Resource(name = "systemRolePermissionService")
    private SystemRolePermissionService systemRolePermissionService;

    @Override
    public Integer addSystemRole(SystemRole systemRole) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemRole.setCreateBy(principal.getId());
        systemRole.setCreateTime(new Date());
        systemRole.setUpdateBy(principal.getId());
        systemRole.setUpdateTime(new Date());
        systemRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemRoleDao.insertSystemRole(systemRole);
        if (count != 1){
            throw new Exception("添加角色异常！");
        }
        return count;
    }

    @Override
    public Integer editSystemRole(SystemRole systemRole) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemRole.setUpdateBy(principal.getId());
        systemRole.setUpdateTime(new Date());
        Integer count = this.systemRoleDao.updateSystemRole(systemRole);
        if (count != 1){
            throw new Exception("修改角色异常！");
        }
        return count;
    }

    @Override
    public Integer deleteSystemRole(SystemRole systemRole) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemRole.setUpdateBy(principal.getId());
        systemRole.setUpdateTime(new Date());
        systemRole.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemRoleDao.updateSystemRole(systemRole);
        if (count != 1){
            throw new Exception("删除角色异常！");
        }
        //删除角色权限
        SystemRoleMenuQuery systemRoleMenuQuery = new SystemRoleMenuQuery();
        systemRoleMenuQuery.setRoleId(systemRole.getId());
        this.systemRoleMenuService.deleteSystemRoleMenuByRole(systemRoleMenuQuery);

        //删除角色菜单
        SystemRolePermissionQuery systemRolePermissionQuery = new SystemRolePermissionQuery();
        systemRolePermissionQuery.setRoleId(systemRole.getId());
        this.systemRolePermissionService.deleteSystemPermissionByRole(systemRolePermissionQuery);

        return count;
    }

    @Override
    public List<Map<String, Object>> querySystemRolePageList(SystemRoleQuery systemRoleQuery) throws Exception {
        return this.systemRoleDao.selectSystemRolePageList(systemRoleQuery);
    }

    @Override
    public Integer querySystemRoleCount(SystemRoleQuery systemRoleQuery) throws Exception {
        return this.systemRoleDao.selectSystemRoleCount(systemRoleQuery);
    }

}
