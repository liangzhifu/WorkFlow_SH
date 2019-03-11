package com.success.agreement.base.service.serviceImpl;

import com.success.agreement.base.dao.AgreementFileDao;
import com.success.agreement.base.domain.AgreementFile;
import com.success.agreement.base.service.AgreementFileService;
import com.takata.dpcoi.file.dao.FileUploadDao;
import com.takata.dpcoi.file.domain.FileUpload;
import com.takata.system.user.domain.SystemUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by L on 2018/10/25.
 */
@Service
public class AgreementFileServiceImpl implements AgreementFileService {

    @Resource(name = "agreementFileDao")
    private AgreementFileDao agreementFileDao;

    @Resource(name="fileUploadDao")
    private FileUploadDao fileUploadDao;

    @Override
    public List<Map<String, Object>> queryAgreementFileList(Integer agreementId) {
        return this.agreementFileDao.selectAgreementFileList(agreementId);
    }

    @Override
    public FileUpload addUploadFile(Integer agreementId, MultipartFile file, String path, SystemUser systemUser) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
        fileUpload.setExcelPdfName("");
        fileUpload.setCreateDate(new Date());
        fileUpload.setCreateBy(systemUser.getId());
        this.fileUploadDao.insertFileUpload(fileUpload);

        AgreementFile agreementFile = new AgreementFile();
        agreementFile.setAgreementId(agreementId);
        agreementFile.setFileId(fileUpload.getFileId());
        agreementFile.setDeleteState(0);
        this.agreementFileDao.insertAgreementFile(agreementFile);
        return fileUpload;
    }

    @Override
    public Integer deleteAgreementFile(Integer id) {
        return this.agreementFileDao.deleteAgreementFile(id);
    }

}
