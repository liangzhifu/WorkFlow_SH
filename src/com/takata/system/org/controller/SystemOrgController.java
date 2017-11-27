package com.takata.system.org.controller;

import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.constant.Url;
import com.takata.system.org.domain.SystemOrg;
import com.takata.system.org.query.SystemOrgQuery;
import com.takata.system.org.service.SystemOrgService;
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
public class SystemOrgController {

    private Logger logger = LogManager.getLogger(SystemOrgController.class.getName());

    @Resource(name = "systemOrgService")
    private SystemOrgService systemOrgService;

    /**
     * 返回角色管理页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ROLE_DIALOG)
    private String getDialog(){
        return "system/role/systemRoleList";
    }

    /**
     * 查询所有组织信息
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_QUERYAllLIST)
    @ResponseBody
    private Object queryAllSystemOrgList(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.systemOrgService.queryAllSystemOrgList();
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
     * 查询当前用户分公司下的所有组织信息
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_QUERYUSERORGTREE)
    @ResponseBody
    private Object queryUserOrgTree(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Principal principal = PrincipalUtils.getPrincipal();
            SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
            systemOrgQuery.setCompanyId(principal.getCompanyId());
            List<Map<String, Object>> dataMapList = this.systemOrgService.queryCompanyOrgTree(systemOrgQuery);
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
     * 新增组织
     * @param systemOrg 组织实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_ADD)
    @ResponseBody
    private Object addSystemOrg(SystemOrg systemOrg){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            Integer id = this.systemOrgService.addSystemOrg(systemOrg);
            map.put("success", true);
            map.put("id", id);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 修改组织
     * @param systemOrg 组织实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_EDIT)
    @ResponseBody
    private Object editSystemOrg(SystemOrg systemOrg){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            this.systemOrgService.editSystemOrg(systemOrg);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除组织
     * @param systemOrg 组织实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_DELETE)
    @ResponseBody
    private Object deleteSystemOrg(SystemOrg systemOrg){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            this.systemOrgService.deleteSystemOrg(systemOrg);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
    
}
