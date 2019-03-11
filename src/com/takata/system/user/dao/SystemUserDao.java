package com.takata.system.user.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.user.domain.SystemUser;
import com.takata.system.user.query.SystemUserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemUserDao extends SqlDao {

    /**
     * 根据用户ID查询用户
     * @param systemUser 用户实体
     * @return 返回结果
     */
    public SystemUser selectSystemUser(SystemUser systemUser){
        return this.readSqlSession.selectOne("SystemUserMapper.selectByPrimaryKey", systemUser);
    }

    /**
     * 新增用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer insertSystemUser(SystemUser systemUser) {
        return this.writerSqlSession.insert("SystemUserMapper.insertSelective", systemUser);
    }

    /**
     * 修改用户信息
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateSystemUser(SystemUser systemUser) {
        return this.writerSqlSession.update("SystemUserMapper.updateSystemUser", systemUser);
    }

    /**
     * 查询用户管理页面列表
     * @param systemUserQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemUserPageList(SystemUserQuery systemUserQuery){
        return this.readSqlSession.selectList("SystemUserMapper.selectSystemUserPageList", systemUserQuery);
    }

    /**
     * 查询用户管理页面总数
     * @param systemUserQuery 查询条件
     * @return 返回总数
     */
    public Integer selectSystemUserCount(SystemUserQuery systemUserQuery){
        return this.readSqlSession.selectOne("SystemUserMapper.selectSystemUserCount", systemUserQuery);
    }

    /**
     * 根据工号查询用户
     * @param systemUser 用户实体数据
     * @return 返回结果
     */
    public SystemUser selectSystemUserByUserCode(SystemUser systemUser){
        return this.readSqlSession.selectOne("SystemUserMapper.selectSystemUserByUserCode", systemUser);
    }

    /**
     * 查询部长权限
     * @param systemUser
     * @return
     */
    public Integer selectMinisterJurisdiction(SystemUser systemUser){
        return this.readSqlSession.selectOne("SystemUserMapper.selectMinisterJurisdiction", systemUser);
    }

    /**
     * 查询用户管理页面列表
     * @param systemUserQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemUserCompleteList(SystemUserQuery systemUserQuery){
        return this.readSqlSession.selectList("SystemUserMapper.selectSystemUserCompleteList", systemUserQuery);
    }

    /**
     * 根据权限ID查询用户列表
     * @param permission 权限
     * @return 返回结果
     */
    public List<SystemUser> selectSystemUserListByPermission(Integer permission) {
        return this.readSqlSession.selectList("SystemUserMapper.selectSystemUserListByPermission", permission);
    }
}
