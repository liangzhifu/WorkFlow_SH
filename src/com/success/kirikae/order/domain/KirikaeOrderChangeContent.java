package com.success.kirikae.order.domain;

import java.util.Date;

/**
 * 切替单设变内容
 *
 * @author lzf
 **/
public class KirikaeOrderChangeContent {

    private Integer id;

    private String beforeChange;

    private String afterChange;

    private Integer beforeFileId;

    private String beforeFileName;

    private Integer newFileId;

    private String newFileName;

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

    public String getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(String beforeChange) {
        this.beforeChange = beforeChange;
    }

    public String getAfterChange() {
        return afterChange;
    }

    public void setAfterChange(String afterChange) {
        this.afterChange = afterChange;
    }

    public Integer getBeforeFileId() {
        return beforeFileId;
    }

    public void setBeforeFileId(Integer beforeFileId) {
        this.beforeFileId = beforeFileId;
    }

    public String getBeforeFileName() {
        return beforeFileName;
    }

    public void setBeforeFileName(String beforeFileName) {
        this.beforeFileName = beforeFileName;
    }

    public Integer getNewFileId() {
        return newFileId;
    }

    public void setNewFileId(Integer newFileId) {
        this.newFileId = newFileId;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
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
