package com.success.kirikae.confirmation.dao;

import com.success.kirikae.confirmation.domain.KirikaeConfirmation;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author lzf
 **/
@Repository
public class KirikaeConfirmationDao extends BaseDao {

    /**
     * 新增切替确认书
     * @param kirikaeConfirmation 切替确认书实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeConfirmation kirikaeConfirmation){
        return this.sqlSession.insert("KirikaeConfirmationMapper.insertSelective", kirikaeConfirmation);
    }

    /**
     * 获取切替确认书--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public KirikaeConfirmation selectKirikaeConfirmationByOrderId(Integer orderId){
        return this.sqlSession.selectOne("KirikaeConfirmationMapper.selectKirikaeConfirmationByOrderId", orderId);
    }

}
