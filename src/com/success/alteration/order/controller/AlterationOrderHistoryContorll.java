package com.success.alteration.order.controller;

import com.success.alteration.constant.Url;
import com.success.alteration.order.domain.AlterationOrderHistory;
import com.success.alteration.order.service.AlterationOrderHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class AlterationOrderHistoryContorll {

    @Resource(name = "alterationOrderHistoryService")
    private AlterationOrderHistoryService alterationOrderHistoryService;

    /**
     * 获取变更单历史详情页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.HISTORY_DETAILDIALOG)
    private String getDetailDialog(){
        return "alteration/alterationOrderHistoryDetail";
    }

    /**
     * 获取变更单历史
     * @param orderId 变更单历史ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.HISTORY_GET)
    @ResponseBody
    private Object gerOrder(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            AlterationOrderHistory alterationOrderHistory = new AlterationOrderHistory();
            alterationOrderHistory.setId(orderId);
            alterationOrderHistory = this.alterationOrderHistoryService.getAlterationOrderHistory(alterationOrderHistory);
            map.put("alterationOrderHistory", alterationOrderHistory);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
