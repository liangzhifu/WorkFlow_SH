package com.success.agreement.base.controller;

import com.success.agreement.base.service.AgreementFileService;
import com.takata.common.shiro.Principal;
import com.takata.common.shiro.PrincipalUtils;
import com.takata.dpcoi.file.domain.FileUpload;
import com.takata.system.user.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2018/10/25.
 */
@Controller
@RequestMapping("agreementFile")
public class AgreementFileController {

    @Autowired
    private AgreementFileService agreementFileService;

    @RequestMapping("/getAgreementFileList.do")
    @ResponseBody
    public Object getAgreementFileList(Integer agreementId){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> agreementFileList = this.agreementFileService.queryAgreementFileList(agreementId);
            map.put("agreementFileList", agreementFileList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping("/deleteAgreementFile.do")
    @ResponseBody
    public Object deleteAgreementFile(Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.agreementFileService.deleteAgreementFile(id);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value="uploadFile.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object uploadFile(HttpServletRequest request, @RequestParam("agreementId") Integer agreementId, @RequestParam("uploadFile") MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Principal principal = PrincipalUtils.getPrincipal();
            SystemUser systemUser = new SystemUser();
            systemUser.setId(principal.getId());
            systemUser.setUserName(principal.getUserName());
            String path = request.getSession().getServletContext().getRealPath("/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            if(!file.isEmpty()){
                FileUpload fileUpload = this.agreementFileService.addUploadFile(agreementId, file, path, systemUser);
                map.put("success", true);
                map.put("message", "上传成功");
            }else {
                map.put("success", false);
                map.put("message", "上传失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
