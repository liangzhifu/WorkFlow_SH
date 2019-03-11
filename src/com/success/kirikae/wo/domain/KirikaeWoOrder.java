package com.success.kirikae.wo.domain;

import java.util.Date;

/**
 * @author lzf
 **/

public class KirikaeWoOrder {

    private Integer id;

    private Integer orderId;

    private Integer parentOrgId;

    private String parentOrgName;

    private Integer orgId;

    private String orgName;

    private Integer confirmBy;

    private Date confirmTime;

    private Integer woOrderState;

    private Date createTime;

    private Date sendMailTime;

    private Date startTime;

    private Integer deleteState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getConfirmBy() {
        return confirmBy;
    }

    public void setConfirmBy(Integer confirmBy) {
        this.confirmBy = confirmBy;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getWoOrderState() {
        return woOrderState;
    }

    public void setWoOrderState(Integer woOrderState) {
        this.woOrderState = woOrderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendMailTime() {
        return sendMailTime;
    }

    public void setSendMailTime(Date sendMailTime) {
        this.sendMailTime = sendMailTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }
}
