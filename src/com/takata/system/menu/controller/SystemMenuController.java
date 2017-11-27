package com.takata.system.menu.controller;

import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.system.constant.Url;
import com.takata.system.menu.query.SystemMenuQuery;
import com.takata.system.menu.service.SystemMenuService;
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
public class SystemMenuController {

    private Logger logger = LogManager.getLogger(SystemMenuController.class.getName());

    @Resource(name = "systemMenuService")
    private SystemMenuService systemMenuService;

    /**
     * 查询菜单的树型列表
     * @return 返回结果
     */
    @RequestMapping(value = Url.MENU_QUERYTREELIST)
    @ResponseBody
    private Object queryTreeList(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.systemMenuService.queryTreeMenuList();
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
     * 查询当前用户的模块
     * @return 返回结果
     */
    @RequestMapping(value = Url.MENU_QUERACTIVEUSERMODULE)
    @ResponseBody
    private Object queryActiveUserModule(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Principal principal = PrincipalUtils.getPrincipal();
            SystemMenuQuery systemMenuQuery = new SystemMenuQuery();
            systemMenuQuery.setUserId(principal.getId());
            List<Map<String, Object>> dataMapList = this.systemMenuService.queryUserModule(systemMenuQuery);
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
     * 查询当前用户模块下的菜单
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.MENU_QUERACTIVEUSERMENUBYMODULE)
    @ResponseBody
    private Object queryActiveUserMenuByModule(SystemMenuQuery systemMenuQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Principal principal = PrincipalUtils.getPrincipal();
            systemMenuQuery.setUserId(principal.getId());
            List<Map<String, Object>> dataMapList = this.systemMenuService.queryUserMenuByModule(systemMenuQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
