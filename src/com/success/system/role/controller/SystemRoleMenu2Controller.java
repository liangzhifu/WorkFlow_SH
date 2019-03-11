package com.success.system.role.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.role.domain.SystemRoleMenu;
import com.success.system.role.query.SystemRoleMenuQuery;
import com.success.system.role.service.SystemRoleMenu2Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class SystemRoleMenu2Controller {

    @Resource(name = "systemRoleMenu2Service")
    private SystemRoleMenu2Service systemRoleMenuService;

    /**
     * 查询角色拥有的菜单权限
     * @param systemRoleMenurQuery 查询条件
     * @return 返回菜单权限列表
     */
    @RequestMapping(value = Url.ROLEMENU_QUERYROLEMENULIST)
    @ResponseBody
    private Object querySystemRoleMenuList(SystemRoleMenuQuery systemRoleMenurQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<SystemRoleMenu> systemRoleMenuList = this.systemRoleMenuService.listSystemRoleMenu(systemRoleMenurQuery);
            map.put("dataMapList", systemRoleMenuList);
            map.put("success", true);
        }catch (Exception e){
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
    private Object addSystemRoleMenu(HttpServletRequest request, Integer roleId, String menuIdStr){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            String[] menuIds = null;
            if(!(menuIdStr == null || "".equals(menuIdStr))){
                menuIds = menuIdStr.split(",");
            }
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemRoleMenuService.addSystemRoleMenu(roleId, menuIds, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}