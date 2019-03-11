package com.success.agreement.base.service;

import com.takata.dpcoi.file.domain.FileUpload;
import com.takata.system.user.domain.SystemUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by L on 2018/10/25.
 */
public interface AgreementFileService {

    List<Map<String, Object>> queryAgreementFileList(Integer agreementId);

    FileUpload addUploadFile(Integer agreementId, MultipartFile file, String path, SystemUser systemUser) throws Exception;

    Integer deleteAgreementFile(Integer id);
}
