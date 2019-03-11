package com.success.system.holiday.service.impl;

import com.success.system.holiday.dao.SystemHoliday2Dao;
import com.success.system.holiday.domain.SystemHoliday;
import com.success.system.holiday.query.SystemHolidayQuery;
import com.success.system.holiday.service.SystemHoliday2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/
@Service("systemHoliday2Service")
public class SystemHoliday2ServiceImpl implements SystemHoliday2Service {

    @Resource(name = "systemHoliday2Dao")
    private SystemHoliday2Dao systemHolidayDao;

    @Override
    public Integer deleteSystemHoliday(SystemHoliday systemHoliday) {
        return this.systemHolidayDao.deleteSystemHoliday(systemHoliday);
    }

    @Override
    public Integer addSystemHoliday(SystemHoliday systemHoliday) {
        return this.systemHolidayDao.insertSystemHoliday(systemHoliday);
    }

    @Override
    public List<SystemHoliday> listSystemHolidayPage(SystemHolidayQuery systemHolidayQuery) throws Exception {
        return this.systemHolidayDao.selectSystemHolidayPageList(systemHolidayQuery);
    }

    @Override
    public Integer countSystemHoliday(SystemHolidayQuery systemHolidayQuery) throws Exception {
        return this.systemHolidayDao.selectSystemHolidayCount(systemHolidayQuery);
    }

    @Override
    public List<SystemHoliday> listSystemHoliday(SystemHolidayQuery systemHolidayQuery) throws Exception {
        return this.systemHolidayDao.selectSystemHolidayList(systemHolidayQuery);
    }
}
