package com.success.kirikae.procedure.controller;

import com.success.kirikae.constant.Url;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
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
public class KirikaeOrderProcedureController {

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    /**
     * 获取订单的所有流程
     * @param orderId 订单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.PROCEDURE_ORDER_LIST)
    @ResponseBody
    private Object getKirikaeOrderProcedureList(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<KirikaeOrderProcedure> dataMapList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
