package com.success.alteration.order.dao;

import com.success.alteration.order.domain.AlterationOrder;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author lzf
 **/
@Repository
public class AlterationOrderDao extends BaseDao {

    /**
     * 新增变更单
     * @param alterationOrder 变更单实体
     * @return 返回结果
     */
    public Integer insertAlterationOrder(AlterationOrder alterationOrder){
        return this.sqlSession.insert("AlterationOrderMapper.insertSelective", alterationOrder);
    }

    /**
     * 更新变更单
     * @param alterationOrder 变更单实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(AlterationOrder alterationOrder){
        return this.sqlSession.update("AlterationOrderMapper.updateByPrimaryKeySelective", alterationOrder);
    }

    /**
     * 获取单个变更单
     * @param alterationOrder 变更单实体
     * @return 返回结果
     */
    public AlterationOrder selectAlterationOrder(AlterationOrder alterationOrder){
        return this.sqlSession.selectOne("AlterationOrderMapper.selectByPrimaryKey", alterationOrder);
    }

}
