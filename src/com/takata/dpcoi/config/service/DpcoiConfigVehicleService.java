package com.takata.dpcoi.config.service;

import com.takata.dpcoi.config.domain.DpcoiConfigVehicle;
import com.takata.dpcoi.config.query.DpcoiConfigVehicleQuery;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/

public interface DpcoiConfigVehicleService {

    /**
     * 新增
     * @param dpcoiConfigVehicle 参数
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer addDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws Exception;

    /**
     * 删除
     * @param dpcoiConfigVehicle 参数
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer deleteDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws Exception;

    /**
     * 查询列表
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryDpcoiConfigVehicleList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws Exception;

    /**
     * 查询分页列表
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryDpcoiConfigVehiclePageList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws Exception;

    /**
     * 查询总数
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer queryDpcoiConfigVehicleCount(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws Exception;
}
