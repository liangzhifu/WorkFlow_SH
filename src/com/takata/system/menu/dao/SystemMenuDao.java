package com.takata.system.menu.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.menu.query.SystemMenuQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemMenuDao extends SqlDao {

    /**
     * 查询树型菜单列表
     * @return 返回结果
     */
    public List<Map<String, Object>> selectTreeMenuList(){
        return this.readSqlSession.selectList("SystemMenuMapper.selectTreeMenuList");
    }

    /**
     * 查询所有模块
     * @return
     */
    public List<Map<String, Object>> selectAllModule(){
        return this.readSqlSession.selectList("SystemMenuMapper.selectAllModule");
    }

    /**
     * 查询用户的模块
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectUserModule(SystemMenuQuery systemMenuQuery){
        return this.readSqlSession.selectList("SystemMenuMapper.selectUserModule", systemMenuQuery);
    }

    /**
     * 查询用户的菜单
     * @param systemMenuQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectUserMenuByModule(SystemMenuQuery systemMenuQuery){
        return this.readSqlSession.selectList("SystemMenuMapper.selectUserMenuByModule", systemMenuQuery);
    }
}
