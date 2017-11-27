package com.takata.dpcoi.file.service;

import com.takata.dpcoi.file.domain.FileUpload;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

public interface FileUploadService {

    /**
     * 获取文件实体
     * @param fileUpload 实体ID
     * @return 返回结果
     * @throws Exception 异常
     */
    public FileUpload queryFileUpload(FileUpload fileUpload) throws Exception;
}
