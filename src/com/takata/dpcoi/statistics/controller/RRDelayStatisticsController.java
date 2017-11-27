package com.takata.dpcoi.statistics.controller;

import com.takata.dpcoi.statistics.query.RRDelayStatisticsQuery;
import com.takata.dpcoi.statistics.service.RRDelayStatisticsService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-20
 **/
@Controller
@RequestMapping("rrDelayStatistics")
public class RRDelayStatisticsController {

    @Resource(name = "rRDelayStatisticsService")
    private RRDelayStatisticsService rRDelayStatisticsService;

    @RequestMapping("getRRDelayStatisticsDlg.do")
    public String getRRDelayStatisticsDlg(){
        return "dpcoi/rrDelayStatistics";
    }

    /**
     * 获取RR问题延时统计数量
     * @param response 参数
     * @param rrDelayStatisticsQuery 查询条件
     */
    @RequestMapping("getStatisticsCount.do")
    @ResponseBody
    public Object getStatisticsCount(HttpServletResponse response, RRDelayStatisticsQuery rrDelayStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Integer statisticsCount = this.rRDelayStatisticsService.queryStatisticsCount(rrDelayStatisticsQuery);
            map.put("statisticsCount", statisticsCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取RR问题延时分组（按责任人分组）统计数量
     * @param response 参数
     * @param rrDelayStatisticsQuery 查询条件
     */
    @RequestMapping("getStatisticsList.do")
    @ResponseBody
    public Object getStatisticsList(HttpServletResponse response, RRDelayStatisticsQuery rrDelayStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> mapList = this.rRDelayStatisticsService.queryStatisticsList(rrDelayStatisticsQuery);
            map.put("mapList", mapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取RR问题延时统计数量
     * @param response 参数
     * @param rrDelayStatisticsQuery 查询条件
     */
    @RequestMapping("getRRDelayStatisticsList.do")
    @ResponseBody
    public Object getRRDelayStatisticsList(HttpServletResponse response, RRDelayStatisticsQuery rrDelayStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> mapList = this.rRDelayStatisticsService.queryRRDelayStatisticsList(rrDelayStatisticsQuery);
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
