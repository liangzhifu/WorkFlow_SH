package com.success.kirikae.order.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lzf
 **/
public class KirikaeOrderQuery {

    private Integer start;

    private Integer size;

    private String tkNo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date designChangeTimingFrom;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date designChangeTimingTo;

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

    public String getTkNo() {
        return tkNo;
    }

    public void setTkNo(String tkNo) {
        this.tkNo = tkNo;
    }

    public Date getDesignChangeTimingFrom() {
        return designChangeTimingFrom;
    }

    public void setDesignChangeTimingFrom(Date designChangeTimingFrom) {
        this.designChangeTimingFrom = designChangeTimingFrom;
    }

    public Date getDesignChangeTimingTo() {
        return designChangeTimingTo;
    }

    public void setDesignChangeTimingTo(Date designChangeTimingTo) {
        this.designChangeTimingTo = designChangeTimingTo;
    }
}
