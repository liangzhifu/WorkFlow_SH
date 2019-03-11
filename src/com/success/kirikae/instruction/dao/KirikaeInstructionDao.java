package com.success.kirikae.instruction.dao;

import com.success.kirikae.instruction.domain.KirikaeInstruction;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author lzf
 **/
@Repository
public class KirikaeInstructionDao extends BaseDao {

    /**
     * 新增切替指示书
     * @param kirikaeInstruction 切替指示书实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeInstruction kirikaeInstruction){
        return this.sqlSession.insert("KirikaeInstructionMapper.insertSelective", kirikaeInstruction);
    }

    /**
     * 获取切替指示书--通过切替单ID
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public KirikaeInstruction selectKirikaeInstructionByOrderId(Integer orderId){
        return this.sqlSession.selectOne("KirikaeInstructionMapper.selectKirikaeInstructionByOrderId", orderId);
    }

}
