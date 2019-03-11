package com.success.kirikae.order.dao;

import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.query.KirikaeOrderQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderDao extends BaseDao {

    /**
     * 新增切替变更单
     * @param alterationKirikaeOrder 切替变更单实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeOrder alterationKirikaeOrder){
        return this.sqlSession.insert("KirikaeOrderMapper.insertSelective", alterationKirikaeOrder);
    }

    /**
     * 更新切替变更单
     * @param alterationKirikaeOrder 切替变更单实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeOrder alterationKirikaeOrder){
        return this.sqlSession.update("KirikaeOrderMapper.updateByPrimaryKeySelective", alterationKirikaeOrder);
    }

    /**
     * 更新切替变更单
     * @param alterationKirikaeOrder 切替变更单实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKey(KirikaeOrder alterationKirikaeOrder){
        return this.sqlSession.update("KirikaeOrderMapper.updateByPrimaryKeySelective", alterationKirikaeOrder);
    }

    /**
     * 获取单个切替变更单
     * @param kirikaeOrder 切替变更单实体
     * @return 返回结果
     */
    public KirikaeOrder selectByPrimaryKey(KirikaeOrder kirikaeOrder){
        return this.sqlSession.selectOne("KirikaeOrderMapper.selectByPrimaryKey", kirikaeOrder);
    }

    /**
     * 获取单个切替变更单
     * @param orderId 变更单ID
     * @return 返回结果
     */
    public KirikaeOrder selectKirikaeOrderByOrderId(Integer orderId){
        return this.sqlSession.selectOne("KirikaeOrderMapper.selectKirikaeOrderByOrderId", orderId);
    }

    /**
     * 查询切替单列表--分页
     * @param kirikaeOrderQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeOrderPageList(KirikaeOrderQuery kirikaeOrderQuery){
        return this.sqlSession.selectList("KirikaeOrderMapper.selectKirikaeOrderPageList", kirikaeOrderQuery);
    }

    /**
     * 查询切替单数量
     * @param kirikaeOrderQuery 查询条件
     * @return 返回结果
     */
    public Integer selectKirikaeOrderCount(KirikaeOrderQuery kirikaeOrderQuery){
        return this.sqlSession.selectOne("KirikaeOrderMapper.selectKirikaeOrderCount", kirikaeOrderQuery);
    }

    /**
     * 获取切图单--屏幕展示列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeOrderScreenShowList() {
        return this.sqlSession.selectList("KirikaeOrderMapper.selectKirikaeOrderScreenShowList");
    }
}
