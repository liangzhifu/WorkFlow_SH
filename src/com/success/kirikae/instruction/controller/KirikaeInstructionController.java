package com.success.kirikae.instruction.controller;

import com.success.common.Constant;
import com.success.kirikae.constant.Url;
import com.success.kirikae.instruction.domain.KirikaeInstruction;
import com.success.kirikae.instruction.service.KirikaeInstructionService;
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
public class KirikaeInstructionController {

    @Resource(name = "kirikaeInstructionService")
    private KirikaeInstructionService kirikaeInstructionService;

    /**
     * 获取切替单指示书页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_DIALOG)
    private String getDialog(){
        return "kirikae/instruction/kirikaeInstruction";
    }

    /**
     * 获取切替单指示书确认页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_CHECKED_DIALOG)
    private String getCheckedDialog(){
        return "kirikae/instruction/kirikaeInstructionChecked";
    }

    /**
     * 获取切替单指示书承认页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_APPROVED_DIALOG)
    private String getApprovedDialog(){
        return "kirikae/instruction/kirikaeInstructionApproved";
    }

    /**
     * 添加切替单的指示书
     * @param request 请求参数
     * @param kirikaeInstruction 切替指示书实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_ADD, method = RequestMethod.POST)
    @ResponseBody
    private Object addKirikaeInstruction(HttpServletRequest request, KirikaeInstruction kirikaeInstruction){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeInstructionService.addKirikaeInstruction(kirikaeInstruction, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取切替单的指示书
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_GET)
    @ResponseBody
    private Object getKirikaeInstruction(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            KirikaeInstruction kirikaeInstruction = this.kirikaeInstructionService.getKirikaeInstructionByOrderId(orderId);
            map.put("kirikaeInstruction", kirikaeInstruction);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 指示书确认
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_CHECKED)
    @ResponseBody
    private Object checkedKirikaeInstruction(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeInstructionService.editCheckedKirikaeInstruction(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 指示书承认
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.INSTRUCTION_APPROVED)
    @ResponseBody
    private Object approvedKirikaeInstruction(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeInstructionService.editApprovedKirikaeInstruction(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
