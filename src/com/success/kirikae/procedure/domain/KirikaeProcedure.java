package com.success.kirikae.procedure.domain;

/**
 * @author lzf
 **/
public class KirikaeProcedure {

    private Integer id;

    private Integer templateId;

    private Integer procedureSeq;

    private Integer procedureCode;

    private Integer procedureType;

    private String procedureName;

    private Integer deleteState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getProcedureSeq() {
        return procedureSeq;
    }

    public void setProcedureSeq(Integer procedureSeq) {
        this.procedureSeq = procedureSeq;
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

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }
}
