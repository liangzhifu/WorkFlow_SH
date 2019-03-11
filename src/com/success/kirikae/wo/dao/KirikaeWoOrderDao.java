package com.success.kirikae.wo.dao;

import com.success.kirikae.wo.domain.KirikaeWoOrder;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeWoOrderDao extends BaseDao {

    /**
     * 插入新工单
     * @param kirikaeWoOrder
     * @return
     */
    public Integer insertSelective(KirikaeWoOrder kirikaeWoOrder){
        return this.sqlSession.insert("KirikaeWoOrderMapper.insertSelective", kirikaeWoOrder);
    }

    /**
     * 更新工单
     * @param kirikaeWoOrder
     * @return
     */
    public Integer updateByPrimaryKeySelective(KirikaeWoOrder kirikaeWoOrder){
        return this.sqlSession.update("KirikaeWoOrderMapper.updateByPrimaryKeySelective", kirikaeWoOrder);
    }

    /**
     * 获取工单列表--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public List<KirikaeWoOrder> selectKirikaeWoOrderListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeWoOrderMapper.selectKirikaeWoOrderListByOrderId", orderId);
    }

    public List<Map<String, Object>> selectStandCloseOrgList(Integer orderId){
        return this.sqlSession.selectList("KirikaeWoOrderMapper.selectStandCloseOrgList", orderId);
    }

    public Integer updateWoOrderStateAuto(Integer orderId){
        return this.sqlSession.update("KirikaeWoOrderMapper.updateWoOrderStateAuto", orderId);
    }
}
