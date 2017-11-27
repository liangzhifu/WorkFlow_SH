package com.takata.system.user.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.org.domain.SystemOrg;
import com.takata.system.user.dao.SystemUserOrgDao;
import com.takata.system.user.domain.SystemUser;
import com.takata.system.user.domain.SystemUserOrg;
import com.takata.system.user.query.SystemUserOrgQuery;
import com.takata.system.user.service.SystemUserOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemUserOrgService")
public class SystemUserOrgServiceImpl implements SystemUserOrgService {

    @Resource(name = "systemUserOrgDao")
    private SystemUserOrgDao systemUserOrgDao;

    @Override
    public void addSystemUserOrg(Integer userId, String[] orgIds) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        for (String orgId : orgIds){
            SystemUserOrg systemUserOrg = new SystemUserOrg();
            systemUserOrg.setUserId(userId);
            systemUserOrg.setOrgId(Integer.valueOf(orgId));
            systemUserOrg.setCreateBy(principal.getId());
            systemUserOrg.setCreateTime(new Date());
            systemUserOrg.setUpdateBy(principal.getId());
            systemUserOrg.setUpdateTime(new Date());
            systemUserOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            Integer count = this.systemUserOrgDao.insertSystemUserOrg(systemUserOrg);
            if (count != 1){
                throw new Exception("添加用户组织关联异常！");
            }
        }
    }

    @Override
    public void editSystemUserOrg(Integer userId, String[] orgIds) throws Exception {
        SystemUserOrgQuery systemUserOrgQuery = new SystemUserOrgQuery();
        systemUserOrgQuery.setUserId(userId);
        List<Map<String, Object>> mapList = this.querySystemUserOrgList(systemUserOrgQuery);
        for(Map<String, Object> map : mapList){
            SystemUserOrg systemUserOrg = new SystemUserOrg();
            systemUserOrg.setId(Integer.valueOf(map.get("id").toString()));
            this.deleteSystemUserOrg(systemUserOrg);
        }
        this.addSystemUserOrg(userId, orgIds);
    }

    @Override
    public Integer deleteSystemUserOrg(SystemUserOrg systemUserOrg) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemUserOrg.setUpdateBy(principal.getId());
        systemUserOrg.setUpdateTime(new Date());
        systemUserOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemUserOrgDao.updateSystemUserOrg(systemUserOrg);
        if (count != 1){
            throw new Exception("删除用户组织关联异常！");
        }
        return count;
    }

    @Override
    public void deleteSystemUserOrgByUser(SystemUserOrgQuery systemUserOrgQuery) throws Exception {
        List<Map<String, Object>> mapList = this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
        for (Map<String, Object> map : mapList){
            SystemUserOrg systemUserOrg = new SystemUserOrg();
            systemUserOrg.setId(Integer.valueOf(map.get("id").toString()));
            this.deleteSystemUserOrg(systemUserOrg);
        }
    }

    @Override
    public void deleteSystemUserOrgByOrg(SystemUserOrgQuery systemUserOrgQuery) throws Exception {
        List<Map<String, Object>> mapList = this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
        for (Map<String, Object> map : mapList){
            SystemUserOrg systemUserOrg = new SystemUserOrg();
            systemUserOrg.setId(Integer.valueOf(map.get("id").toString()));
            this.deleteSystemUserOrg(systemUserOrg);
        }
    }

    @Override
    public List<Map<String, Object>> querySystemUserOrgList(SystemUserOrgQuery systemUserOrgQuery) throws Exception {
        return this.systemUserOrgDao.selectSystemUserOrgList(systemUserOrgQuery);
    }

}
