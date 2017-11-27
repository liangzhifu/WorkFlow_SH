package com.takata.system.role.controller;

import com.takata.system.constant.Url;
import com.takata.system.role.domain.SystemRole;
import com.takata.system.role.query.SystemRoleQuery;
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
public class SystemRoleController {

    private Logger logger = LogManager.getLogger(SystemRoleController.class.getName());

    @Resource(name = "systemRoleService")
    private SystemRoleService systemRoleService;

    /**
     * 返回用户管理页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_DIALOG)
    private String getDialog(){
        return "system/org/systemOrg";
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
            List<Map<String, Object>> dataMapList = this.systemRoleService.querySystemRolePageList(systemUserQuery);
            Integer totalCount = this.systemRoleService.querySystemRoleCount(systemUserQuery);
            Integer totalPage = totalCount / systemUserQuery.getSize() + (totalCount % systemUserQuery.getSize() > 0 ? 1 : 0);
            map.put("dataMapList", dataMapList);
            map.put("totalCount", totalCount);
            map.put("totalPage", totalPage);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
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
    private Object addSystemRole(SystemRole systemRole){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            this.systemRoleService.addSystemRole(systemRole);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
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
    private Object editSystemRole(SystemRole systemRole){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            this.systemRoleService.editSystemRole(systemRole);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
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
    private Object deleteSystemRole(SystemRole systemRole){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            this.systemRoleService.deleteSystemRole(systemRole);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
