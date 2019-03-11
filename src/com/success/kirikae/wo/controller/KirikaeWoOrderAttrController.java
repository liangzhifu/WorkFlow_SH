package com.success.kirikae.wo.controller;

import com.success.alteration.order.constant.AlterationOrderEnum;
import com.success.alteration.order.domain.AlterationOrder;
import com.success.alteration.order.service.AlterationOrderService;
import com.success.common.Constant;
import com.success.kirikae.constant.Url;
import com.success.kirikae.instruction.domain.KirikaeInstruction;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.kirikae.procedure.service.KirikaeOrderProcedureService;
import com.success.kirikae.wo.domain.KirikaeWoOrderAttr;
import com.success.kirikae.wo.domain.KirikaeWoOrderAttrOut;
import com.success.kirikae.wo.service.KirikaeWoOrderAttrService;
import com.success.kirikae.wo.service.KirikaeWoOrderService;
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
public class KirikaeWoOrderAttrController {

    @Resource(name = "alterationOrderService")
    private AlterationOrderService alterationOrderService;

    @Resource(name = "kirikaeWoOrderAttrService")
    private KirikaeWoOrderAttrService kirikaeWoOrderAttrService;

    @Resource(name = "kirikaeWoOrderService")
    private KirikaeWoOrderService kirikaeWoOrderService;

    @Resource(name = "kirikaeOrderProcedureService")
    private KirikaeOrderProcedureService kirikaeOrderProcedureService;

    @RequestMapping(value = Url.WOORDERATTR_ADD_DIALOG)
    private String getAddDialog(){
        return "kirikae/wo/woAttrAdd";
    }

    @RequestMapping(value = Url.WOORDERATTR_CONFIRM_DIALOG)
    private String getConfirmDialog(){
        return "kirikae/wo/woAttrConfirm";
    }

    @RequestMapping(value = Url.WOORDERATTR_REVIEW_DIALOG)
    private String getReviewDialog(){
        return "kirikae/wo/woAttrReview";
    }

    @RequestMapping(value = Url.WOORDERATTR_UPLOAD_DIALOG)
    private String getUploadDialog(){
        return "kirikae/wo/woAttrUpload";
    }

    @RequestMapping(value = Url.WOORDERATTR_STANDCLOSE_DIALOG)
    private String getStandCloseDialog(){
        return "kirikae/stand/kirikaeStandCloseResult";
    }

    @RequestMapping(value = Url.WOORDERATTR_STANDCLOSE_VALID_DIALOG)
    private String getStandCloseValidDialog(){
        return "kirikae/stand/kirikaeStandCloseValidResult";
    }

    @RequestMapping(value = Url.WOORDERATTR_CHILD_UPLOAD_DIALOG)
    private String getChildUploadDialog(){
        return "kirikae/wo/woAttrUploadChild";
    }

    /**
     * 获取查看切替单确认项目页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.WOORDERATTR_VIEW_DIALOG)
    private String getViewDialog(){
        return "kirikae/question/orderQuestionList";
    }

    @RequestMapping(value = Url.WOORDERATTR_LIST_ADD)
    @ResponseBody
    private Object getAddList(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<Map<String, Object>> dataMapList = this.kirikaeWoOrderAttrService.listKirikaeWoOrderAttrAdd(orderId, user.getUserId());
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_LIST_BYORDERID)
    @ResponseBody
    private Object getLisByOrderId(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeWoOrderAttrService.listKirikaeWoOrderAttrMapByOrderId(orderId);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_LIST_BYUSERID)
    @ResponseBody
    private Object getKirikaeInstruction(HttpServletRequest request, Integer orderId, String stateType){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<Map<String, Object>> dataMapList = this.kirikaeWoOrderAttrService.listKirikaeWoOrderAttrByUserId(orderId, user.getUserId(), stateType);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取立合列表
     * @param request 请求参数
     * @param orderId 订单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.WOORDERATTR_LIST_AGREEMENT)
    @ResponseBody
    private Object getAgeementList(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeWoOrderAttrService.listKirikaeAgreement(orderId);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_ADD)
    @ResponseBody
    private Object add(HttpServletRequest request, String woOrderIdStr, String questionIdStr, String preparedUserStr, String changeCompleteTimeStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Integer orderId = Integer.valueOf((String)request.getParameter("orderId"));
            String[] woOrderIds = woOrderIdStr.split(",");
            String[] questionIds = questionIdStr.split(",");
            String[] preparedUsers = preparedUserStr.split(",");
            String[] changeCompleteTimes = changeCompleteTimeStr.split(",");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeWoOrderAttrService.addKirikaeWoOrderAttr(woOrderIds,questionIds,preparedUsers,changeCompleteTimes,user);
            this.kirikaeWoOrderService.editWoOrderStateAuto(orderId);
            this.kirikaeOrderProcedureService.editWoOrderConfirm(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_CONFIRM)
    @ResponseBody
    private Object confirm(HttpServletRequest request, KirikaeWoOrderAttrOut kirikaeWoOrderAttrOut){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Integer orderId = Integer.valueOf((String)request.getParameter("orderId"));
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = kirikaeWoOrderAttrOut.getKirikaeWoOrderAttrList();
            this.kirikaeWoOrderAttrService.editKirikaeWoOrderAttrConfrim(kirikaeWoOrderAttrList, user);
            this.kirikaeWoOrderService.editWoOrderStateAuto(orderId);
            this.kirikaeOrderProcedureService.editWoOrderConfirm(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_REVIEW)
    @ResponseBody
    private Object review(HttpServletRequest request, KirikaeWoOrderAttrOut kirikaeWoOrderAttrOut){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Integer orderId = Integer.valueOf((String) request.getParameter("orderId"));
            String remark = (String) request.getParameter("remark");
            Integer fileId = Integer.valueOf((String) request.getParameter("fileId"));
            String spareColumn = (String) request.getParameter("spareColumn");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = kirikaeWoOrderAttrOut.getKirikaeWoOrderAttrList();
            this.kirikaeWoOrderAttrService.editKirikaeWoOrderAttrReview(kirikaeWoOrderAttrList, user);
            List<KirikaeOrderProcedure> kirikaeOrderProcedureList = kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
            if(kirikaeOrderProcedureList != null && kirikaeOrderProcedureList.size() > 0){
                for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                    if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_REVIEW.getCode().intValue()){
                        //判断评审结果
                        kirikaeOrderProcedure.setRemark(remark);
                        kirikaeOrderProcedure.setFileId(fileId);
                        kirikaeOrderProcedure.setSpareColumn(spareColumn);
                        this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                        //NG
                        if (ProcedureEnum.ProcedureReviewEnum.PROCEDURE_REVIEW_NG.getMsg().equals(spareColumn)) {
                            AlterationOrder alterationOrder = new AlterationOrder();
                            alterationOrder.setId(orderId);
                            alterationOrder.setOrderState(AlterationOrderEnum.OrderStateEnum.ORDER_STATE_COMPLETE.getCode());
                            this.alterationOrderService.updateAlterationOrder(alterationOrder);
                        }
                        break;
                    }
                }
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_UPLOAD)
    @ResponseBody
    private Object upload(HttpServletRequest request, KirikaeWoOrderAttrOut kirikaeWoOrderAttrOut){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Integer orderId = Integer.valueOf((String)request.getParameter("orderId"));
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = kirikaeWoOrderAttrOut.getKirikaeWoOrderAttrList();
            this.kirikaeWoOrderAttrService.editKirikaeWoOrderAttrUpload(kirikaeWoOrderAttrList, user);
            this.kirikaeOrderProcedureService.editWoOrderAttrUpload(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_STANDCLOSE)
    @ResponseBody
    private Object standClose(HttpServletRequest request, KirikaeWoOrderAttrOut kirikaeWoOrderAttrOut){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Integer orderId = Integer.valueOf((String)request.getParameter("orderId"));
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = kirikaeWoOrderAttrOut.getKirikaeWoOrderAttrList();
            this.kirikaeWoOrderAttrService.editKirikaeWoOrderAttrStandClose(kirikaeWoOrderAttrList, user);
            this.kirikaeOrderProcedureService.editWoOrderAttrStandClose(orderId, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_STANDCLOSE_VALID)
    @ResponseBody
    private Object standCloseValid(HttpServletRequest request, KirikaeWoOrderAttrOut kirikaeWoOrderAttrOut){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            Integer orderId = Integer.valueOf((String)request.getParameter("orderId"));
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            List<KirikaeWoOrderAttr> kirikaeWoOrderAttrList = kirikaeWoOrderAttrOut.getKirikaeWoOrderAttrList();
            this.kirikaeWoOrderAttrService.editKirikaeWoOrderAttrStandCloseValid(kirikaeWoOrderAttrList, user);
            List<KirikaeOrderProcedure> kirikaeOrderProcedureList = this.kirikaeOrderProcedureService.listKirikaeOrderProcedureByOrderId(orderId);
            for(KirikaeOrderProcedure kirikaeOrderProcedure : kirikaeOrderProcedureList){
                if(kirikaeOrderProcedure.getProcedureCode().intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_RESULT_VALID.getCode().intValue()){
                    this.kirikaeOrderProcedureService.editCompleteKirikaeOrderProcedure(kirikaeOrderProcedure, user);
                    break;
                }
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_REVIEW_ADD)
    @ResponseBody
    private Object reviewAdd(HttpServletRequest request, KirikaeWoOrderAttr kirikaeWoOrderAttr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeWoOrderAttrService.addKirikaeWoOrderAttr(kirikaeWoOrderAttr, user);
            map.put("id", kirikaeWoOrderAttr.getId());
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = Url.WOORDERATTR_REVIEW_DELETE)
    @ResponseBody
    private Object reviewDelete(HttpServletRequest request, Integer id){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeWoOrderAttrService.deleteKirikaeWoOrderAttr(id, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
