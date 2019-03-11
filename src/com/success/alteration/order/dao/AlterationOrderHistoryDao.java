package com.success.alteration.order.dao;

import com.success.alteration.order.domain.AlterationOrderHistory;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author lzf
 **/
@Repository
public class AlterationOrderHistoryDao extends BaseDao {

    /**
     * 新增变更单历史
     * @param alterationOrderHistory 变更单历史实体
     * @return 返回结果
     */
    public Integer insertAlterationOrder(AlterationOrderHistory alterationOrderHistory){
        return this.sqlSession.insert("AlterationOrderHistoryMapper.insertSelective", alterationOrderHistory);
    }

    /**
     * 获取单个变更单历史
     * @param alterationOrderHistory 变更单历史实体
     * @return 返回结果
     */
    public AlterationOrderHistory selectByPrimaryKey(AlterationOrderHistory alterationOrderHistory){
        return this.sqlSession.selectOne("AlterationOrderHistoryMapper.selectByPrimaryKey", alterationOrderHistory);
    }

    public Integer selectAlterationOrderVersion(Integer orderId){
        return this.sqlSession.selectOne("AlterationOrderHistoryMapper.selectAlterationOrderVersion", orderId);
    }


}
