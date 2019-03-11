package com.success.system.org.controller;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.org.constant.SystemOrgEnum;
import com.success.system.org.domain.SystemOrg;
import com.success.system.org.query.SystemOrgQuery;
import com.success.system.org.service.SystemOrg2Service;
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
public class SystemOrg2Controller {

    @Resource(name = "systemOrg2Service")
    private SystemOrg2Service systemOrgService;

    /**
     * 返回组织管理页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_DIALOG)
    private String getDialog(){
        return "system2/org/systemOrg";
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
            SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
            List<SystemOrg> systemOrgList = this.systemOrgService.listSystemOrg(systemOrgQuery);
            map.put("dataMapList", systemOrgList);
            map.put("success", true);
        }catch (Exception e){
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
            SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
            List<SystemOrg> systemOrgList = this.systemOrgService.listSystemOrg(systemOrgQuery);
            map.put("dataMapList", systemOrgList);
            map.put("success", true);
        }catch (Exception e){
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
    private Object addSystemOrg(HttpServletRequest request, SystemOrg systemOrg){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemOrgService.addSystemOrg(systemOrg, user);
            map.put("success", true);
            map.put("id", systemOrg.getId());
        }catch (Exception e){
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
    private Object editSystemOrg(HttpServletRequest request, SystemOrg systemOrg){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemOrgService.editSystemOrg(systemOrg, user);
            map.put("success", true);
        }catch (Exception e){
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
    private Object deleteSystemOrg(HttpServletRequest request, SystemOrg systemOrg){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.systemOrgService.deleteSystemOrg(systemOrg, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 查询切替组织列表
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORG_KIRIKAELIIST)
    @ResponseBody
    private Object queryKirikaeList(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            SystemOrgQuery systemOrgQuery = new SystemOrgQuery();
            systemOrgQuery.setOrgType(SystemOrgEnum.OrgTypeEnum.ORG_TYPE_THREE.getCode());
            List<SystemOrg> systemOrgList = this.systemOrgService.listSystemOrg(systemOrgQuery);
            map.put("systemOrgKirikaeList", systemOrgList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
    
}
