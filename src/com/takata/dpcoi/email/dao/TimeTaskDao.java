package com.takata.dpcoi.email.dao;

import com.takata.common.dao.SqlDao;
import com.takata.dpcoi.email.domain.TimeTask;
import com.takata.dpcoi.email.query.TimeTaskQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("timeTaskDao")
public class TimeTaskDao extends SqlDao {

	public List<TimeTask> selectTimeTask(TimeTaskQuery query) {
		return this.readSqlSession.selectList("timeTask.selectTimeTask", query);
	}
	
	public Integer insertTimeTask(TimeTask timeTask){
		if(timeTask == null) {
			return -1;
		}
		return this.writerSqlSession.insert("timeTask.insertTimeTask", timeTask);
	}
	
	public Integer updateTimeTask(TimeTask timeTask){
		if(timeTask == null) {
			return -1;
		}
		return this.writerSqlSession.update("timeTask.updateTimeTask", timeTask);
	}

	public Integer deleteTimeTask(TimeTask timeTask){
		return this.writerSqlSession.delete("timeTask.deleteTimeTask", timeTask);
	}
	
}
