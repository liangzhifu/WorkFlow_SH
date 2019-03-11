package com.success.system.user.dao;

import com.success.system.user.domain.SystemUser;
import com.success.system.user.query.SystemUserQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemUser2Dao extends BaseDao {

    /**
     * 根据用户ID查询用户
     * @param systemUser 用户实体
     * @return 返回结果
     */
    public SystemUser selectSystemUser(SystemUser systemUser){
        return this.sqlSession.selectOne("SystemUserMapper2.selectByPrimaryKey", systemUser);
    }

    /**
     * 新增用户
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer insertSystemUser(SystemUser systemUser) {
        return this.sqlSession.insert("SystemUserMapper2.insertSelective", systemUser);
    }

    /**
     * 修改用户信息
     * @param systemUser 用户实体信息
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateSystemUser(SystemUser systemUser) {
        return this.sqlSession.update("SystemUserMapper2.updateSystemUser", systemUser);
    }

    /**
     * 查询用户管理页面列表
     * @param systemUserQuery 查询条件
     * @return 返回列表数据
     */
    public List<Map<String, Object>> selectSystemUserPageList(SystemUserQuery systemUserQuery){
        return this.sqlSession.selectList("SystemUserMapper2.selectSystemUserPageList", systemUserQuery);
    }

    /**
     * 查询用户管理页面总数
     * @param systemUserQuery 查询条件
     * @return 返回总数
     */
    public Integer selectSystemUserCount(SystemUserQuery systemUserQuery){
        return this.sqlSession.selectOne("SystemUserMapper2.selectSystemUserCount", systemUserQuery);
    }

    /**
     * 根据工号查询用户
     * @param systemUser 用户实体数据
     * @return 返回结果
     */
    public SystemUser selectSystemUserByUserCode(SystemUser systemUser){
        return this.sqlSession.selectOne("SystemUserMapper2.selectSystemUserByUserCode", systemUser);
    }

    /**
     * 查询用户列表
     * @param systemUserQuery
     * @return
     */
    public List<SystemUser> selectSystemUserList(SystemUserQuery systemUserQuery) {
        return this.sqlSession.selectList("SystemUserMapper2.selectSystemUserList", systemUserQuery);
    }

    /**
     * 查询用户列表--通过权限
     * @param permissionId 权限ID
     * @return 返回结果
     */
    public List<SystemUser> selectSystemUserListByPermissionId(Integer permissionId) {
        return this.sqlSession.selectList("SystemUserMapper2.selectSystemUserListByPermissionId", permissionId);
    }

    /**
     * 过滤用户--通过权限ID
     * @param userIdList 用户列表
     * @param permissionId 权限ID
     * @return 返回结果
     */
    public List<SystemUser> selectFiterSystemUserListByPermissionId(List<Integer> userIdList, Integer permissionId){
        Map<String, Object> map = new HashedMap();
        map.put("userIdList", userIdList);
        map.put("permissionId", permissionId);
        return this.sqlSession.selectList("SystemUserMapper2.selectFiterSystemUserListByPermissionId", map);
    }

    /**
     * 验证用户是否有权限
     * @param userId 用户ID
     * @param permissionId 权限ID
     * @return 返回结果
     */
    public Integer selectVerificationSystemUserByPermissionId(Integer userId, Integer permissionId){
        Map<String, Object> map = new HashedMap();
        map.put("userId", userId);
        map.put("permissionId", permissionId);
        return this.sqlSession.selectOne("SystemUserMapper2.selectVerificationSystemUserByPermissionId", map);
    }

    /**
     * 查询设变单中确认书中的所有担当人员
     * @param orderId 设变单
     * @return 返回结果
     */
    public List<SystemUser> selectPreparedSystemUserListByOrderId(Integer orderId) {
        return this.sqlSession.selectList("SystemUserMapper2.selectPreparedSystemUserListByOrderId", orderId);
    }

}
