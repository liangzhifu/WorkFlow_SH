package com.success.kirikae.order.dao;

import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrderPartsNumberHistoryDao extends BaseDao{

        /**
         * 新增品号变更
         * @param alterationKirikaeOrderPartsNumber 品号变更实体
         * @return 返回结果
         */
        public Integer insertSelective(KirikaeOrderPartsNumber alterationKirikaeOrderPartsNumber){
            return this.sqlSession.insert("KirikaeOrderPartsNumberHistoryMapper.insertSelective", alterationKirikaeOrderPartsNumber);
        }

        /**
         * 查询品号变更列表--通过切替单ID
         * @param orderId 切替单ID
         * @return 返回结果
         */
        public List<KirikaeOrderPartsNumber> selectKirikaeOrderPartsNumberHistoryListByOrderId(Integer orderId){
            return this.sqlSession.selectList("KirikaeOrderPartsNumberHistoryMapper.selectKirikaeOrderPartsNumberHistoryListByOrderId", orderId);
        }

}
