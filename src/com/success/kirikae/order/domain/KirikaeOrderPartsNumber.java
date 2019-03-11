package com.success.kirikae.order.domain;

import java.util.Date;

/**
 * 切替单品号变更
 *
 * @author lzf
 **/
public class KirikaeOrderPartsNumber {

    private Integer id;

    private String oldPartsNumber;

    private String newPattsNumber;

    private Integer deleteState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPartsNumber() {
        return oldPartsNumber;
    }

    public void setOldPartsNumber(String oldPartsNumber) {
        this.oldPartsNumber = oldPartsNumber;
    }

    public String getNewPattsNumber() {
        return newPattsNumber;
    }

    public void setNewPattsNumber(String newPattsNumber) {
        this.newPattsNumber = newPattsNumber;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
