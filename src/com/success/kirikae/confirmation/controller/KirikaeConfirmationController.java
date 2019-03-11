package com.success.kirikae.confirmation.controller;

import com.success.common.Constant;
import com.success.kirikae.confirmation.domain.KirikaeConfirmation;
import com.success.kirikae.confirmation.service.KirikaeConfirmationService;
import com.success.kirikae.constant.Url;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class KirikaeConfirmationController {

    @Resource(name = "kirikaeConfirmationService")
    private KirikaeConfirmationService kirikaeConfirmationService;

    /**
     * 获取切替单确认书页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_DIALOG)
    private String getDialog(){
        return "kirikae/confirmation/kirikaeConfirmation";
    }

    /**
     * 获取切替单确认书确认页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_CHECKED_DIALOG)
    private String getCheckedDialog(){
        return "kirikae/confirmation/kirikaeConfirmationChecked";
    }

    /**
     * 获取切替单确认书承认页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_APPROVED_DIALOG)
    private String getApprovedDialog(){
        return "kirikae/confirmation/kirikaeConfirmationApproved";
    }

    /**
     * 添加切替单的确认书
     * @param request 请求参数
     * @param kirikaeConfirmation 切替确认书实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_ADD, method = RequestMethod.POST)
    @ResponseBody
    private Object addKirikaeConfirmation(HttpServletRequest request, KirikaeConfirmation kirikaeConfirmation){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeConfirmationService.addKirikaeConfirmation(kirikaeConfirmation, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取切替单的确认书
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_GET)
    @ResponseBody
    private Object getKirikaeConfirmation(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            KirikaeConfirmation kirikaeConfirmation = this.kirikaeConfirmationService.getKirikaeConfirmationByOrderId(orderId);
            map.put("kirikaeConfirmation", kirikaeConfirmation);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 确认书确认
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_CHECKED)
    @ResponseBody
    private Object checkedKirikaeConfirmation(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeConfirmationService.editCheckedKirikaeConfirmation(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 确认书承认
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.CONFIRMATION_APPROVED)
    @ResponseBody
    private Object approvedKirikaeConfirmation(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeConfirmationService.editApprovedKirikaeConfirmation(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
