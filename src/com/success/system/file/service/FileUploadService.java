package com.success.system.file.service;

import com.dpcoi.file.domain.FileUpload;
import com.success.sys.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lzf
 **/

public interface FileUploadService {

    /**
     * 获取文件实体
     * @param fileUpload 实体ID
     * @return 返回结果
     * @throws Exception 异常
     */
    FileUpload getFileUpload(FileUpload fileUpload) throws Exception;

    /**
     * 新增上传文件
     * @param fileUpload 上传文件实体
     * @throws Exception 异常
     */
    void addFileUpload(FileUpload fileUpload, User user) throws Exception;

    /**
     *  保存上传文件
     * @param file 文件
     * @param path 文件路径
     * @return 返回结果
     * @throws Exception 异常
     */
    FileUpload addFile(MultipartFile file, String path, User user) throws Exception;

}
