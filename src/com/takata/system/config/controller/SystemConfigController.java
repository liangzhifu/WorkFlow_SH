package com.takata.system.config.controller;

import com.takata.system.config.domain.SystemConfig;
import com.takata.system.config.query.SystemConfigQuery;
import com.takata.system.config.service.SystemConfigService;
import com.takata.system.constant.Url;
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
public class SystemConfigController {

    private Logger logger = LogManager.getLogger(SystemConfigController.class.getName());

    @Resource(name = "systemConfigService")
    private SystemConfigService systemConfigService;

    @RequestMapping(value = Url.CONFIG_DIALOG)
    public String getDialog() throws Exception{
        return "system/config/systemConfigList";
    }

    /**
     * 获取下拉菜单选项列表--分页
     * @param systemConfigQuery 查询条件
     */
    @RequestMapping(value = Url.CONFIG_QUERYPAGELIST)
    @ResponseBody
    public Object getSystemConfigPageList(SystemConfigQuery systemConfigQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.systemConfigService.querySystemConfigPageList(systemConfigQuery);
            Integer totalCount = this.systemConfigService.querySystemConfigCount(systemConfigQuery);
            Integer totalPage = totalCount / systemConfigQuery.getSize() + (totalCount % systemConfigQuery.getSize() > 0 ? 1 : 0);
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
     * 新增下拉菜单选项
     * @param systemConfig 下拉菜单选项
     */
    @RequestMapping(value = Url.CONFIG_ADD)
    @ResponseBody
    public Object addSystemConfig(SystemConfig systemConfig){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            this.systemConfigService.addSystemConfig(systemConfig);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除下拉菜单选项
     * @param systemConfig 下拉菜单选项
     */
    @RequestMapping(value = Url.CONFIG_DELETE)
    @ResponseBody
    public Object deleteSystemConfig(SystemConfig systemConfig){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            this.systemConfigService.deleteSystemConfig(systemConfig);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取下拉菜单选项列表
     * @param systemConfigQuery 查询条件
     */
    @RequestMapping(value = Url.CONFIG_QUERYLIST)
    @ResponseBody
    public Object getSystemConfigList(SystemConfigQuery systemConfigQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.systemConfigService.querySystemConfigList(systemConfigQuery);
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
     * 获取下拉菜单类型列表
     */
    @RequestMapping(value = Url.CONFIG_QUERYCODELIST)
    @ResponseBody
    public Object getSystemConfigCodeList(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.systemConfigService.querySystemConfigCodeList();
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
