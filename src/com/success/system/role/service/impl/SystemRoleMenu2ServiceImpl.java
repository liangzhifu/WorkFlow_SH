package com.success.system.role.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.role.dao.SystemRoleMenu2Dao;
import com.success.system.role.domain.SystemRoleMenu;
import com.success.system.role.query.SystemRoleMenuQuery;
import com.success.system.role.service.SystemRoleMenu2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemRoleMenu2Service")
public class SystemRoleMenu2ServiceImpl implements SystemRoleMenu2Service {

    @Resource(name = "systemRoleMenu2Dao")
    private SystemRoleMenu2Dao systemRoleMenuDao;

    @Override
    public void addSystemRoleMenu(Integer roleId, String[] menuIds, User user) throws Exception {
        //删除旧的角色菜单关联
        this.deleteSystemRoleMenuByRole(roleId, user);

        //增加新的角色菜单关联
        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setCreateBy(user.getUserId());
        systemRoleMenu.setCreateTime(new Date());
        systemRoleMenu.setUpdateBy(user.getUserId());
        systemRoleMenu.setUpdateTime(new Date());
        systemRoleMenu.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        for (String menuId : menuIds){
            systemRoleMenu.setRoleId(roleId);
            systemRoleMenu.setMenuId(Integer.valueOf(menuId));
            systemRoleMenu.setId(null);
            Integer count = this.systemRoleMenuDao.insertSystemRoleMenu(systemRoleMenu);
            if (count != 1){
                throw new Exception("添加角色权限关联异常！");
            }
        }
    }

    @Override
    public void deleteSystemRoleMenu(SystemRoleMenu systemRoleMenu, User user) throws Exception {
        systemRoleMenu.setUpdateBy(user.getUserId());
        systemRoleMenu.setUpdateTime(new Date());
        systemRoleMenu.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemRoleMenuDao.updateSystemRoleMenu(systemRoleMenu);
        if (count != 1){
            throw new Exception("删除角色权限关联异常！");
        }
    }

    @Override
    public void deleteSystemRoleMenuByRole(Integer roleId, User user) throws Exception {
        SystemRoleMenuQuery systemRoleMenuQuery = new SystemRoleMenuQuery();
        systemRoleMenuQuery.setRoleId(roleId);
        List<SystemRoleMenu> systemRoleMenuList = this.listSystemRoleMenu(systemRoleMenuQuery);
        for (SystemRoleMenu systemRoleMenu : systemRoleMenuList){
            this.deleteSystemRoleMenu(systemRoleMenu, user);
        }
    }

    @Override
    public void deleteSystemRoleMenuByMenu(Integer menuId, User user) throws Exception {
        SystemRoleMenuQuery systemRoleMenuQuery = new SystemRoleMenuQuery();
        systemRoleMenuQuery.setMenuId(menuId);
        List<SystemRoleMenu> systemRoleMenuList = this.listSystemRoleMenu(systemRoleMenuQuery);
        for (SystemRoleMenu systemRoleMenu : systemRoleMenuList){
            this.deleteSystemRoleMenu(systemRoleMenu, user);
        }
    }

    @Override
    public List<SystemRoleMenu> listSystemRoleMenu(SystemRoleMenuQuery systemRoleMenuQuery) throws Exception {
        return this.systemRoleMenuDao.selectSystemRoleMenuList(systemRoleMenuQuery);
    }
}
