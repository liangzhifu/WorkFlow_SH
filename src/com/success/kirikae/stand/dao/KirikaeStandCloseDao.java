package com.success.kirikae.stand.dao;

import com.success.kirikae.stand.domain.KirikaeStandClose;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeStandCloseDao extends BaseDao {

    /**
     * 插入新的切替单立合
     * @param kirikaeStandClose 切替单立合实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeStandClose kirikaeStandClose){
        return this.sqlSession.insert("KirikaeStandCloseMapper.insertSelective", kirikaeStandClose);
    }

    /**
     * 更新切替单立合
     * @param kirikaeStandClose 切替单立合实体
     * @return 返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeStandClose kirikaeStandClose){
        return this.sqlSession.update("KirikaeStandCloseMapper.updateByPrimaryKeySelective", kirikaeStandClose);
    }

    /**
     * 查询切替单的立合人员列表
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public List<KirikaeStandClose> selectKirikaeStandCloseListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeStandCloseMapper.selectKirikaeStandCloseListByOrderId", orderId);
    }

    /**
     * 查询切替单的立合人员列表
     * @param orderId 切替单ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeStandCloseMapListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeStandCloseMapper.selectKirikaeStandCloseMapListByOrderId", orderId);
    }

}
