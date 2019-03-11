package com.success.kirikae.procedure.service.impl;

import com.success.kirikae.procedure.dao.KirikaeProcedureDao;
import com.success.kirikae.procedure.domain.KirikaeProcedure;
import com.success.kirikae.procedure.service.KirikaeProcedureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/
@Service("kirikaeProcedureService")
public class KirikaeProcedureServiceImpl implements KirikaeProcedureService {

    @Resource(name = "kirikaeProcedureDao")
    private KirikaeProcedureDao kirikaeProcedureDao;

    @Override
    public List<KirikaeProcedure> listKirikaeProcedure(Integer templateId) throws Exception {
        return this.kirikaeProcedureDao.selectKirikaeProcedureList(templateId);
    }

}
