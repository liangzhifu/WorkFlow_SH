package com.success.kirikae.order.controller;

import com.success.kirikae.constant.Url;
import com.success.kirikae.order.query.KirikaeOrderHistoryQuery;
import com.success.kirikae.order.service.KirikaeOrderHistoryService;
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
public class KirikaeOrderHistoryController {

    @Resource(name = "kirikaeOrderHistoryService")
    private KirikaeOrderHistoryService kirikaeOrderHistoryService;

    /**
     * 获取切替单历史列表页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.HISTORY_PAGEINOF_DIALOG)
    private String getPageInfoDialog(){
        return "kirikae/order/kirikaeOrderHistoryList";
    }


    /**
     * 获取切替单历史列表页面信息
     * @param kirikaeOrderHistoryQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.HISTORY_PAGEINOF)
    @ResponseBody
    private Object getPageInfo(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeOrderHistoryService.listKirikaeOrderHistoryPage(kirikaeOrderHistoryQuery);
            Integer totalCount = this.kirikaeOrderHistoryService.countKirikaeOrderHistory(kirikaeOrderHistoryQuery);
            Integer totalPage = totalCount / kirikaeOrderHistoryQuery.getSize() + (totalCount % kirikaeOrderHistoryQuery.getSize() > 0 ? 1 : 0);
            map.put("dataMapList", dataMapList);
            map.put("totalCount", totalCount);
            map.put("totalPage", totalPage);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
    
}
