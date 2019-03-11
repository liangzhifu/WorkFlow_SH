package com.success.system.role.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.role.domain.SystemRolePermission;
import com.success.system.role.query.SystemRolePermissionQuery;
import com.success.system.role.service.SystemRolePermission2Service;
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
public class SystemRolePermission2Controller {

    @Resource(name = "systemRolePermission2Service")
    private SystemRolePermission2Service systemRolePermissionService;

    /**
     * 查询角色权限关联列表页面信息
     * @param systemRolePermissionQuery 角色权限关联列表页面查询条件
     * @return 返回角色权限关联分页列表信息和总数
     */
    @RequestMapping(value = Url.ROLEPERMISSION_QUERYLIST)
    @ResponseBody
    private Object querySystemRolePermissionList(SystemRolePermissionQuery systemRolePermissionQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<SystemRolePermission> systemRolePermissionList = this.systemRolePermissionService.listSystemRolePermission(systemRolePermissionQuery);
            map.put("dataMapList", systemRolePermissionList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增角色权限关联
     * @param roleId 角色ID
     * @param permissionIdStr 权限ID字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLEPERMISSION_ADD)
    @ResponseBody
    private Object addSystemRolePermission(HttpServletRequest request, Integer roleId, String permissionIdStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] permissionIds = permissionIdStr.split(",");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemRolePermissionService.addSystemRolePermission(roleId, permissionIds, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除角色权限关联
     * @param systemRolePermission 角色实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLEPERMISSION_DELETE)
    @ResponseBody
    private Object deleteSystemRolePermission(HttpServletRequest request, SystemRolePermission systemRolePermission){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemRolePermissionService.deleteSystemRolePermission(systemRolePermission, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
