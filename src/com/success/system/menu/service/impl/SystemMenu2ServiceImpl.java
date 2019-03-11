package com.success.system.menu.service.impl;

import com.success.system.constant.CommonConstant;
import com.success.system.menu.dao.SystemMenu2Dao;
import com.success.system.menu.query.SystemMenuQuery;
import com.success.system.menu.service.SystemMenu2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemMenu2Service")
public class SystemMenu2ServiceImpl implements SystemMenu2Service {

    @Resource(name = "systemMenu2Dao")
    private SystemMenu2Dao systemMenuDao;

    @Override
    public List<Map<String, Object>> queryTreeMenuList() throws Exception {
        return this.systemMenuDao.selectTreeMenuList();
    }

    @Override
    public List<Map<String, Object>> queryUserModule(SystemMenuQuery systemMenuQuery) throws Exception {
        Integer userId = systemMenuQuery.getUserId();
        if(userId.intValue() == CommonConstant.ADMIN_USER_ID.intValue()){
            return this.systemMenuDao.selectAllModule();
        }else {
            return this.systemMenuDao.selectUserModule(systemMenuQuery);
        }
    }

    @Override
    public List<Map<String, Object>> queryUserMenuByModule(SystemMenuQuery systemMenuQuery) throws Exception {
        Integer userId = systemMenuQuery.getUserId();
        if(userId.intValue() == CommonConstant.ADMIN_USER_ID.intValue()){
            systemMenuQuery.setUserId(null);
        }
        return this.systemMenuDao.selectUserMenuByModule(systemMenuQuery);
    }
}
