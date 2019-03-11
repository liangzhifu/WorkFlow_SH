package com.success.kirikae.org.dao;

import com.success.kirikae.org.domain.KirikaeOrgQuestion;
import com.success.kirikae.org.query.KirikaeOrgQuestionQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class KirikaeOrgQuestionDao extends BaseDao {

    /**
     * 新增组织切替问题点关联
     * @param kirikaeOrgQuestion 组织切替问题点关联实体
     * @return 返回结果
     */
    public Integer insertKirikaeOrgQuestion(KirikaeOrgQuestion kirikaeOrgQuestion){
        return this.sqlSession.insert("KirikaeOrgQuestionMapper.insertSelective", kirikaeOrgQuestion);
    }

    /**
     * 更新组织切替问题点关联
     * @param kirikaeOrgQuestion 组织切替问题点关联实体
     * @return 返回结果
     */
    public Integer updateKirikaeOrgQuestion(KirikaeOrgQuestion kirikaeOrgQuestion){
        return this.sqlSession.update("KirikaeOrgQuestionMapper.updateByPrimaryKeySelective", kirikaeOrgQuestion);
    }

    /**
     * 查询组织关联的切替问题点关联
     * @param kirikaeOrgQuestionQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectKirikaeOrgQuestionList(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery){
        return this.sqlSession.selectList("KirikaeOrgQuestionMapper.selectKirikaeOrgQuestionList", kirikaeOrgQuestionQuery);
    }

    /**
     * 查询可以添加的确认内容列表
     * @param kirikaeOrgQuestionQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectAddQuestionList(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery){
        return this.sqlSession.selectList("KirikaeOrgQuestionMapper.selectAddQuestionList", kirikaeOrgQuestionQuery);
    }

    /**
     * 查询可以添加的确认内容列表
     * @param woOrderId 工单ID
     * @param orgId 组织ID
     * @return 返回结果
     */
    public List<Map<String, Object>> selectWoOrderOrgAddQuestionList(Integer woOrderId, Integer orgId){
        Map<String, Object> map = new LinkedMap();
        map.put("woOrderId", woOrderId);
        map.put("orgId", orgId);
        return this.sqlSession.selectList("KirikaeOrgQuestionMapper.selectWoOrderOrgAddQuestionList", map);
    }
}
