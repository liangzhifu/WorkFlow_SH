package com.success.four.order.dao;

import com.success.four.order.domain.FourOrderAttr;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class FourOrderAttrHistoryDao extends BaseDao {

    /**
     * 插入订单属性
     * @param fourOrderAttr 订单属性实体
     * @return 返回结果
     */
    public Integer insertSelective(FourOrderAttr fourOrderAttr){
        return this.sqlSession.insert("FourOrderAttrHistoryMapper.insertSelective", fourOrderAttr);
    }

    /**
     * 查询订单属性列表--通过订单ID
     * @param orderId 订单ID
     * @return 返回结果
     */
    public List<FourOrderAttr> selectFourOrderAttrHistoryListByOrderId(Integer orderId){
        return this.sqlSession.selectList("FourOrderAttrHistoryMapper.selectFourOrderAttrHistoryListByOrderId", orderId);
    }

}
