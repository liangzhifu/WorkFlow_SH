package com.success.system.user.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.user.domain.SystemUserOrg;
import com.success.system.user.query.SystemUserOrgQuery;
import com.success.system.user.service.SystemUserOrg2Service;
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
public class SystemUserOrg2Controller {

    @Resource(name = "systemUserOrg2Service")
    private SystemUserOrg2Service systemUserOrgService;

    /**
     * 查询用户关联的组织
     * @param systemUserOrgQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.USERORG_QUERYUSERROLELIST)
    @ResponseBody
    private Object querySystemUserRoleList(SystemUserOrgQuery systemUserOrgQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<SystemUserOrg> systemUserOrgList = this.systemUserOrgService.listSystemUserOrg(systemUserOrgQuery);
            map.put("dataMapList", systemUserOrgList);
            map.put("success", true);
        }catch (Exception e){
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
    private Object editSystemUserOrg(HttpServletRequest request, Integer userId, String orgIdStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] orgIds = null;
            if(!(orgIdStr == null || "".equals(orgIdStr))){
                orgIds = orgIdStr.split(",");
            }
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemUserOrgService.editSystemUserOrg(userId, orgIds, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取用户组织关联列表
     * @param request 请求参数
     * @return 返回结果
     */
    @RequestMapping(value = Url.USER_ORG_LIST)
    @ResponseBody
    private Object getUserOrgList(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> mapList = this.systemUserOrgService.listUserOrg();
            map.put("mapList", mapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
