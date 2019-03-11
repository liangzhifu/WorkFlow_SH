package com.success.four.template.controller;

import com.success.four.constant.Url;
import com.success.four.template.domain.FourTemplateAttr;
import com.success.four.template.service.FourTemplateAttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class FourTemplateAttrController {

    @Resource(name = "fourTemplateAttrService")
    private FourTemplateAttrService fourTemplateAttrService;

    /**
     * 查询模板多选框列表
     * @return 返回结果
     */
    @RequestMapping(value = Url.TEMPLATE_TEMPLATEATTRLIST)
    @ResponseBody
    private Object queryTemplateAttrList(){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try{
            //现在模板只有一个，写死模板ID为1
            List<FourTemplateAttr> dataMapList = this.fourTemplateAttrService.listFourTemplateAttr(1);
            map.put("dataMapList", dataMapList);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
