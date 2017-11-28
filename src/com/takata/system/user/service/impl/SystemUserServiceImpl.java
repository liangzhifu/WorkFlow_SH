package com.takata.system.user.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.user.dao.SqlServerDao;
import com.takata.system.user.dao.SystemUserDao;
import com.takata.system.user.domain.SystemUser;
import com.takata.system.user.query.SystemUserOrgQuery;
import com.takata.system.user.query.SystemUserQuery;
import com.takata.system.user.query.SystemUserRoleQuery;
import com.takata.system.user.service.SystemUserOrgService;
import com.takata.system.user.service.SystemUserRoleService;
import com.takata.system.user.service.SystemUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service(value = "systemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    @Resource(name = "systemUserDao")
    private SystemUserDao systemUserDao;

    @Resource(name = "sqlServerDao")
    private SqlServerDao sqlServerDao;

    @Resource(name = "systemUserOrgService")
    private SystemUserOrgService systemUserOrgService;

    @Resource(name = "systemUserRoleService")
    private SystemUserRoleService systemUserRoleService;

    @Override
    public Integer addSystemUser(SystemUser systemUser) throws Exception {
        //查看是否存在
        SystemUser oldSystemUser = this.querySystemUserByUserCode(systemUser);
        if(oldSystemUser != null){
            throw new Exception("添加用户异常！用户已存在！");
        }

        Map<String, Object> map = this.sqlServerDao.selectSqlServerUser(systemUser.getUserCode());
        if(map == null){
            throw new Exception("添加用户异常！用户在上海数据库中不存在！");
        }
        systemUser.setUserName((String)map.get("userName"));
        systemUser.setUserEmail((String)map.get("userEmail"));

        Principal principal = PrincipalUtils.getPrincipal();
        systemUser.setUserPassword("111111");
        systemUser.setCreateBy(principal.getId());
        systemUser.setCreateTime(new Date());
        systemUser.setUpdateBy(principal.getId());
        systemUser.setUpdateTime(new Date());
        systemUser.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemUserDao.insertSystemUser(systemUser);
        if (count != 1){
            throw new Exception("添加用户异常！");
        }
        return count;
    }

    @Override
    public Integer editSystemUser(SystemUser systemUser) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemUser.setUpdateBy(principal.getId());
        systemUser.setUpdateTime(new Date());
        Integer count = this.systemUserDao.updateSystemUser(systemUser);
        if (count != 1){
            throw new Exception("修改用户异常！");
        }
        return count;
    }

    @Override
    public Integer deleteSystemUser(SystemUser systemUser) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemUser.setUpdateBy(principal.getId());
        systemUser.setUpdateTime(new Date());
        systemUser.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemUserDao.updateSystemUser(systemUser);
        if (count != 1){
            throw new Exception("删除用户异常！");
        }
        //删除用户关联的角色
        SystemUserRoleQuery systemUserRoleQuery = new SystemUserRoleQuery();
        systemUserRoleQuery.setUserId(systemUser.getId());
        this.systemUserRoleService.deleteSystemUserRoleByUser(systemUserRoleQuery);

        //删除用户关联的组织
        SystemUserOrgQuery systemUserOrgQuery = new SystemUserOrgQuery();
        systemUserOrgQuery.setUserId(systemUser.getId());
        this.systemUserOrgService.deleteSystemUserOrgByOrg(systemUserOrgQuery);
        return count;
    }

    @Override
    public List<Map<String, Object>> querySystemUserPageList(SystemUserQuery systemUserQuery) throws Exception {
        return this.systemUserDao.selectSystemUserPageList(systemUserQuery);
    }

    @Override
    public List<Map<String, Object>> querySystemUserCompleteList(SystemUserQuery systemUserQuery) throws Exception {
        return this.systemUserDao.selectSystemUserCompleteList(systemUserQuery);
    }

    @Override
    public Integer querySystemUserCount(SystemUserQuery systemUserQuery) throws Exception {
        return this.systemUserDao.selectSystemUserCount(systemUserQuery);
    }

    @Override
    public boolean checkUserCode(SystemUser systemUser) throws Exception {
        systemUser = this.systemUserDao.selectSystemUserByUserCode(systemUser);
        if(systemUser == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public SystemUser querySystemUserByUserCode(SystemUser systemUser) {
        return this.systemUserDao.selectSystemUserByUserCode(systemUser);
    }

    @Override
    public void editUserPassworkd(Integer id, String oldPassword, String newPassword) throws Exception {
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

}
