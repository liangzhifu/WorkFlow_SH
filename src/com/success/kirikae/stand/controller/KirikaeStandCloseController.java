package com.success.kirikae.stand.controller;

import com.success.alteration.order.service.AlterationOrderService;
import com.success.common.Constant;
import com.success.kirikae.constant.Url;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.kirikae.stand.service.KirikaeStandCloseService;
import com.success.kirikae.wo.service.KirikaeWoOrderAttrService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class KirikaeStandCloseController {

    @Resource(name = "kirikaeStandCloseService")
    private KirikaeStandCloseService kirikaeStandCloseService;

    @Resource(name = "alterationOrderService")
    private AlterationOrderService alterationOrderService;

    @Resource(name = "kirikaeWoOrderAttrService")
    private KirikaeWoOrderAttrService kirikaeWoOrderAttrService;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    /**
     * 获取切替单立合人员填写页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_DIALOG)
    private String getDialog(){
        return "kirikae/stand/kirikaeStandClose";
    }

    /**
     * 获取立合结果确认页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_CHECKED_DIALOG)
    private String getCheckedDialog(){
        return "kirikae/stand/kirikaeStandCloseChecked";
    }

    /**
     * 获取立合结果承认页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_APPROVED_DIALOG)
    private String getApprovedDialog(){
        return "kirikae/stand/kirikaeStandCloseApproved";
    }

    /**
     * 添加切替单的立合结果
     * @param request 请求参数
     * @param orderId 切替单ID
     * @param orgIdStr 组织字符串
     * @param userNameStr 用户字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_ADD)
    @ResponseBody
    private Object addKirikaeStandClose(HttpServletRequest request, Integer orderId, String orgIdStr, String userNameStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] orgIds = orgIdStr.split(",");
            String[] userNames = userNameStr.split(":");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeStandCloseService.addKirikaeStandCloseList(orderId, orgIds, userNames, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 确认立合结果
     * @param request 请求参数
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_CHECKED)
    @ResponseBody
    private Object checkedKirikaeStandClose(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeStandCloseService.editCheckedStandClose(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 拒绝立合结果
     * @param request 请求参数
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_REFUSE)
    @ResponseBody
    private Object refuseKirikaeStandClose(HttpServletRequest request, Integer orderId, String woOrderIdstr, String refuseReason){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] woOrderIds = woOrderIdstr.split(",");
            this.kirikaeOrderProcedureService.editRefuseOrderProcedure(orderId);
            this.kirikaeWoOrderAttrService.editRefuseWoOrderAttr(woOrderIds, refuseReason);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 承认立合结果
     * @param request 请求参数
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.STAND_APPROVED)
    @ResponseBody
    private Object approvedKirikaeStandClose(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeStandCloseService.editApprovedStandClose(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
