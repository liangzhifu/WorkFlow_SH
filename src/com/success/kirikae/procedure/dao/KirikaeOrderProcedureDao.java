package com.success.kirikae.procedure.dao;

import com.success.kirikae.procedure.domain.KirikaeOrderProcedure;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderProcedureDao extends BaseDao {

    /**
     * 插入订单流程步骤
     * @param kirikaeOrderProcedure 订单流程步骤实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeOrderProcedure kirikaeOrderProcedure){
        return this.sqlSession.insert("KirikaeOrderProcedureMapper.insertSelective", kirikaeOrderProcedure);
    }

    /**
     * 更新订单流程步骤
     * @param kirikaeOrderProcedure 订单流程步骤实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeOrderProcedure kirikaeOrderProcedure){
        return this.sqlSession.update("KirikaeOrderProcedureMapper.updateByPrimaryKeySelective", kirikaeOrderProcedure);
    }

    /**
     * 查询切替单的订单流程步骤列表
     * @param kirikaeOrderId 切替单ID
     * @return 返回结果
     */
    public List<KirikaeOrderProcedure> selectKirikaeOrderProcedureListByOrderId(Integer kirikaeOrderId){
        return this.sqlSession.selectList("KirikaeOrderProcedureMapper.selectKirikaeOrderProcedureListByOrderId", kirikaeOrderId);
    }

    /**
     * 查询切替单的订单流程步骤列表
     * @param kirikaeOrderId 切替单ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeOrderProcedureMapListByOrderId(Integer kirikaeOrderId){
        return this.sqlSession.selectList("KirikaeOrderProcedureMapper.selectKirikaeOrderProcedureMapListByOrderId", kirikaeOrderId);
    }

}
