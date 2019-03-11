package com.success.kirikae.order.dao;

import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.query.KirikaeOrderPartsNumberQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderPartsNumberDao extends BaseDao {

    /**
     * 新增品号变更
     * @param alterationKirikaeOrderPartsNumber 品号变更实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeOrderPartsNumber alterationKirikaeOrderPartsNumber){
        return this.sqlSession.insert("KirikaeOrderPartsNumberMapper.insertSelective", alterationKirikaeOrderPartsNumber);
    }

    /**
     * 更新品号变更
     * @param alterationKirikaeOrderPartsNumber 品号变更实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeOrderPartsNumber alterationKirikaeOrderPartsNumber){
        return this.sqlSession.update("KirikaeOrderPartsNumberMapper.updateByPrimaryKeySelective", alterationKirikaeOrderPartsNumber);
    }

    /**
     * 查询品号变更列表--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public List<KirikaeOrderPartsNumber> selectKirikaeOrderPartsNumberListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeOrderPartsNumberMapper.selectKirikaeOrderPartsNumberListByOrderId", orderId);
    }
}
