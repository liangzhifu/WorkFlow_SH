package com.takata.system.holiday.service.impl;

import com.takata.system.holiday.dao.SystemHolidayDao;
import com.takata.system.holiday.domain.SystemHoliday;
import com.takata.system.holiday.query.SystemHolidayQuery;
import com.takata.system.holiday.service.SystemHolidayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("systemHolidayService")
public class SystemHolidayServiceImpl implements SystemHolidayService {

    @Resource(name = "systemHolidayDao")
    private SystemHolidayDao systemHolidayDao;

    @Override
    public Integer deleteSystemHoliday(SystemHoliday systemHoliday) {
        return this.systemHolidayDao.deleteSystemHoliday(systemHoliday);
    }

    @Override
    public Integer addSystemHoliday(SystemHoliday systemHoliday) {
        return this.systemHolidayDao.insertSystemHoliday(systemHoliday);
    }

    @Override
    public List<Map<String, Object>> querySystemHolidayListPage(SystemHolidayQuery systemHolidayQuery) throws ServiceException {
        return this.systemHolidayDao.selectSystemHolidayListPage(systemHolidayQuery);
    }

    @Override
    public Integer querySystemHolidayCount(SystemHolidayQuery systemHolidayQuery) throws ServiceException {
        return this.systemHolidayDao.selectSystemHolidayCount(systemHolidayQuery);
    }
}
