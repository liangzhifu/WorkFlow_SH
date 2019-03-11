package com.success.agreement.base.dao;

import com.success.agreement.base.domain.AgreementFile;
import com.takata.common.dao.SqlDao;
import com.takata.dpcoi.rr.query.RRProblemQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by L on 2018/10/25.
 */
@Repository("agreementFileDao")
public class AgreementFileDao extends SqlDao {

    public List<Map<String, Object>> selectAgreementFileList(Integer agreementId){
        return this.readSqlSession.selectList("AgreementFileMapper.selectAgreementFileList", agreementId);
    }

    public Integer insertAgreementFile(AgreementFile agreementFile) {
        return this.writerSqlSession.insert("AgreementFileMapper.insertAgreementFile", agreementFile);
    }

    public Integer deleteAgreementFile(Integer id){
        return this.writerSqlSession.update("AgreementFileMapper.deleteAgreementFile", id);
    }
}
