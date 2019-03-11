package com.success.system.org.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.org.service.KirikaeOrgQuestionService;
import com.success.sys.user.domain.User;
import com.success.system.org.constant.SystemOrgEnum;
import com.success.system.org.dao.SystemOrg2Dao;
import com.success.system.org.domain.SystemOrg;
import com.success.system.org.query.SystemOrgQuery;
import com.success.system.org.service.SystemOrg2Service;
import com.success.system.user.service.SystemUserOrg2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemOrg2Service")
public class SystemOrg2ServiceImpl implements SystemOrg2Service {

    @Resource(name = "systemOrg2Dao")
    private SystemOrg2Dao systemOrgDao;

    @Resource(name = "systemUserOrg2Service")
    private SystemUserOrg2Service systemUserOrgService;

    @Resource(name = "kirikaeOrgQuestionService")
    private KirikaeOrgQuestionService kirikaeOrgQuestionService;

    @Override
    public void addSystemOrg(SystemOrg systemOrg, User user) throws Exception {
        SystemOrg parentOrg = new SystemOrg();
        parentOrg.setId(systemOrg.getParentId());
        parentOrg = this.systemOrgDao.selectByPrimaryKey(parentOrg);
        systemOrg.setCreateBy(user.getUserId());
        systemOrg.setCreateTime(new Date());
        systemOrg.setUpdateBy(user.getUserId());
        systemOrg.setUpdateTime(new Date());
        systemOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemOrgDao.insertSystemOrg(systemOrg);
        if (count != 1){
            throw new Exception("添加组织异常！");
        }
        systemOrg.setOrgPathCode(parentOrg.getOrgPathCode()+systemOrg.getId()+",");
        this.systemOrgDao.updateSystemOrg(systemOrg);
        Integer orgType = systemOrg.getOrgType();
        if (orgType.intValue() == SystemOrgEnum.OrgTypeEnum.ORG_TYPE_THREE.getCode()){
            //科室，自动添加对象外切替确认内容关联
            //对象外切替确认内容ID默认为1
            String[] questionIds = {"1"};
            this.kirikaeOrgQuestionService.addKirikaeOrgQuestion(systemOrg.getId(), questionIds, user);
        }
    }

    @Override
    public void editSystemOrg(SystemOrg systemOrg, User user) throws Exception {
        systemOrg.setUpdateBy(user.getUserId());
        systemOrg.setUpdateTime(new Date());
        Integer count = this.systemOrgDao.updateSystemOrg(systemOrg);
        if (count != 1){
            throw new Exception("修改组织异常！");
        }
    }

    @Override
    public void deleteSystemOrg(SystemOrg systemOrg, User user) throws Exception {
        systemOrg = this.systemOrgDao.selectByPrimaryKey(systemOrg);
        systemOrg.setUpdateBy(user.getUserId());
        systemOrg.setUpdateTime(new Date());
        systemOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());

        //删除组织--删除组织关联的切替确认内容
        Integer orgType = systemOrg.getOrgType();
        if (orgType.intValue() == SystemOrgEnum.OrgTypeEnum.ORG_TYPE_THREE.getCode()){
            this.kirikaeOrgQuestionService.deleteKirikaeOrgQuestionByOrg(systemOrg.getId(), user);
        }

        Integer count = this.systemOrgDao.updateSystemOrg(systemOrg);
        if (count != 1){
            throw new Exception("删除组织异常！");
        }

        //删除人员关联的组织
        this.systemUserOrgService.deleteSystemUserOrgByOrg(systemOrg.getId(), user);

        //删除子组织
        SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
        systemOrgQuery.setOrgPathCode(systemOrg.getOrgPathCode());
        List<SystemOrg> systemOrgList = this.systemOrgDao.selectSystemOrgList(systemOrgQuery);
        for(SystemOrg tempSystemOrg : systemOrgList){
            tempSystemOrg.setUpdateBy(user.getUserId());
            tempSystemOrg.setUpdateTime(new Date());
            tempSystemOrg.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());

            //删除组织--删除组织关联的切替确认内容
            orgType = tempSystemOrg.getOrgType();
            if (orgType.intValue() == SystemOrgEnum.OrgTypeEnum.ORG_TYPE_THREE.getCode()){
                this.kirikaeOrgQuestionService.deleteKirikaeOrgQuestionByOrg(tempSystemOrg.getId(), user);
            }

            Integer tempCount = this.systemOrgDao.updateSystemOrg(tempSystemOrg);
            if (tempCount != 1){
                throw new Exception("删除组织异常！");
            }

            //删除人员关联的组织
            this.systemUserOrgService.deleteSystemUserOrgByOrg(tempSystemOrg.getId(), user);
        }
    }

    @Override
    public List<SystemOrg> listSystemOrg(SystemOrgQuery systemOrgQuery) throws Exception {
        return this.systemOrgDao.selectSystemOrgList(systemOrgQuery);
    }

}
