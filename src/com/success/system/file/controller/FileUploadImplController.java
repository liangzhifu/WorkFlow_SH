package com.success.system.file.controller;

import com.dpcoi.file.domain.FileUpload;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.system.constant.Url;
import com.success.system.file.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class FileUploadImplController {

    @Resource(name = "fileUploadServiceImpl")
    private FileUploadService fileUploadService;

    @RequestMapping(value= Url.FILE_FILEUPLOAD, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object fileUpload(HttpServletRequest request, @RequestParam("uploadFile") MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>(5);
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String path = request.getSession().getServletContext().getRealPath("/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            if(!file.isEmpty()){
                User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
                FileUpload fileUpload = this.fileUploadService.addFile(file, path, user);
                map.put("success", true);
                map.put("fileId", fileUpload.getFileId());
                map.put("fileName", fileUpload.getFileName());
                map.put("fileDate", formatter.format(new Date()));
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

    @RequestMapping(value=Url.FILE_FILEDOWNLOAD,method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response, Integer fileId) throws Exception{
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileId(fileId);
        fileUpload = this.fileUploadService.getFileUpload(fileUpload);
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
        String path = request.getSession().getServletContext().getRealPath("/");

        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");

        String fileName = fileUpload.getFileName();

        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+new String( fileName.getBytes("gb2312"), "ISO8859-1" ) );
        ServletOutputStream out;
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
        File file = new File(path + "fileupload/" + fileUpload.getFileAlias());

        try {
            FileInputStream inputStream = new FileInputStream(file);

            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1){
                b = inputStream.read(buffer);
                //4.写到输出流(out)中
                if(b != -1) {
                    out.write(buffer,0,b);
                }
            }
            inputStream.close();
            out.close();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
