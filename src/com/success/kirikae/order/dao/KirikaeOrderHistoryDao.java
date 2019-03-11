package com.success.kirikae.order.dao;

import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.query.KirikaeOrderHistoryQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderHistoryDao extends BaseDao {
    /**
     * 新增切替变更单
     * @param kirikaeOrder 切替变更单实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeOrder kirikaeOrder){
        return this.sqlSession.insert("KirikaeOrderHistoryMapper.insertSelective", kirikaeOrder);
    }

    /**
     * 获取单个切替变更单
     * @param orderId 变更单ID
     * @return 返回结果
     */
    public KirikaeOrder selectKirikaeOrderHistoryByOrderId(Integer orderId){
        return this.sqlSession.selectOne("KirikaeOrderHistoryMapper.selectKirikaeOrderHistoryByOrderId", orderId);
    }

    /**
     * 查询切替单列表--分页
     * @param kirikaeOrderHistoryQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeOrderHistoryPageList(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery){
        return this.sqlSession.selectList("KirikaeOrderHistoryMapper.selectKirikaeOrderHistoryPageList", kirikaeOrderHistoryQuery);
    }

    /**
     * 查询切替单数量
     * @param kirikaeOrderHistoryQuery 查询条件
     * @return 返回结果
     */
    public Integer selectKirikaeOrderHistoryCount(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery){
        return this.sqlSession.selectOne("KirikaeOrderHistoryMapper.selectKirikaeOrderHistoryCount", kirikaeOrderHistoryQuery);
    }

}
