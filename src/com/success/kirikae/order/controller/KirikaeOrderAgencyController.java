package com.success.kirikae.order.controller;

import com.success.common.Constant;
import com.success.kirikae.constant.Url;
import com.success.kirikae.order.query.KirikaeOrderAgencyQuery;
import com.success.kirikae.order.service.KirikaeOrderAgencyService;
import com.success.sys.user.domain.User;
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
public class KirikaeOrderAgencyController {

    @Resource(name = "kirikaeOrderAgencyService")
    private KirikaeOrderAgencyService kirikaeOrderAgencyService;

    /**
     * 获取切替单待办列表页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_AGENCY_DIALOG)
    private String getDialog(){
        return "kirikae/order/kirikaeOrderAgencyList";
    }


    /**
     * 获取切替单待办列表页面信息
     * @param kirikaeOrderAgencyQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_AGENCY_PAGEINOF)
    @ResponseBody
    private Object getPageInfo(HttpServletRequest httpServletRequest, KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            User user = (User)httpServletRequest.getSession().getAttribute(Constant.STAFF_KEY);
            kirikaeOrderAgencyQuery.setUserId(user.getUserId());
            List<Map<String, Object>> dataMapList = this.kirikaeOrderAgencyService.listKirikaeOrderAgencyPage(kirikaeOrderAgencyQuery);
            Integer totalCount = this.kirikaeOrderAgencyService.countKirikaeOrderAgency(kirikaeOrderAgencyQuery);
            Integer totalPage = totalCount / kirikaeOrderAgencyQuery.getSize() + (totalCount % kirikaeOrderAgencyQuery.getSize() > 0 ? 1 : 0);
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
