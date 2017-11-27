package com.takata.dpcoi.rr.controller;

import com.takata.dpcoi.rr.domain.RRDelayLeader;
import com.takata.dpcoi.rr.query.RRDelayLeaderQuery;
import com.takata.dpcoi.rr.service.RRDelayLeaderService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-28
 **/
@Controller
@RequestMapping("rrDelayLeader")
public class RRDelayLeaderController {

    @Resource(name = "rRDelayLeaderService")
    private RRDelayLeaderService rRDelayLeaderService;

    @RequestMapping("/getRRDelayLeaderListDlg.do")
    public String getRRDelayLeaderListDlg() {
        return "dpcoi/rrDelayLeaderList";
    }

    /**
     * 查询RR问题点责任人领导列表--分页
     * @param response 参数
     * @param rrDelayLeaderQuery 查询条件
     */
    @RequestMapping("/getRRDelayLeaderListPage.do")
    @ResponseBody
    public Object getRRDelayLeaderListPage(HttpServletResponse response, RRDelayLeaderQuery rrDelayLeaderQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> rrDelayLeaderList = this.rRDelayLeaderService.queryRRDelayLeaderPageList(rrDelayLeaderQuery);
            Integer rrDelayLeaderCount = this.rRDelayLeaderService.quereyRRDelayLeaderCount(rrDelayLeaderQuery);
            Integer pageCount = rrDelayLeaderCount / rrDelayLeaderQuery.getSize() + (rrDelayLeaderCount % rrDelayLeaderQuery.getSize() > 0 ? 1 : 0);
            map.put("rrDelayLeaderList", rrDelayLeaderList);
            map.put("rrDelayLeaderCount", rrDelayLeaderCount);
            map.put("pageCount", pageCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增RR问题责任人
     * @param response 参数
     * @param rrDelayLeader RR问题责任人
     */
    @RequestMapping("/addRRDelayLeader.do")
    @ResponseBody
    public Object addRRDelayLeader(HttpServletResponse response, @ModelAttribute RRDelayLeader rrDelayLeader){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rrDelayLeader.setDeleteState("0");
            this.rRDelayLeaderService.addRRDelayLeader(rrDelayLeader);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 更新RR问题点选项
     * @param response 参数
     * @param rrDelayLeader RR问题点选项
     */
    @RequestMapping("updateRRDelayLeader.do")
    @ResponseBody
    public Object updateRRDelayLeader(HttpServletResponse response, RRDelayLeader rrDelayLeader){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.rRDelayLeaderService.updateRRDelayLeader(rrDelayLeader);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
