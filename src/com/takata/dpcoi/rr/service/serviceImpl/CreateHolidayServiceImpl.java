package com.takata.dpcoi.rr.service.serviceImpl;

import com.takata.system.holiday.domain.SystemHoliday;
import com.takata.system.holiday.query.SystemHolidayQuery;
import com.takata.system.holiday.service.SystemHolidayService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lzf
 **/

@Component("createHolidayServiceImpl")
public class CreateHolidayServiceImpl {

    @Resource(name="systemHolidayService")
    private SystemHolidayService systemHolidayService;

    @Scheduled(cron = "0 0 1 1 * ?")
    public void job(){
        try {
            Date nowDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowDate);
            cal.add(Calendar.MONTH, 3);
            int maxDay = cal.getActualMaximum(Calendar.DATE);
            for(int i = 0; i < maxDay; i++){
                if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
                    Date date = cal.getTime();
                    SystemHolidayQuery holidayQuery = new SystemHolidayQuery();
                    holidayQuery.setHolidayStart(date);
                    holidayQuery.setHolidayEnd(date);
                    Integer num = this.systemHolidayService.querySystemHolidayCount(holidayQuery);
                    if(num == 0){
                        SystemHoliday holiday = new SystemHoliday();
                        holiday.setHoliday(date);
                        this.systemHolidayService.addSystemHoliday(holiday);
                    }
                }
                cal.add(Calendar.DATE, 1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
