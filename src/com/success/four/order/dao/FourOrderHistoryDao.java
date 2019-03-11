package com.success.four.order.dao;

import com.success.four.order.domain.FourOrder;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author lzf
 **/
@Repository
public class FourOrderHistoryDao extends BaseDao {

    /**
     * 插入新的4M订单
     * @param fourOrder 4M订单实体
     * @return 返回结果
     */
    public Integer insertSelective(FourOrder fourOrder){
        return this.sqlSession.insert("FourOrderHistoryMapper.insertSelective", fourOrder);
    }

    /**
     * 查询4M单--通过订单ID
     * @param orderId 订单ID
     * @return 返回结果
     */
    public FourOrder selectFourOrderHistoryByOrderId(Integer orderId){
        return this.sqlSession.selectOne("FourOrderHistoryMapper.selectFourOrderHistoryByOrderId", orderId);
    }

}
