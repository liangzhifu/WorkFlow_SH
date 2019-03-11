package com.success.kirikae.org.controller;

import com.success.common.Constant;
import com.success.kirikae.constant.Url;
import com.success.kirikae.org.domain.KirikaeOrgQuestion;
import com.success.kirikae.org.query.KirikaeOrgQuestionQuery;
import com.success.kirikae.org.service.KirikaeOrgQuestionService;
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
public class KirikaeOrgQuestionController {

    @Resource(name = "kirikaeOrgQuestionService")
    private KirikaeOrgQuestionService kirikaeOrgQuestionService;

    /**
     * 返回组织确认内容管理页面
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORGQUESTION_DIALOG)
    private String getDialog(){
        return "kirikae/org/kirikaeOrgQuestionList";
    }

    /**
     * 查询组织确认内容关联列表
     * @param kirikaeOrgQuestionQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORGQUESTION_LIST)
    @ResponseBody
    private Object queryList(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeOrgQuestionService.listKirikaeOrgQuestion(kirikaeOrgQuestionQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除组织确认内容关联
     * @param kirikaeOrgQuestion 组织确认内容关联实体信息
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORGQUESTION_DELETE)
    @ResponseBody
    private Object deleteKirikaeOrgQuestion(HttpServletRequest request, KirikaeOrgQuestion kirikaeOrgQuestion){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeOrgQuestionService.deleteKirikaeOrgQuestion(kirikaeOrgQuestion, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 查询可以添加的确认内容列表
     * @param kirikaeOrgQuestionQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORGQUESTION_ADDLIST)
    @ResponseBody
    private Object queryAddList(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeOrgQuestionService.listAddQuestion(kirikaeOrgQuestionQuery);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 查询工单组织可以添加的确认内容列表
     * @param woOrderId 工单ID
     * @param orgId 组织ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORGQUESTION_ADDWOLIST)
    @ResponseBody
    private Object queryWoOrderOrgAddList(Integer woOrderId, Integer orgId){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            List<Map<String, Object>> dataMapList = this.kirikaeOrgQuestionService.listWoOrderAddQuestion(woOrderId, orgId);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 增加组织确认内容关联
     * @param orgId 组织ID
     * @param questionIdStr 确认内容ID字符串
     * @return 返回结果
     */
    @RequestMapping(value = Url.ORGQUESTION_ADD)
    @ResponseBody
    private Object addKirikaeOrgQuestion(HttpServletRequest request, Integer orgId, String questionIdStr){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            String[] questionIds = null;
            if(!(questionIdStr == null || "".equals(questionIdStr))){
                questionIds = questionIdStr.split(",");
            }
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.kirikaeOrgQuestionService.addKirikaeOrgQuestion(orgId, questionIds, user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
