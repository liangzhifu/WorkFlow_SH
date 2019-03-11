package com.success.system.role.dao;

import com.success.system.role.domain.SystemRoleMenu;
import com.success.system.role.query.SystemRoleMenuQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class SystemRoleMenu2Dao extends BaseDao {

    /**
     * 新增角色菜单关联
     * @param systemRoleMenu 角色菜单关联实体信息
     * @return 返回结果
     */
    public Integer insertSystemRoleMenu(SystemRoleMenu systemRoleMenu) {
        return this.sqlSession.insert("SystemRoleMenuMapper2.insertSystemRoleMenu", systemRoleMenu);
    }

    /**
     * 修改角色菜单关联信息
     * @param systemRoleMenu 角色菜单关联实体信息
     * @return 返回结果
     */
    public Integer updateSystemRoleMenu(SystemRoleMenu systemRoleMenu) {
        return this.sqlSession.update("SystemRoleMenuMapper2.updateSystemRoleMenu", systemRoleMenu);
    }

    /**
     * 查询角色所拥有的菜单列表
     * @param systemRoleMenuQuery 查询条件
     * @return 返回结果
     */
    public List<SystemRoleMenu> selectSystemRoleMenuList(SystemRoleMenuQuery systemRoleMenuQuery){
        return this.sqlSession.selectList("SystemRoleMenuMapper2.selectSystemRoleMenuList", systemRoleMenuQuery);
    }

}
