package com.success.system.file.service.impl;

import com.dpcoi.file.dao.FileUpload2Dao;
import com.dpcoi.file.domain.FileUpload;
import com.success.sys.user.domain.User;
import com.success.system.file.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @author lzf
 **/
@Service("fileUploadServiceImpl")
public class FileUploadServiceImpl implements FileUploadService {

    @Resource(name = "fileUpload2Dao")
    private FileUpload2Dao fileUploadDao;

    @Override
    public FileUpload getFileUpload(FileUpload fileUpload) throws Exception {
        return this.fileUploadDao.selectBySelf(fileUpload);
    }

    @Override
    public void addFileUpload(FileUpload fileUpload, User user) throws Exception {
        fileUpload.setCreateBy(user.getUserId());
        fileUpload.setCreateDate(new Date());
        Integer num = this.fileUploadDao.insertFileUpload(fileUpload);
        if(num != 1){
            throw new Exception("新增上传文件失败！");
        }
    }

    @Override
    public FileUpload addFile(MultipartFile file, String path, User user) throws Exception {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        int index = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(index);
        String fileAlias = UUID.randomUUID().toString() + fileSuffix;
        String filePath = path + "fileupload/" + fileAlias;
        file.transferTo(new File(filePath));

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileAlias(fileAlias);
        fileUpload.setFileType(fileType);
        this.addFileUpload(fileUpload, user);
        return fileUpload;
    }

}
