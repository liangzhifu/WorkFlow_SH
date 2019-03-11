package com.success.kirikae.procedure.service;

import com.success.kirikae.procedure.domain.KirikaeProcedure;

import java.util.List;

public interface KirikaeProcedureService {

    /**
     * 查询切替流程步骤
     * @return 返回结果
     * @throws Exception 异常
     */
    List<KirikaeProcedure> listKirikaeProcedure(Integer templateId) throws Exception;

}
