package com.success.system.holiday.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lzf
 **/
public class SystemHoliday {

    private Integer id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holiday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHoliday() {
        return holiday;
    }

    public void setHoliday(Date holiday) {
        this.holiday = holiday;
    }
}
