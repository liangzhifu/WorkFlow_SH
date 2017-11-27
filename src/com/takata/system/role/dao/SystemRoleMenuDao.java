package com.takata.system.role.dao;

import com.takata.common.dao.SqlDao;
import com.takata.system.role.domain.SystemRole;
import com.takata.system.role.domain.SystemRoleMenu;
import com.takata.system.role.query.SystemRoleMenuQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SystemRoleMenuDao extends SqlDao {

    /**
     * 新增角色菜单关联
     * @param systemRoleMenu 角色菜单关联实体信息
     * @return 返回结果
     */
    public Integer insertSystemRoleMenu(SystemRoleMenu systemRoleMenu) {
        return this.writerSqlSession.insert("SystemRoleMenuMapper.insertSystemRoleMenu", systemRoleMenu);
    }

    /**
     * 修改角色菜单关联信息
     * @param systemRoleMenu 角色菜单关联实体信息
     * @return 返回结果
     */
    public Integer updateSystemRoleMenu(SystemRoleMenu systemRoleMenu) {
        return this.writerSqlSession.update("SystemRoleMenuMapper.updateSystemRoleMenu", systemRoleMenu);
    }

    /**
     * 查询角色所拥有的菜单列表
     * @param systemRoleMenuQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectSystemRoleMenuList(SystemRoleMenuQuery systemRoleMenuQuery){
        return this.readSqlSession.selectList("SystemRoleMenuMapper.selectSystemRoleMenuList", systemRoleMenuQuery);
    }

}
