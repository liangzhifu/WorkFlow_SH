package com.success.system.user.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.user.dao.SystemUser2Dao;
import com.success.system.user.domain.SystemUser;
import com.success.system.user.query.SystemUserQuery;
import com.success.system.user.service.SystemUserOrg2Service;
import com.success.system.user.service.SystemUserRole2Service;
import com.success.system.user.service.SystemUser2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service(value = "systemUser2Service")
public class SystemUser2ServiceImpl implements SystemUser2Service {

    @Resource(name = "systemUser2Dao")
    private SystemUser2Dao systemUserDao;

    @Resource(name = "systemUserOrg2Service")
    private SystemUserOrg2Service systemUserOrgService;

    @Resource(name = "systemUserRole2Service")
    private SystemUserRole2Service systemUserRoleService;

    @Override
    public Integer addSystemUser(SystemUser systemUser, User user) throws Exception {
        //查看是否存在
        SystemUser oldSystemUser = this.getSystemUserByUserCode(systemUser);
        if(oldSystemUser != null){
            throw new Exception("添加用户异常！用户已存在！");
        }

        Map<String, Object> map = null;
        if(null == null){
            throw new Exception("添加用户异常！用户在上海数据库中不存在！");
        }
        systemUser.setUserName((String)map.get("userName"));
        systemUser.setUserEmail((String)map.get("userEmail"));

        systemUser.setUserPassword("111111");
        systemUser.setCreateBy(user.getUserId());
        systemUser.setCreateTime(new Date());
        systemUser.setUpdateBy(user.getUserId());
        systemUser.setUpdateTime(new Date());
        systemUser.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemUserDao.insertSystemUser(systemUser);
        if (count != 1){
            throw new Exception("添加用户异常！");
        }
        return count;
    }

    @Override
    public Integer editSystemUser(SystemUser systemUser, User user) throws Exception {
        systemUser.setUpdateBy(user.getUserId());
        systemUser.setUpdateTime(new Date());
        Integer count = this.systemUserDao.updateSystemUser(systemUser);
        if (count != 1){
            throw new Exception("修改用户异常！");
        }
        return count;
    }

    @Override
    public Integer deleteSystemUser(SystemUser systemUser, User user) throws Exception {
        systemUser.setUpdateBy(user.getUserId());
        systemUser.setUpdateTime(new Date());
        systemUser.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemUserDao.updateSystemUser(systemUser);
        if (count != 1){
            throw new Exception("删除用户异常！");
        }
        //删除用户关联的角色
        this.systemUserRoleService.deleteSystemUserRoleByUser(systemUser.getId(), user);

        //删除用户关联的组织
        this.systemUserOrgService.deleteSystemUserOrgByOrg(systemUser.getId(), user);
        return count;
    }

    @Override
    public List<Map<String, Object>> listSystemUserPage(SystemUserQuery systemUserQuery) throws Exception {
        return this.systemUserDao.selectSystemUserPageList(systemUserQuery);
    }

    @Override
    public Integer countSystemUser(SystemUserQuery systemUserQuery) throws Exception {
        return this.systemUserDao.selectSystemUserCount(systemUserQuery);
    }

    @Override
    public boolean checkUserCode(SystemUser systemUser) throws Exception {
        systemUser = this.systemUserDao.selectSystemUserByUserCode(systemUser);
        return systemUser != null;
    }

    @Override
    public SystemUser getSystemUserByUserCode(SystemUser systemUser) {
        return this.systemUserDao.selectSystemUserByUserCode(systemUser);
    }

    @Override
    public void editUserPassword(Integer id, String oldPassword, String newPassword) throws Exception {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(id);
        systemUser = this.systemUserDao.selectSystemUser(systemUser);
        if(oldPassword.equals(systemUser.getUserPassword())){
            systemUser.setUserPassword(newPassword);
            this.systemUserDao.updateSystemUser(systemUser);
        }else {
            throw new Exception("旧密码不对！");
        }
    }

    @Override
    public List<SystemUser> listSystemUser(SystemUserQuery systemUserQuery) throws Exception {
        return this.systemUserDao.selectSystemUserList(systemUserQuery);
    }

    @Override
    public List<SystemUser> listSystemUserByPermissionId(Integer permissionId) throws Exception {
        return null;
    }

    @Override
    public List<SystemUser> listSystemUserValidPermission(Integer[] userIds, Integer permissionId) throws Exception {
        return null;
    }

    @Override
    public boolean validSystemUserPermission(Integer userId, Integer permissionId) throws Exception {
        Integer count = this.systemUserDao.selectVerificationSystemUserByPermissionId(userId, permissionId);
        return count > 0;
    }

}
