package com.success.kirikae.procedure.dao;

import com.success.kirikae.procedure.domain.KirikaeProcedure;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class KirikaeProcedureDao extends BaseDao {

    /**
     * 查询切替流程
     * @param templateId 模板ID
     * @return 返回结果
     */
    public List<KirikaeProcedure> selectKirikaeProcedureList(Integer templateId){
        return this.sqlSession.selectList("KirikaeProcedureMapper.selectKirikaeProcedureList", templateId);
    }

}
