package com.dpcoi.file.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/7.
 */

import com.dpcoi.file.dao.FileUpload2Dao;
import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.file.service.FileUpload2Service;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

@Service("fileUpload2Service")
public class FileUpload2ServiceImpl implements FileUpload2Service {

    @Resource(name="fileUpload2Dao")
    private FileUpload2Dao fileUploadDao;

    @Override
    public FileUpload queryFileUpload(FileUpload fileUpload) throws ServiceException {
        return this.fileUploadDao.selectBySelf(fileUpload);
    }
}
