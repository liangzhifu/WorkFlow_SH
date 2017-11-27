package com.takata.dpcoi.email.service;

import com.takata.dpcoi.email.domain.TimeTask;
import com.takata.dpcoi.email.query.TimeTaskQuery;

import java.util.List;

public interface TimeTaskService {

	public List<TimeTask> queryTimeTasks(TimeTaskQuery query);
	
	public Integer insertTimeTask(TimeTask timeTask) throws Exception;
	
	public Integer updateTimeTask(TimeTask timeTask) throws Exception;
	
	public Integer deleteTimeTask(TimeTask timeTask) throws Exception;
	
}
