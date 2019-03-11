package com.success.system.role.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.role.domain.SystemRole;
import com.success.system.role.query.SystemRoleQuery;
import com.success.system.role.service.SystemRole2Service;
import com.success.system.user.query.SystemUserRoleQuery;
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
public class SystemRole2Controller {

    @Resource(name = "systemRole2Service")
    private SystemRole2Service systemRoleService;

    /**
     * 返回角色管理页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLE_DIALOG)
    private String getDialog(){
        return "system2/role/systemRoleList";
    }

    /**
     * 查询角色列表页面信息
     * @param systemUserQuery 角色列表页面查询条件
     * @return 返回角色分页列表信息和总数
     */
    @RequestMapping(value = Url.ROLE_QUERYPAGELIST)
    @ResponseBody
    private Object queryPageList(SystemRoleQuery systemUserQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<SystemRole> systemRoleList = this.systemRoleService.listSystemRolePage(systemUserQuery);
            Integer totalCount = this.systemRoleService.countSystemRole(systemUserQuery);
            Integer totalPage = totalCount / systemUserQuery.getSize() + (totalCount % systemUserQuery.getSize() > 0 ? 1 : 0);
            map.put("dataMapList", systemRoleList);
            map.put("totalCount", totalCount);
            map.put("totalPage", totalPage);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增角色
     * @param systemRole 角色实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLE_ADD)
    @ResponseBody
    private Object addSystemRole(HttpServletRequest request, SystemRole systemRole){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemRoleService.addSystemRole(systemRole, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 修改角色
     * @param systemRole 角色实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLE_EDIT)
    @ResponseBody
    private Object editSystemRole(HttpServletRequest request, SystemRole systemRole){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemRoleService.editSystemRole(systemRole, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除角色
     * @param systemRole 角色实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLE_DELETE)
    @ResponseBody
    private Object deleteSystemRole(HttpServletRequest request, SystemRole systemRole){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemRoleService.deleteSystemRole(systemRole, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 查询可以加入权限的列表
     * @param systemUserRoleQuery 可以加入权限的列表查询条件
     * @return 返回可以加入权限的列表信息和总数
     */
    @RequestMapping(value = Url.ROLE_ADDLIST)
    @ResponseBody
    private Object queryAddSystemRoleList(SystemUserRoleQuery systemUserRoleQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<SystemRole> systemRoleList = this.systemRoleService.listAddRole(systemUserRoleQuery);
            map.put("dataMapList", systemRoleList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
