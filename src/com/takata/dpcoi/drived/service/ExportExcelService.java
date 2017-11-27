package com.takata.dpcoi.drived.service;

import com.takata.dpcoi.rr.query.RRProblemQuery;

/**
 *
 * @author lzf
 * @create 2017-07-03
 **/

public interface ExportExcelService {

    /**
     * 导出RR问题点EXCEL
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public String excelRRProblem(String path, RRProblemQuery rrProblemQuery) throws Exception;

}
