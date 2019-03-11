package com.success.kirikae.wo.controller;

import com.success.common.Constant;
import com.success.kirikae.constant.Url;
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
public class KirikaeWoOrderController {

    @Resource(name = "kirikaeWoOrderService")
    private KirikaeWoOrderService kirikaeWoOrderService;

    /**
     * 获取需要立合的组织列表
     * @param orderId 切替单ID
     * @return 返回结果
     */
    @RequestMapping(value = Url.WOORDER_STAND_CLOSE_ORG)
    @ResponseBody
    private Object getStandCloseOrg(Integer orderId) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        try {
            List<Map<String, Object>> dataMapList = this.kirikaeWoOrderService.listStandCloseOrg(orderId);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
