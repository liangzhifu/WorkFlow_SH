package com.success.system.holiday.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lzf
 **/
public class SystemHolidayQuery {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holidayStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holidayEnd;

    private Integer start;

    private Integer size;

    public Date getHolidayStart() {
        return holidayStart;
    }

    public void setHolidayStart(Date holidayStart) {
        this.holidayStart = holidayStart;
    }

    public Date getHolidayEnd() {
        return holidayEnd;
    }

    public void setHolidayEnd(Date holidayEnd) {
        this.holidayEnd = holidayEnd;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
