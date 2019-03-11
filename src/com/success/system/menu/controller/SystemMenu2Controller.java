package com.success.system.menu.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.menu.query.SystemMenuQuery;
import com.success.system.menu.service.SystemMenu2Service;
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
public class SystemMenu2Controller {

    @Resource(name = "systemMenu2Service")
    private SystemMenu2Service systemMenuService;

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
    private Object queryActiveUserModule(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            SystemMenuQuery systemMenuQuery = new SystemMenuQuery();
            systemMenuQuery.setUserId(user.getUserId());
            List<Map<String, Object>> dataMapList = this.systemMenuService.queryUserModule(systemMenuQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
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
    private Object queryActiveUserMenuByModule(HttpServletRequest request, SystemMenuQuery systemMenuQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            systemMenuQuery.setUserId(user.getUserId());
            List<Map<String, Object>> dataMapList = this.systemMenuService.queryUserMenuByModule(systemMenuQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
