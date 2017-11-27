package com.takata.system.config.service.impl;

import com.takata.common.constant.CommonEnum;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.config.dao.SystemConfigDao;
import com.takata.system.config.domain.SystemConfig;
import com.takata.system.config.query.SystemConfigQuery;
import com.takata.system.config.service.SystemConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Resource(name = "systemConfigDao")
    private SystemConfigDao systemConfigDao;

    @Override
    public void deleteSystemConfig(SystemConfig systemConfig) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemConfig.setUpdateBy(principal.getId());
        systemConfig.setUpdateTime(new Date());
        systemConfig.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemConfigDao.updateSystemConfig(systemConfig);
        if (count != 1){
            throw new Exception("删除下拉菜单选项异常！");
        }
    }

    @Override
    public void addSystemConfig(SystemConfig systemConfig) throws Exception {
        Principal principal = PrincipalUtils.getPrincipal();
        systemConfig.setCreateBy(principal.getId());
        systemConfig.setCreateTime(new Date());
        systemConfig.setUpdateBy(principal.getId());
        systemConfig.setUpdateTime(new Date());
        systemConfig.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        Integer count = this.systemConfigDao.insertSystemConfig(systemConfig);
        if (count != 1){
            throw new Exception("添加下拉菜单选项异常！");
        }
    }

    @Override
    public List<Map<String, Object>> querySystemConfigPageList(SystemConfigQuery systemConfigQuery) throws Exception {
        return this.systemConfigDao.selectSystemConfigPageList(systemConfigQuery);
    }

    @Override
    public Integer querySystemConfigCount(SystemConfigQuery systemConfigQuery) throws Exception {
        return this.systemConfigDao.selectSystemConfigCount(systemConfigQuery);
    }

    @Override
    public List<Map<String, Object>> querySystemConfigList(SystemConfigQuery systemConfigQuery) throws Exception {
        return this.systemConfigDao.selectSystemConfigList(systemConfigQuery);
    }

    @Override
    public List<Map<String, Object>> querySystemConfigCodeList() throws Exception {
        return this.systemConfigDao.selectSystemConfigCodeList();
    }
}
