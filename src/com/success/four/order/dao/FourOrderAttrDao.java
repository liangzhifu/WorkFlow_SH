package com.success.four.order.dao;

import com.success.four.order.domain.FourOrderAttr;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class FourOrderAttrDao extends BaseDao {

    /**
     * 插入订单属性
     * @param fourOrderAttr 订单属性实体
     * @return 返回结果
     */
    public Integer insertSelective(FourOrderAttr fourOrderAttr){
        return this.sqlSession.insert("FourOrderAttrMapper.insertSelective", fourOrderAttr);
    }

    /**
     * 删除订单属性--通过订单ID
     * @param orderId 订单ID
     * @return 返回结果
     */
    public Integer deleteFourOrderAttrByOrderId(Integer orderId){
        return this.sqlSession.delete("FourOrderAttrMapper.deleteFourOrderAttrByOrderId", orderId);
    }

    /**
     * 查询订单属性列表--通过订单ID
     * @param orderId 订单ID
     * @return 返回结果
     */
    public List<FourOrderAttr> selectFourOrderAttrListByOrderId(Integer orderId){
        return this.sqlSession.selectList("FourOrderAttrMapper.selectFourOrderAttrListByOrderId", orderId);
    }
}
