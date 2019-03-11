package com.success.kirikae.wo.dao;

import com.success.kirikae.wo.domain.KirikaeWoOrderAttr;
import com.success.web.framework.mybatis.BaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeWoOrderAttrDao extends BaseDao {

    /**
     * 插入新的工单属性
     * @param kirikaeWoOrderAttr 工单属性实体
     * @return 返回结果
     */
    public Integer insertSelective(KirikaeWoOrderAttr kirikaeWoOrderAttr){
        return this.sqlSession.insert("KirikaeWoOrderAttrMapper.insertSelective", kirikaeWoOrderAttr);
    }

    /**
     * 更新工单属性
     * @param kirikaeWoOrderAttr 工单属性实体
     * @return  返回结果
     */
    public Integer updateByPrimaryKeySelective(KirikaeWoOrderAttr kirikaeWoOrderAttr){
        return this.sqlSession.update("KirikaeWoOrderAttrMapper.updateByPrimaryKeySelective", kirikaeWoOrderAttr);
    }

    /**
     * 获取可以添加的确认项目
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeWoOrderAttrAddList(Integer orderId, Integer userId){
        Map<String, Object> map = new HashedMap();
        map.put("orderId", orderId);
        map.put("userId", userId);
        return this.sqlSession.selectList("KirikaeWoOrderAttrMapper.selectKirikaeWoOrderAttrAddList", map);
    }

    /**
     * 查询所有的确认项目
     * @param orderId 订单ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeWoOrderAttrMapListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeWoOrderAttrMapper.selectKirikaeWoOrderAttrMapListByOrderId", orderId);
    }

    /**
     * 查询所有的确认项目
     * @param orderId 订单ID
     * @return 返回结果
     */
    public List<KirikaeWoOrderAttr> selectKirikaeWoOrderAttrListByOrderId(Integer orderId){
        return this.sqlSession.selectList("KirikaeWoOrderAttrMapper.selectKirikaeWoOrderAttrListByOrderId", orderId);
    }

    /**
     * 查询所有的确认项目
     * @param woOrderId 工单ID
     * @return 返回结果
     */
    public List<KirikaeWoOrderAttr> selectKirikaeWoOrderAttrListByWoOrderId(Integer woOrderId){
        return this.sqlSession.selectList("KirikaeWoOrderAttrMapper.selectKirikaeWoOrderAttrListByWoOrderId", woOrderId);
    }

    /**
     * 查询某人担当的确认项目
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeWoOrderAttrListByUserId(Integer orderId, Integer userId, String stateType){
        Map<String, Object> map = new HashedMap();
        map.put("orderId", orderId);
        map.put("userId", userId);
        map.put("stateType", stateType);
        return this.sqlSession.selectList("KirikaeWoOrderAttrMapper.selectKirikaeWoOrderAttrListByUserId", map);
    }

    /**
     * 删除工单的属性
     * @param woOrderId 工单
     * @return 返回结果
     */
    public Integer deleteWoOrderAttrByWoOrderId(Integer woOrderId){
        return this.sqlSession.update("KirikaeWoOrderAttrMapper.deleteWoOrderAttrByWoOrderId", woOrderId);
    }

    /**
     * 查询未确认的工单数量
     * @param orderId 订单ID
     * @return 返回结果
     */
    public Integer selectWoOrderAttrNoConfirmCount(Integer orderId){
        return this.sqlSession.selectOne("KirikaeWoOrderAttrMapper.selectWoOrderAttrNoConfirmCount", orderId);
    }

    /**
     * 查询未填写立合结果的工单属性数量
     * @param orderId 订单ID
     * @return 返回结果
     */
    public Integer selectWoOrderAttrNoStandCloseCount(Integer orderId){
        return this.sqlSession.selectOne("KirikaeWoOrderAttrMapper.selectWoOrderAttrNoStandCloseCount", orderId);
    }

    /**
     * 查询未上传文件的工单属性数量
     * @param orderId 订单ID
     * @return 返回结果
     */
    public Integer selectWoOrderAttrNoUploadCount(Integer orderId){
        return this.sqlSession.selectOne("KirikaeWoOrderAttrMapper.selectWoOrderAttrNoUploadCount", orderId);
    }

    /**
     * 查询立合列表
     * @param orderId 订单ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeAgreementList(Integer orderId){
        return this.sqlSession.selectList("KirikaeWoOrderAttrMapper.selectKirikaeAgreementList", orderId);
    }
}
