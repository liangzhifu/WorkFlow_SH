package com.takata.system.org.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.org.dao.SystemOrgDao;
import com.takata.system.org.domain.SystemOrg;
import com.takata.system.org.query.SystemOrgQuery;
import com.takata.system.org.service.SystemOrgService;
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
@Service("systemOrgService")
public class SystemOrgServiceImpl implements SystemOrgService {

    @Resource(name = "systemOrgDao")
    private SystemOrgDao systemOrgDao;

    @Resource(name = "systemUserOrgService")
    private SystemUserOrgService systemUserOrgService;

    @Override
    public Integer addSystemOrg(SystemOrg systemOrg) throws Exception {
        SystemOrg parentOrg = new SystemOrg();
        parentOrg.setId(systemOrg.getParentId());
        parentOrg = this.systemOrgDao.selectByPrimaryKey(parentOrg);
        Principal principal = PrincipalUtils.getPrincipal();
        systemOrg.setCreateBy(principal.getId());
        systemOrg.setCreateTime(new Date());
        systemOrg.setUpdateBy(principal.getId());
        systemOrg.setUpdateTime(new Date());
        systemOrg.setCompanyId(parentOrg.getCompanyId());
        systemOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemOrgDao.insertSystemOrg(systemOrg);
        if (count != 1){
            throw new Exception("添加组织异常！");
        }
        systemOrg.setOrgPathCode(parentOrg.getOrgPathCode()+systemOrg.getId()+",");
        this.systemOrgDao.updateSystemOrg(systemOrg);
        return systemOrg.getId();
    }

    @Override
    public Integer editSystemOrg(SystemOrg systemOrg) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemOrg.setUpdateBy(principal.getId());
        systemOrg.setUpdateTime(new Date());
        Integer count = this.systemOrgDao.updateSystemOrg(systemOrg);
        if (count != 1){
            throw new Exception("修改组织异常！");
        }
        return count;
    }

    @Override
    public Integer deleteSystemOrg(SystemOrg systemOrg) throws Exception {
        systemOrg = this.systemOrgDao.selectByPrimaryKey(systemOrg);
        Principal principal = PrincipalUtils.getPrincipal();
        systemOrg.setUpdateBy(principal.getId());
        systemOrg.setUpdateTime(new Date());
        systemOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemOrgDao.updateSystemOrg(systemOrg);
        if (count != 1){
            throw new Exception("删除组织异常！");
        }

        //删除人员关联的组织
        SystemUserOrgQuery systemUserOrgQuery = new SystemUserOrgQuery();
        systemUserOrgQuery.setOrgId(systemOrg.getId());
        this.systemUserOrgService.deleteSystemUserOrgByOrg(systemUserOrgQuery);

        //删除子组织
        SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
        systemOrgQuery.setOrgPathCode(systemOrg.getOrgPathCode());
        List<Map<String, Object>> mapList = this.systemOrgDao.selectSystemOrgList(systemOrgQuery);
        for(Map<String, Object> map : mapList){
            SystemOrg tempSystemOrg = new SystemOrg();
            tempSystemOrg.setId(Integer.valueOf(map.get("id").toString()));
            tempSystemOrg.setUpdateBy(principal.getId());
            tempSystemOrg.setUpdateTime(new Date());
            tempSystemOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
            Integer tempCount = this.systemOrgDao.updateSystemOrg(tempSystemOrg);
            if (tempCount != 1){
                throw new Exception("删除组织异常！");
            }

            //删除人员关联的组织
            SystemUserOrgQuery tempSystemUserOrgQuery = new SystemUserOrgQuery();
            tempSystemUserOrgQuery.setOrgId(tempSystemOrg.getId());
            this.systemUserOrgService.deleteSystemUserOrgByOrg(tempSystemUserOrgQuery);
        }
        return count;
    }

    @Override
    public List<Map<String, Object>> queryCompanyOrgTree(SystemOrgQuery systemOrgQuery) throws Exception {
        return this.systemOrgDao.selectSystemOrgList(systemOrgQuery);
    }

    @Override
    public List<Map<String, Object>> queryAllSystemOrgList() throws Exception {
        return this.systemOrgDao.selectAllSystemOrgList();
    }
}
