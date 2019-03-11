package com.success.sys.email.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.email.domain.TimeTask;
import com.success.sys.email.query.TimeTaskQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("timeTask2Dao")
public class TimeTask2Dao extends BaseDao {

	public List<TimeTask> selectTimeTask(TimeTaskQuery query) throws DaoException{
		return this.sqlSession.selectList("timeTask2.selectTimeTask", query);
	}
	
	public Integer insertTimeTask(TimeTask timeTask){
		if(timeTask == null) return -1;
		return this.sqlSession.insert("timeTask2.insertTimeTask", timeTask);
	}
	
	public Integer updateTimeTask(TimeTask timeTask){
		if(timeTask == null) return -1;
		return this.sqlSession.update("timeTask2.updateTimeTask", timeTask);
	}

	public Integer deleteTimeTask(TimeTask timeTask){
		return this.sqlSession.delete("timeTask2.deleteTimeTask", timeTask);
	}
	
}
