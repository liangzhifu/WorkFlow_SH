package com.takata.dpcoi.config.controller;

import com.takata.dpcoi.config.domain.DpcoiConfigVehicle;
import com.takata.dpcoi.config.query.DpcoiConfigVehicleQuery;
import com.takata.dpcoi.config.service.DpcoiConfigVehicleService;
import org.springframework.stereotype.Controller;
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
 * @create 2017-06-17
 **/
@Controller
@RequestMapping("/dpcoiConfigVehicle")
public class DpcoiConfigVehicleController {

    @Resource(name = "dpcoiConfigVehicleService")
    private DpcoiConfigVehicleService dpcoiConfigVehicleService;

    @RequestMapping("/getDpcoiConfigVehicleListDlg.do")
    public String getDpcoiConfigVehicleListDlg() throws Exception{
        return "dpcoi/dpcoiConfigVehicleList";
    }

    /**
     * 查询车型选项列表--分页
     * @param response 参数
     * @param dpcoiConfigVehicleQuery 查询条件
     */
    @RequestMapping("/getDpcoiConfigVehicleListPage.do")
    @ResponseBody
    public Object getDpcoiConfigVehicleListPage(HttpServletResponse response, DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiConfigVehicleList = this.dpcoiConfigVehicleService.queryDpcoiConfigVehiclePageList(dpcoiConfigVehicleQuery);
            Integer dpcoiConfigVehicleCount = this.dpcoiConfigVehicleService.queryDpcoiConfigVehicleCount(dpcoiConfigVehicleQuery);
            Integer pageCount = dpcoiConfigVehicleCount / dpcoiConfigVehicleQuery.getSize() + (dpcoiConfigVehicleCount % dpcoiConfigVehicleQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiConfigVehicleList", dpcoiConfigVehicleList);
            map.put("dpcoiConfigVehicleCount", dpcoiConfigVehicleCount);
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
     * 查询车型选项列表
     * @param response 参数
     * @param dpcoiConfigVehicleQuery 查询条件
     */
    @RequestMapping("/getDpcoiConfigVehicleList.do")
    @ResponseBody
    public Object getDpcoiConfigVehicleList(HttpServletResponse response, DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiConfigVehicleList = this.dpcoiConfigVehicleService.queryDpcoiConfigVehicleList(dpcoiConfigVehicleQuery);
            map.put("dpcoiConfigVehicleList", dpcoiConfigVehicleList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 新增车型选项
     * @param response 参数
     * @param dpcoiConfigVehicle 车型选项
     */
    @RequestMapping("/addDpcoiConfigVehicle.do")
    @ResponseBody
    public Object addDpcoiConfigVehicle(HttpServletResponse response, DpcoiConfigVehicle dpcoiConfigVehicle){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            dpcoiConfigVehicle.setDeleteState("0");
            this.dpcoiConfigVehicleService.addDpcoiConfigVehicle(dpcoiConfigVehicle);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 删除车型选项
     * @param response 参数
     * @param dpcoiConfigVehicle 车型选项
     */
    @RequestMapping("deleteDpcoiConfigVehicle.do")
    @ResponseBody
    public Object deleteDpcoiConfigVehicle(HttpServletResponse response, DpcoiConfigVehicle dpcoiConfigVehicle){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.dpcoiConfigVehicleService.deleteDpcoiConfigVehicle(dpcoiConfigVehicle);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
