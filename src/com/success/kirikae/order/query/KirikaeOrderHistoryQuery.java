package com.success.kirikae.order.query;

/**
 * @author lzf
 **/

public class KirikaeOrderHistoryQuery {

    private Integer start;

    private Integer size;

    private String tkNo;

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
}
