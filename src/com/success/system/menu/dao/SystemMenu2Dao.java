package com.success.system.menu.dao;

import com.success.system.menu.query.SystemMenuQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemMenu2Dao extends BaseDao {

    /**
     * 查询树型菜单列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectTreeMenuList(){
        return this.sqlSession.selectList("SystemMenuMapper2.selectTreeMenuList");
    }

    /**
     * 查询所有模块
     * @return
     */
    public List<Map<String, Object>> selectAllModule(){
        return this.sqlSession.selectList("SystemMenuMapper2.selectAllModule");
    }

    /**
     * 查询用户的模块
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectUserModule(SystemMenuQuery systemMenuQuery){
        return this.sqlSession.selectList("SystemMenuMapper2.selectUserModule", systemMenuQuery);
    }

    /**
     * 查询用户的菜单
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectUserMenuByModule(SystemMenuQuery systemMenuQuery){
        return this.sqlSession.selectList("SystemMenuMapper2.selectUserMenuByModule", systemMenuQuery);
    }
}
