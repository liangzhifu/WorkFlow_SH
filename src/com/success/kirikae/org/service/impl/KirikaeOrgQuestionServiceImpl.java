package com.success.kirikae.org.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.org.dao.KirikaeOrgQuestionDao;
import com.success.kirikae.org.domain.KirikaeOrgQuestion;
import com.success.kirikae.org.query.KirikaeOrgQuestionQuery;
import com.success.kirikae.org.service.KirikaeOrgQuestionService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("kirikaeOrgQuestionService")
public class KirikaeOrgQuestionServiceImpl implements KirikaeOrgQuestionService {

    @Resource(name = "kirikaeOrgQuestionDao")
    private KirikaeOrgQuestionDao kirikaeOrgQuestionDao;
    
    @Override
    public void addKirikaeOrgQuestion(Integer orgId, String[] questionIds, User user) throws Exception {
        KirikaeOrgQuestion kirikaeOrgQuestion = new KirikaeOrgQuestion();
        kirikaeOrgQuestion.setCreateBy(user.getUserId());
        kirikaeOrgQuestion.setCreateTime(new Date());
        kirikaeOrgQuestion.setUpdateBy(user.getUserId());
        kirikaeOrgQuestion.setUpdateTime(new Date());
        kirikaeOrgQuestion.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
        kirikaeOrgQuestion.setOrgId(orgId);
        for(String questionId : questionIds) {
            kirikaeOrgQuestion.setQuestionId(Integer.valueOf(questionId));
            kirikaeOrgQuestion.setId(null);
            Integer count = this.kirikaeOrgQuestionDao.insertKirikaeOrgQuestion(kirikaeOrgQuestion);
            if (count != 1) {
                throw new Exception("添加组织切替问题点关联异常！");
            }
        }
    }

    @Override
    public void deleteKirikaeOrgQuestion(KirikaeOrgQuestion kirikaeOrgQuestion, User user) throws Exception {
        kirikaeOrgQuestion.setUpdateBy(user.getUserId());
        kirikaeOrgQuestion.setUpdateTime(new Date());
        kirikaeOrgQuestion.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
        Integer count = this.kirikaeOrgQuestionDao.updateKirikaeOrgQuestion(kirikaeOrgQuestion);
        if (count != 1){
            throw new Exception("添加组织切替问题点关联异常！");
        }
    }

    @Override
    public void deleteKirikaeOrgQuestionByOrg(Integer orgId, User user) throws Exception {
        KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery = new KirikaeOrgQuestionQuery();
        kirikaeOrgQuestionQuery.setOrgId(orgId);
        List<Map<String, Object>> mapList = this.kirikaeOrgQuestionDao.selectKirikaeOrgQuestionList(kirikaeOrgQuestionQuery);
        for (Map<String, Object> map : mapList){
            KirikaeOrgQuestion kirikaeOrgQuestion = new KirikaeOrgQuestion();
            Integer id = (Integer) map.get("id");
            kirikaeOrgQuestion.setId(id);
            this.deleteKirikaeOrgQuestion(kirikaeOrgQuestion, user);
        }
    }

    @Override
    public void deleteKirikaeOrgQuestionByQuestion(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery, User user) throws Exception {
        List<Map<String, Object>> mapList = this.kirikaeOrgQuestionDao.selectKirikaeOrgQuestionList(kirikaeOrgQuestionQuery);
        for (Map<String, Object> map : mapList){
            KirikaeOrgQuestion kirikaeOrgQuestion = new KirikaeOrgQuestion();
            Integer id = (Integer) map.get("id");
            kirikaeOrgQuestion.setId(id);
            this.deleteKirikaeOrgQuestion(kirikaeOrgQuestion, user);
        }
    }

    @Override
    public List<Map<String, Object>> listKirikaeOrgQuestion(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery) throws Exception {
        return this.kirikaeOrgQuestionDao.selectKirikaeOrgQuestionList(kirikaeOrgQuestionQuery);
    }

    @Override
    public List<Map<String, Object>> listAddQuestion(KirikaeOrgQuestionQuery kirikaeOrgQuestionQuery) throws Exception {
        return this.kirikaeOrgQuestionDao.selectAddQuestionList(kirikaeOrgQuestionQuery);
    }

    @Override
    public List<Map<String, Object>> listWoOrderAddQuestion(Integer woOrderId, Integer orgId) throws Exception {
        return this.kirikaeOrgQuestionDao.selectWoOrderOrgAddQuestionList(woOrderId, orgId);
    }
}
