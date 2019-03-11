package com.success.system.holiday.controller;

import com.success.system.constant.Url;
import com.success.system.holiday.domain.SystemHoliday;
import com.success.system.holiday.query.SystemHolidayQuery;
import com.success.system.holiday.service.SystemHoliday2Service;
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
public class SystemHoliday2Controller {

    @Resource(name = "systemHoliday2Service")
    private SystemHoliday2Service systemHolidayService;

    @RequestMapping(value = Url.HOLIDAY_DIALOG)
    public String getDialog() throws Exception{
        return "system/holiday/systemHolidayList";
    }

    /**
     * 获取节假日列表--分页
     * @param systemHolidayQuery 查询条件
     */
    @RequestMapping(value = Url.HOLIDAY_QUERYPAGELIST)
    @ResponseBody
    public Object getSystemHolidayPageList(SystemHolidayQuery systemHolidayQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<SystemHoliday> systemHolidayList = this.systemHolidayService.listSystemHolidayPage(systemHolidayQuery);
            Integer totalCount = this.systemHolidayService.countSystemHoliday(systemHolidayQuery);
            Integer totalPage = totalCount / systemHolidayQuery.getSize() + (totalCount % systemHolidayQuery.getSize() > 0 ? 1 : 0);
            map.put("dataMapList", systemHolidayList);
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
     * 新增节假日
     * @param systemHoliday 节假日
     */
    @RequestMapping(value = Url.HOLIDAY_ADD)
    @ResponseBody
    public Object addSystemHoliday(SystemHoliday systemHoliday){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            this.systemHolidayService.addSystemHoliday(systemHoliday);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除节假日
     * @param systemHoliday 节假日
     */
    @RequestMapping(value = Url.HOLIDAY_DELETE)
    @ResponseBody
    public Object deleteSystemHoliday(SystemHoliday systemHoliday){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            this.systemHolidayService.deleteSystemHoliday(systemHoliday);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
