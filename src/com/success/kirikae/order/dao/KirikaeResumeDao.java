package com.success.kirikae.order.dao;

import com.success.kirikae.order.domain.KirikaeResume;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class KirikaeResumeDao extends BaseDao {

    /**
     * 新增切替单履历
     * @param kirikaeResume 切替单履历实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeResume kirikaeResume){
        return this.sqlSession.insert("KirikaeResumeMapper.insertSelective", kirikaeResume);
    }

    /**
     * 更新切替单履历
     * @param kirikaeResume 切替单履历实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeResume kirikaeResume){
        return this.sqlSession.delete("KirikaeResumeMapper.updateByPrimaryKeySelective", kirikaeResume);
    }

    /**
     * 获取切替单履历--通过订单ID
     * @param orderId 订单ID
     * @return 返回结果
     */
    public List<KirikaeResume> selectKirikaeResumeListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeResumeMapper.selectKirikaeResumeListByOrderId", orderId);
    }
}
