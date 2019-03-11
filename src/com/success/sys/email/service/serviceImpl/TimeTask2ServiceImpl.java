package com.success.sys.email.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.email.query.TimeTaskQuery;
import com.success.sys.email.service.TimeTask2Service;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;

@Service("timeTask2Service")
public class TimeTask2ServiceImpl implements TimeTask2Service {

	@Resource(name = "timeTask2Dao")
	private TimeTask2Dao timeTaskDao;
	
	@Override
	public List<TimeTask> queryTimeTasks(TimeTaskQuery query) {
		// TODO Auto-generated method stub
		try{
			List<TimeTask> timeTasks = this.timeTaskDao.selectTimeTask(query);
			return timeTasks;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insertTimeTask(TimeTask timeTask) throws ServiceException {
		// TODO Auto-generated method stub
		return this.timeTaskDao.insertTimeTask(timeTask);
	}

	@Override
	public Integer updateTimeTask(TimeTask timeTask) throws ServiceException {
		// TODO Auto-generated method stub
		return this.timeTaskDao.updateTimeTask(timeTask);
	}

	@Override
	public Integer deleteTimeTask(TimeTask timeTask) throws ServiceException {
		// TODO Auto-generated method stub
		return this.timeTaskDao.deleteTimeTask(timeTask);
	}

}
