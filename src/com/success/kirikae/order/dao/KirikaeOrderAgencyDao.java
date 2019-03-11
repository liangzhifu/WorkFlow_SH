package com.success.kirikae.order.dao;

import com.success.kirikae.order.query.KirikaeOrderAgencyQuery;
import com.success.kirikae.order.query.KirikaeOrderQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderAgencyDao extends BaseDao {

    /**
     * 查询切替单待办列表--分页
     * @param kirikaeOrderAgencyQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeOrderAgencyPageList(KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery){
        return this.sqlSession.selectList("KirikaeOrderAgencyMapper.selectKirikaeOrderAgencyPageList", kirikaeOrderAgencyQuery);
    }

    /**
     * 查询切替单待办数量
     * @param kirikaeOrderAgencyQuery 查询条件
     * @return 返回结果
     */
    public Integer selectKirikaeOrderAgencyCount(KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery){
        return this.sqlSession.selectOne("KirikaeOrderAgencyMapper.selectKirikaeOrderAgencyCount", kirikaeOrderAgencyQuery);
    }

}
