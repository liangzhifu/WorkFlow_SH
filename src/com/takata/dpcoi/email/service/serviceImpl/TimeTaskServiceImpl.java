package com.takata.dpcoi.email.service.serviceImpl;

import javax.annotation.Resource;

import com.takata.dpcoi.email.dao.TimeTaskDao;
import com.takata.dpcoi.email.domain.TimeTask;
import com.takata.dpcoi.email.query.TimeTaskQuery;
import com.takata.dpcoi.email.service.TimeTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("timeTaskService")
public class TimeTaskServiceImpl implements TimeTaskService {

	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Override
	public List<TimeTask> queryTimeTasks(TimeTaskQuery query) {
		// TODO Auto-generated method stub
		try{
			List<TimeTask> timeTasks = this.timeTaskDao.selectTimeTask(query);
			return timeTasks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insertTimeTask(TimeTask timeTask) throws Exception {
		// TODO Auto-generated method stub
		return this.timeTaskDao.insertTimeTask(timeTask);
	}

	@Override
	public Integer updateTimeTask(TimeTask timeTask) throws Exception {
		// TODO Auto-generated method stub
		return this.timeTaskDao.updateTimeTask(timeTask);
	}

	@Override
	public Integer deleteTimeTask(TimeTask timeTask) throws Exception {
		// TODO Auto-generated method stub
		return this.timeTaskDao.deleteTimeTask(timeTask);
	}

}
