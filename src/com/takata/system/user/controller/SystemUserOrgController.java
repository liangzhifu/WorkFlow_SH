package com.takata.system.user.controller;

import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.constant.Url;
import com.takata.system.org.controller.SystemOrgController;
import com.takata.system.user.query.SystemUserOrgQuery;
import com.takata.system.user.service.SystemUserOrgService;
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
public class SystemUserOrgController {

    private Logger logger = LogManager.getLogger(SystemOrgController.class.getName());

    @Resource(name = "systemUserOrgService")
    private SystemUserOrgService systemUserOrgService;

    /**
     * 查询用户关联的组织
     * @param systemUserOrgQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.USERORG_QUERYUSERROLELIST)
    @ResponseBody
    private Object querySystemUserRoleList(SystemUserOrgQuery systemUserOrgQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.systemUserOrgService.querySystemUserOrgList(systemUserOrgQuery);
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
     * 修改用户的组织
     * @param userId 用户ID
     * @param orgIdStr 组织ID字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.USERORG_ADD)
    @ResponseBody
    private Object editSystemUserOrg(Integer userId, String orgIdStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] orgIds = null;
            if(!(orgIdStr == null || "".equals(orgIdStr))){
                orgIds = orgIdStr.split(",");
            }
            this.systemUserOrgService.editSystemUserOrg(userId, orgIds);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
