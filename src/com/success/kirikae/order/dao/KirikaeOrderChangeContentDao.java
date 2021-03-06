package com.success.kirikae.order.dao;

import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.query.KirikaeOrderChangeContentQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderChangeContentDao extends BaseDao {

    /**
     * 新增设变内容
     * @param kKirikaeOrderChangeContent 设变内容实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeOrderChangeContent kKirikaeOrderChangeContent){
        return this.sqlSession.insert("KirikaeOrderChangeContentMapper.insertSelective", kKirikaeOrderChangeContent);
    }

    /**
     * 更新设变内容
     * @param kKirikaeOrderChangeContent 设变内容实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeOrderChangeContent kKirikaeOrderChangeContent){
        return this.sqlSession.update("KirikaeOrderChangeContentMapper.updateByPrimaryKeySelective", kKirikaeOrderChangeContent);
    }

    /**
     * 查询设变内容列表--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public List<KirikaeOrderChangeContent> selectKirikaeOrderChangeContentListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeOrderChangeContentMapper.selectKirikaeOrderChangeContentListByOrderId", orderId);
    }

}
