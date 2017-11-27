package com.takata.dpcoi.file.service.serviceImpl;

import com.takata.dpcoi.file.dao.FileUploadDao;
import com.takata.dpcoi.file.domain.FileUpload;
import com.takata.dpcoi.file.service.FileUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    @Resource(name="fileUploadDao")
    private FileUploadDao fileUploadDao;

    @Override
    public FileUpload queryFileUpload(FileUpload fileUpload) throws Exception {
        return this.fileUploadDao.selectBySelf(fileUpload);
    }
}
