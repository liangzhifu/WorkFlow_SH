package com.success.kirikae.order.controller;

import com.success.common.Constant;
import com.success.kirikae.constant.Url;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.query.KirikaeOrderQuery;
import com.success.kirikae.order.service.ExportKirikaeOrderPdfService;
import com.success.kirikae.order.service.KirikaeOrderService;
import com.success.sys.user.domain.User;
import com.success.task.detail.service.DetailService;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.ServletAPIUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lzf
 **/
@Controller
public class KirikaeOrderController {

    @Resource(name = "detailService")
    private DetailService detailService;

    @Resource(name = "kirikaeOrderService")
    private KirikaeOrderService kirikaeOrderService;

    @Resource(name = "exportKirikaeOrderPdfService")
    private ExportKirikaeOrderPdfService exportKirikaeOrderPdfService;

    /**
     * 获取切替单列表页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_PAGEINOF_DIALOG)
    private String getPageInfoDialog(){
        return "kirikae/order/kirikaeOrderList";
    }

    /**
     * 获取切替单确认切替日期页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_VALID_DESIGNCHANGETIMING_DIALOG)
    private String getDesignChangeTimingDialog(){
        return "kirikae/order/designChangeTiming";
    }

    /**
     * 获取切替单生成4M变化单页面
     * @param request 参数
     * @param model 参数
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_GENERATE_FORU_DIALOG)
    private String getGenerateFourDialog(HttpServletRequest request, Map<String, Object> model){
        User user = (User) request.getSession().getAttribute(Constant.STAFF_KEY);
        model.put("userId", user.getUserId());
        return "kirikae/order/generateFourOrder";
    }

    /**
     * 获取切替单确认切替日期页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_INSTRUCTION_CONFIRMATION_DIALOG)
    private String getInstructionConfirmationDialog(){
        return "kirikae/order/kirikaeInstructionConfirmation";
    }

    /**
     * 获取切替单列表页面信息
     * @param kirikaeOrderQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_PAGEINOF)
    @ResponseBody
    private Object getPageInfo(KirikaeOrderQuery kirikaeOrderQuery){
        Map<String, Object> map = new HashMap<String, Object>(4);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeOrderService.listKirikaeOrderPage(kirikaeOrderQuery);
            Integer totalCount = this.kirikaeOrderService.countKirikaeOrder(kirikaeOrderQuery);
            Integer totalPage = totalCount / kirikaeOrderQuery.getSize() + (totalCount % kirikaeOrderQuery.getSize() > 0 ? 1 : 0);
            map.put("dataMapList", dataMapList);
            map.put("totalCount", totalCount);
            map.put("totalPage", totalPage);
            map.put("success", true);
        } catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 设变屏幕展示
     * model 页面返回数据
     * @return 返回结果
     * @throws Exception 异常
     */
    @RequestMapping("/kirikae/order/getKirikaeOrderScreenShowDlg.do")
    public String getKirikaeOrderScreenShowDlg(Map<String, Object> model) throws Exception{
        List<Map<String, Object>> newMapList = new LinkedList<Map<String, Object>>();
        List<Map<String, Object>> mapList = this.kirikaeOrderService.listKirikaeOrderScreenShow();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = df.format(new Date());
        for (Map<String, Object> map : mapList){
            Date designChangeTiming = (Date) map.get("designChangeTiming");
            if (designChangeTiming != null) {
                String designChangeTimingStr = df.format(designChangeTiming);
                if (nowDateStr.compareTo(designChangeTimingStr) > 0) {
                    map.put("backgroundColor", "background-color : red;color: #FFFFFF;!important;");
                }
            }
            newMapList.add(map);
        }
        model.put("mapList", newMapList);
        return "kirikae/order/kirikaeOrderScreenShow";
    }

    /**
     * 获取切替单新增修改页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_ADD_DIALOG)
    private String getAddDialog(){
        return "kirikae/order/kirikaeOrderAddOrEdit";
    }

    /**
     * 新增变更单
     * @param request 参数
     * @param kirikaeOrder 变更单实体
     * @httpServletRequest 请求参数
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_ADD)
    @ResponseBody
    private Object addOrder(HttpServletRequest request, KirikaeOrder kirikaeOrder){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User) request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeOrderService.addKirikaeOrder(kirikaeOrder, user);
            map.put("success", true);
        } catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 修改变更单
     * @param request 参数
     * @param kirikaeOrder 变更单实体
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_EDIT)
    @ResponseBody
    private Object editOrder(HttpServletRequest request, KirikaeOrder kirikaeOrder){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User) request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeOrderService.editKirikaeOrder(kirikaeOrder, user);
            map.put("success", true);
        } catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 获取变更单
     * @param orderId 变更单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_DETAIL)
    @ResponseBody
    private Object gerOrderDetail(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            KirikaeOrder kirikaeOrder = new KirikaeOrder();
            kirikaeOrder = this.kirikaeOrderService.getKirikaeOrder(kirikaeOrder);
            map.put("kirikaeOrder", kirikaeOrder);
            map.put("success", true);
        } catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 验证切替单修改--是否可重走流程
     * @param orderId 变更单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_VALID_REPEAT)
    @ResponseBody
    private Object validOrderRepeat(Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            boolean flag = this.kirikaeOrderService.validOrderRepeat(orderId);
            map.put("valid", flag);
            map.put("success", true);
        } catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 确认切替日期
     * @param request 参数
     * @param orderId 切替单ID
     * @param designChangeTiming 切替时间
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_VALID_DESIGNCHANGETIMING)
    @ResponseBody
    private Object validOrderDesignChangeTiming(HttpServletRequest request, Integer orderId, String designChangeTiming){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User) request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeOrderService.editValidDesignChangeTiming(orderId, designChangeTiming, user);
            map.put("success", true);
        } catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 导出确认书
     * @param request 请求参数
     * @param orderId 订单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_EXPORT_CONFIRMBOOK)
    @ResponseBody
    public Object doExportConfirmBookPDF(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String path = request.getSession().getServletContext().getRealPath("/");
            String fileName = this.exportKirikaeOrderPdfService.exportConfirmBook(orderId, path);
            map.put("success", true);
            map.put("path", "/stdout/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 导出确认书
     * @param request 请求参数
     * @param orderId 订单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_EXPORT_HANDMATCH)
    @ResponseBody
    public Object doExportHandMatchPDF(HttpServletRequest request, Integer orderId){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String path = request.getSession().getServletContext().getRealPath("/");
            String fileName = this.exportKirikaeOrderPdfService.exportHandMatchAttachment(orderId, path);
            map.put("success", true);
            map.put("path", "/stdout/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 切替变更单生成4M单
     * @param request 请求条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORDER_GENERATE_FORU)
    @ResponseBody
    public Object generateFour(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Integer orderId = Integer.valueOf((String) request.getParameter("orderId"));
            User user = (User) request.getSession().getAttribute(Constant.STAFF_KEY);
            this.detailService.addTask(request, 1);
            this.kirikaeOrderService.editGenerateFourOrderProcedure(orderId, user);
            map.put("success", true);
        } catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
