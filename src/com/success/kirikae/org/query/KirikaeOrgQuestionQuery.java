package com.success.kirikae.org.query;

/**
 * @author lzf
 **/
public class KirikaeOrgQuestionQuery {

    private Integer orgId;

    private Integer questionId;

    private String confirmProject;

    private String confirmContent;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getConfirmProject() {
        return confirmProject;
    }

    public void setConfirmProject(String confirmProject) {
        this.confirmProject = confirmProject;
    }

    public String getConfirmContent() {
        return confirmContent;
    }

    public void setConfirmContent(String confirmContent) {
        this.confirmContent = confirmContent;
    }

}
