package com.takata.system.role.controller;

import com.takata.system.constant.Url;
import com.takata.system.role.domain.SystemRole;
import com.takata.system.role.domain.SystemRolePermission;
import com.takata.system.role.query.SystemRolePermissionQuery;
import com.takata.system.role.service.SystemRolePermissionService;
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
public class SystemRolePermissionController {
    private Logger logger = LogManager.getLogger(SystemRoleController.class.getName());

    @Resource(name = "systemRolePermissionService")
    private SystemRolePermissionService systemRolePermissionService;

    /**
     * 查询角色权限关联列表页面信息
     * @param systemRolePermissionQuery 角色权限关联列表页面查询条件
     * @return 返回角色权限关联分页列表信息和总数
     */
    @RequestMapping(value = Url.ROLEPERMISSION_QUERYPAGELIST)
    @ResponseBody
    private Object querySystemRolePermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.systemRolePermissionService.querySystemRolePermissionPageList(systemRolePermissionQuery);
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
     * 查询可以加入权限的列表
     * @param systemRolePermissionQuery 可以加入权限的列表查询条件
     * @return 返回可以加入权限的列表信息和总数
     */
    @RequestMapping(value = Url.ROLEPERMISSION_ADD_QUERYPAGELIST)
    @ResponseBody
    private Object queryAddPermissionPageList(SystemRolePermissionQuery systemRolePermissionQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.systemRolePermissionService.queryAddPermissionPageList(systemRolePermissionQuery);
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
     * 新增角色权限关联
     * @param roleId 角色ID
     * @param permissionIdStr 权限ID字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLEPERMISSION_ADD)
    @ResponseBody
    private Object addSystemRolePermission(Integer roleId, String permissionIdStr){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            String[] permissionIds = permissionIdStr.split(",");
            this.systemRolePermissionService.addSystemRolePermission(roleId, permissionIds);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
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
    private Object deleteSystemRolePermission(SystemRolePermission systemRolePermission){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            this.systemRolePermissionService.deleteSystemRolePermission(systemRolePermission);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
