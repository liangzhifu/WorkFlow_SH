package com.takata.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author lzf
 **/
@Repository
public class SqlDao {

    @Resource(name = "readSqlSession")
    public SqlSessionTemplate readSqlSession;

    @Resource(name = "writerSqlSession")
    public SqlSessionTemplate writerSqlSession;

    public SqlDao(){}

}
