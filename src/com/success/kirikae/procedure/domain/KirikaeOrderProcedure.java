package com.success.kirikae.procedure.domain;

import java.util.Date;

/**
 * @author lzf
 **/

public class KirikaeOrderProcedure {

    private Integer id;

    private Integer orderId;

    private Integer procedureState;

    private Integer procedureSeq;

    private Integer deleteState;

    private Integer procedureCode;

    private Integer procedureType;

    private Integer procedureBy;

    private Date procedureTime;

    private Date createTime;

    private Date startTime;

    private String remark;

    private Integer fileId;

    private String fileName;

    private String spareColumn;

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

    public Integer getProcedureState() {
        return procedureState;
    }

    public void setProcedureState(Integer procedureState) {
        this.procedureState = procedureState;
    }

    public Integer getProcedureSeq() {
        return procedureSeq;
    }

    public void setProcedureSeq(Integer procedureSeq) {
        this.procedureSeq = procedureSeq;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(Integer procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Integer getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(Integer procedureType) {
        this.procedureType = procedureType;
    }

    public Integer getProcedureBy() {
        return procedureBy;
    }

    public void setProcedureBy(Integer procedureBy) {
        this.procedureBy = procedureBy;
    }

    public Date getProcedureTime() {
        return procedureTime;
    }

    public void setProcedureTime(Date procedureTime) {
        this.procedureTime = procedureTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getSpareColumn() {
        return spareColumn;
    }

    public void setSpareColumn(String spareColumn) {
        this.spareColumn = spareColumn;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
