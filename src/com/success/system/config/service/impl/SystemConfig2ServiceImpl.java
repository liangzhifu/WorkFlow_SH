package com.success.system.config.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.sys.user.domain.User;
import com.success.system.config.dao.SystemConfig2Dao;
import com.success.system.config.domain.SystemConfig;
import com.success.system.config.query.SystemConfigQuery;
import com.success.system.config.service.SystemConfig2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemConfig2Service")
public class SystemConfig2ServiceImpl implements SystemConfig2Service {

    @Resource(name = "systemConfig2Dao")
    private SystemConfig2Dao systemConfigDao;

    @Override
    public void deleteSystemConfig(SystemConfig systemConfig, User user) throws Exception {
        systemConfig.setUpdateBy(user.getUserId());
        systemConfig.setUpdateTime(new Date());
        systemConfig.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.systemConfigDao.updateSystemConfig(systemConfig);
        if (count != 1){
            throw new Exception("删除下拉菜单选项异常！");
        }
    }

    @Override
    public void addSystemConfig(SystemConfig systemConfig, User user) throws Exception {
        systemConfig.setCreateBy(user.getUserId());
        systemConfig.setCreateTime(new Date());
        systemConfig.setUpdateBy(user.getUserId());
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
