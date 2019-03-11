package com.success.system.user.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.user.dao.SystemUserOrg2Dao;
import com.success.system.user.domain.SystemUserOrg;
import com.success.system.user.query.SystemUserOrgQuery;
import com.success.system.user.service.SystemUserOrg2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemUserOrg2Service")
public class SystemUserOrg2ServiceImpl implements SystemUserOrg2Service {

    @Resource(name = "systemUserOrg2Dao")
    private SystemUserOrg2Dao systemUserOrgDao;

    @Override
    public void addSystemUserOrg(Integer userId, String[] orgIds, User user) throws Exception {
        for (String orgId : orgIds){
            SystemUserOrg systemUserOrg = new SystemUserOrg();
            systemUserOrg.setUserId(userId);
            systemUserOrg.setOrgId(Integer.valueOf(orgId));
            systemUserOrg.setCreateBy(user.getUserId());
            systemUserOrg.setCreateTime(new Date());
            systemUserOrg.setUpdateBy(user.getUserId());
            systemUserOrg.setUpdateTime(new Date());
            systemUserOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            Integer count = this.systemUserOrgDao.insertSystemUserOrg(systemUserOrg);
            if (count != 1){
                throw new Exception("添加用户组织关联异常！");
            }
        }
    }

    @Override
    public void editSystemUserOrg(Integer userId, String[] orgIds, User user) throws Exception {
        SystemUserOrgQuery systemUserOrgQuery = new SystemUserOrgQuery();
        systemUserOrgQuery.setUserId(userId);
        List<SystemUserOrg> systemUserOrgList = this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
        for(SystemUserOrg systemUserOrg : systemUserOrgList){
            this.deleteSystemUserOrg(systemUserOrg, user);
        }
        this.addSystemUserOrg(userId, orgIds, user);
    }

    @Override
    public Integer deleteSystemUserOrg(SystemUserOrg systemUserOrg, User user) throws Exception {
        systemUserOrg.setUpdateBy(user.getUserId());
        systemUserOrg.setUpdateTime(new Date());
        systemUserOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemUserOrgDao.updateSystemUserOrg(systemUserOrg);
        if (count != 1){
            throw new Exception("删除用户组织关联异常！");
        }
        return count;
    }

    @Override
    public void deleteSystemUserOrgByUser(Integer userId, User user) throws Exception {
        SystemUserOrgQuery systemUserOrgQuery = new SystemUserOrgQuery();
        systemUserOrgQuery.setUserId(userId);
        List<SystemUserOrg> systemUserOrgList = this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
        for (SystemUserOrg systemUserOrg : systemUserOrgList){
            this.deleteSystemUserOrg(systemUserOrg, user);
        }
    }

    @Override
    public void deleteSystemUserOrgByOrg(Integer orgId, User user) throws Exception {
        SystemUserOrgQuery systemUserOrgQuery = new SystemUserOrgQuery();
        systemUserOrgQuery.setOrgId(orgId);
        List<SystemUserOrg> systemUserOrgList = this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
        for (SystemUserOrg systemUserOrg : systemUserOrgList){
            this.deleteSystemUserOrg(systemUserOrg, user);
        }
    }

    @Override
    public List<SystemUserOrg> listSystemUserOrg(SystemUserOrgQuery systemUserOrgQuery) throws Exception {
        return this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
    }

    @Override
    public List<Map<String, Object>> listUserOrg() throws Exception {
        return this.systemUserOrgDao.selectUserOrgList();
    }

}
