package com.success.alteration.order.controller;

import com.success.alteration.constant.Url;
import com.success.alteration.order.constant.AlterationOrderEnum;
import com.success.alteration.order.domain.AlterationOrder;
import com.success.alteration.order.service.AlterationOrderService;
import com.success.common.Constant;
import com.success.four.order.domain.FourOrderAttr;
import com.success.four.template.domain.FourTemplateAttr;
import com.success.four.template.service.FourTemplateAttrService;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class AlterationOrderController {

    @Resource(name = "alterationOrderService")
    private AlterationOrderService alterationOrderService;

    @Resource(name = "fourTemplateAttrService")
    private FourTemplateAttrService fourTemplateAttrService;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    /**
     * 获取变更单新增或修改页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_ADDOREDITDIALOG)
    private String getAddOrEditDialog(){
        return "alteration/alterationOrderAddOrEdit";
    }

    /**
     * 获取变更单详情页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_DETAILDIALOG)
    private String getDetailDialog(){
        return "alteration/alterationOrderDetail";
    }

    /**
     * 获取变更单待办页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_TODODIALOG)
    private String getTodoDialog(){
        return "alteration/todoList";
    }

    /**
     * 获取变更单
     * @param orderId 变更单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_GET)
    @ResponseBody
    private Object gerOrder(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            AlterationOrder alterationOrder = new AlterationOrder();
            alterationOrder.setId(orderId);
            alterationOrder = this.alterationOrderService.getAlterationOrder(alterationOrder);
            map.put("alterationOrder", alterationOrder);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增变更单
     * @param alterationOrder 变更单实体
     * @httpServletRequest 请求参数
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_ADD)
    @ResponseBody
    private Object addOrder(AlterationOrder alterationOrder, HttpServletRequest httpServletRequest){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)httpServletRequest.getSession().getAttribute(Constant.STAFF_KEY);
            this.alterationOrderService.addAlterationOrder(alterationOrder, user);
            //启动流程
            this.kirikaeOrderProcedureService.editStartFirstKirikaeOrderProcedure(alterationOrder.getId(), user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 修改变更单
     * @param alterationOrder 变更单实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_EDIT)
    @ResponseBody
    private Object editOrder(AlterationOrder alterationOrder, HttpServletRequest httpServletRequest){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)httpServletRequest.getSession().getAttribute(Constant.STAFF_KEY);
            String isNeedInitialization = httpServletRequest.getParameter("isNeedInitialization");
            if("1".equals(isNeedInitialization)){
                this.alterationOrderService.editCopyAlterationOrder(alterationOrder.getId(), user);
            }
            this.alterationOrderService.editAlterationOrder(alterationOrder, user);
            if("1".equals(isNeedInitialization)){
                //初始化切替单
                this.alterationOrderService.editInitAlterationOrder(alterationOrder.getId(), user);
                //启动流程
                this.kirikaeOrderProcedureService.editStartFirstKirikaeOrderProcedure(alterationOrder.getId(), user);
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 作废变更单
     * @param orderId 变更单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_TOVOID)
    @ResponseBody
    private Object toVoidOrder(HttpServletRequest httpServletRequest, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)httpServletRequest.getSession().getAttribute(Constant.STAFF_KEY);
            this.alterationOrderService.editAlterationOrderToVoid(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
