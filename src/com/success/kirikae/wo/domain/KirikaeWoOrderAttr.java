package com.success.kirikae.wo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lzf
 **/

public class KirikaeWoOrderAttr {

    private Integer id;

    private Integer woOrderId;

    private Integer fileState;

    private Date fileConfirmTime;

    private Integer fileId;

    private Integer agreementState;

    private Date agreementTime;

    private String agreementResult;

    private Integer questionId;

    private String reviewResult;

    private String reviewPrinciple;

    private Date reviewTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date changeCompleteTime;

    private Date preparedTime;

    private Integer preparedUser;

    private Integer preparedState;

    private Integer deleteState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String agreementValidResult;

    private Date agreementValidTime;

    private Integer agreementValidState;

    private Date agreementEmailTime;

    private String refuseReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWoOrderId() {
        return woOrderId;
    }

    public void setWoOrderId(Integer woOrderId) {
        this.woOrderId = woOrderId;
    }

    public Integer getFileState() {
        return fileState;
    }

    public void setFileState(Integer fileState) {
        this.fileState = fileState;
    }

    public Date getFileConfirmTime() {
        return fileConfirmTime;
    }

    public void setFileConfirmTime(Date fileConfirmTime) {
        this.fileConfirmTime = fileConfirmTime;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getAgreementState() {
        return agreementState;
    }

    public void setAgreementState(Integer agreementState) {
        this.agreementState = agreementState;
    }

    public Date getAgreementTime() {
        return agreementTime;
    }

    public void setAgreementTime(Date agreementTime) {
        this.agreementTime = agreementTime;
    }

    public String getAgreementResult() {
        return agreementResult;
    }

    public void setAgreementResult(String agreementResult) {
        this.agreementResult = agreementResult == null ? null : agreementResult.trim();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult == null ? null : reviewResult.trim();
    }

    public String getReviewPrinciple() {
        return reviewPrinciple;
    }

    public void setReviewPrinciple(String reviewPrinciple) {
        this.reviewPrinciple = reviewPrinciple == null ? null : reviewPrinciple.trim();
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getChangeCompleteTime() {
        return changeCompleteTime;
    }

    public void setChangeCompleteTime(Date changeCompleteTime) {
        this.changeCompleteTime = changeCompleteTime;
    }

    public Date getPreparedTime() {
        return preparedTime;
    }

    public void setPreparedTime(Date preparedTime) {
        this.preparedTime = preparedTime;
    }

    public Integer getPreparedUser() {
        return preparedUser;
    }

    public void setPreparedUser(Integer preparedUser) {
        this.preparedUser = preparedUser;
    }

    public Integer getPreparedState() {
        return preparedState;
    }

    public void setPreparedState(Integer preparedState) {
        this.preparedState = preparedState;
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

    public String getAgreementValidResult() {
        return agreementValidResult;
    }

    public void setAgreementValidResult(String agreementValidResult) {
        this.agreementValidResult = agreementValidResult;
    }

    public Date getAgreementValidTime() {
        return agreementValidTime;
    }

    public void setAgreementValidTime(Date agreementValidTime) {
        this.agreementValidTime = agreementValidTime;
    }

    public Integer getAgreementValidState() {
        return agreementValidState;
    }

    public void setAgreementValidState(Integer agreementValidState) {
        this.agreementValidState = agreementValidState;
    }

    public Date getAgreementEmailTime() {
        return agreementEmailTime;
    }

    public void setAgreementEmailTime(Date agreementEmailTime) {
        this.agreementEmailTime = agreementEmailTime;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
