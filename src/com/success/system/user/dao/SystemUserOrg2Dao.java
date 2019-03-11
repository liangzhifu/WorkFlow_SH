package com.success.system.user.dao;

import com.success.system.user.domain.SystemUserOrg;
import com.success.system.user.query.SystemUserOrgQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemUserOrg2Dao extends BaseDao {

    /**
     * 新增用户组织关联
     * @param systemUserOrg 用户组织关联实体信息
     * @return 返回结果
     */
    public Integer insertSystemUserOrg(SystemUserOrg systemUserOrg) {
        return this.sqlSession.insert("SystemUserOrgMapper2.insertSystemUserOrg", systemUserOrg);
    }

    /**
     * 修改用户组织关联信息
     * @param systemUserOrg 用户组织关联实体信息
     * @return 返回结果
     */
    public Integer updateSystemUserOrg(SystemUserOrg systemUserOrg) {
        return this.sqlSession.update("SystemUserOrgMapper2.updateSystemUserOrg", systemUserOrg);
    }

    /**
     * 查询用户组织关联列表
     * @param systemUserOrgQuery 查询条件
     * @return 返回列表数据
     */
    public List<SystemUserOrg> selectSystemUserOrgList(SystemUserOrgQuery systemUserOrgQuery){
        return this.sqlSession.selectList("SystemUserOrgMapper2.selectSystemUserOrgList", systemUserOrgQuery);
    }

    /**
     * 获取用户组织关联列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectUserOrgList(){
        return this.sqlSession.selectList("SystemUserOrgMapper2.selectUserOrgList");
    }
}
