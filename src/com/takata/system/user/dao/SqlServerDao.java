package com.takata.system.user.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lzf
 **/
@Repository
public class SqlServerDao {

    @Resource(name = "sqlServerSession")
    public SqlSessionTemplate sqlServerSession;

    public Map<String, Object> selectSqlServerUser(String userCode){
        return this.sqlServerSession.selectOne("SqlServerUserMapper.selectUser", userCode);
    }

}
