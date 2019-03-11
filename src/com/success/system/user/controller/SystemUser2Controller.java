package com.success.system.user.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.user.domain.SystemUser;
import com.success.system.user.query.SystemUserQuery;
import com.success.system.user.service.SystemUser2Service;
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
public class SystemUser2Controller {

    @Resource(name = "systemUser2Service")
    private SystemUser2Service systemUserService;

    /**
     * 返回用户管理页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_DIALOG)
    private String getDialog(){
        return "system2/user/systemUserList";
    }

    /**
     * 查询用户列表页面信息
     * @param systemUserQuery 用户列表页面查询条件
     * @return 返回用户分页列表信息和总数
     */
    @RequestMapping(value = Url.USER_QUERYPAGELIST)
    @ResponseBody
    private Object queryPageList(SystemUserQuery systemUserQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.systemUserService.listSystemUserPage(systemUserQuery);
            Integer totalCount = this.systemUserService.countSystemUser(systemUserQuery);
            Integer totalPage = totalCount / systemUserQuery.getSize() + (totalCount % systemUserQuery.getSize() > 0 ? 1 : 0);
            map.put("dataMapList", dataMapList);
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
     * 新增用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_ADD)
    @ResponseBody
    private Object addUser(HttpServletRequest request, SystemUser systemUser){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserService.addSystemUser(systemUser, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 修改用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_EDIT)
    @ResponseBody
    private Object editUser(HttpServletRequest request, SystemUser systemUser){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserService.editSystemUser(systemUser, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_DELETE)
    @ResponseBody
    private Object deleteUser(HttpServletRequest request, SystemUser systemUser){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserService.deleteSystemUser(systemUser, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 修改用户密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_EDITPASSWORD)
    @ResponseBody
    private Object editPassword(HttpServletRequest request, String oldPassword, String newPassword){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserService.editUserPassword(user.getUserId(), oldPassword, newPassword);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 查询所有用户
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_QUERYALLUSER)
    @ResponseBody
    private Object queryAllUser(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            SystemUserQuery systemUserQuery = new SystemUserQuery();
            List<SystemUser> dataMapList = this.systemUserService.listSystemUser(systemUserQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 验证当前用户权限
     * @param permissionId 权限ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_VALID_USER_PERMISSION)
    @ResponseBody
    private Object validSystemUserPermission(HttpServletRequest request, Integer permissionId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            boolean valid = this.systemUserService.validSystemUserPermission(user.getUserId(), permissionId);
            map.put("valid", valid);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
