package com.success.system.user.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.user.domain.SystemUserRole;
import com.success.system.user.query.SystemUserRoleQuery;
import com.success.system.user.service.SystemUserRole2Service;
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
public class SystemUserRole2Controller {

    @Resource(name = "systemUserRole2Service")
    private SystemUserRole2Service systemUserRoleService;

    /**
     * 查询用户角色关联列表页面信息
     * @param systemUserRoleQuery 用户角色关联列表页面查询条件
     * @return 返回用户角色关联分页列表信息和总数
     */
    @RequestMapping(value = Url.USERROLE_QUERYLIST)
    @ResponseBody
    private Object querySystemUserRolePageList(SystemUserRoleQuery systemUserRoleQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<SystemUserRole> dataMapList = this.systemUserRoleService.listSystemUserRole(systemUserRoleQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增用户角色关联
     * @param userId 用户ID
     * @param roleIdStr 角色ID字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.USERROLE_ADD)
    @ResponseBody
    private Object addSystemUserRole(HttpServletRequest request, Integer userId, String roleIdStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] roleIds = roleIdStr.split(",");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserRoleService.addSystemUserRole(userId, roleIds, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除用户角色关联
     * @param systemUserRole 角色实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.USERROLE_DELETE)
    @ResponseBody
    private Object deleteSystemUserRole(HttpServletRequest request, SystemUserRole systemUserRole){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserRoleService.deleteSystemUserRole(systemUserRole, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
