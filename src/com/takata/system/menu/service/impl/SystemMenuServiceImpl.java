package com.takata.system.menu.service.impl;

import com.takata.common.constant.CommonConstant;
import com.takata.system.menu.dao.SystemMenuDao;
import com.takata.system.menu.query.SystemMenuQuery;
import com.takata.system.menu.service.SystemMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemMenuService")
public class SystemMenuServiceImpl implements SystemMenuService {

    @Resource(name = "systemMenuDao")
    private SystemMenuDao systemMenuDao;

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
