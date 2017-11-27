package com.takata.system.role.controller;

import com.takata.system.constant.Url;
import com.takata.system.role.query.SystemRoleMenuQuery;
import com.takata.system.role.query.SystemRoleQuery;
import com.takata.system.role.service.SystemRoleMenuService;
import com.takata.system.role.service.SystemRoleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class SystemRoleMenuController {

    private Logger logger = LogManager.getLogger(SystemRoleController.class.getName());

    @Resource(name = "systemRoleMenuService")
    private SystemRoleMenuService systemRoleMenuService;

    /**
     * 查询角色拥有的菜单权限
     * @param systemRoleMenurQuery 查询条件
     * @return 返回菜单权限列表
     */
    @RequestMapping(value = Url.ROLEMENU_QUERYROLEMENULIST)
    @ResponseBody
    private Object querySystemRolePageList(SystemRoleMenuQuery systemRoleMenurQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.systemRoleMenuService.querySystemRoleMenuList(systemRoleMenurQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增角色菜单关联
     * @param roleId 角色ID
     * @param menuIdStr 菜单ID字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLEMENU_ADD)
    @ResponseBody
    private Object addSystemRolePermission(Integer roleId, String menuIdStr){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            String[] menuIds = null;
            if(!(menuIdStr == null || "".equals(menuIdStr))){
                menuIds = menuIdStr.split(",");
            }
            this.systemRoleMenuService.addSystemRoleMenu(roleId, menuIds);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}