package com.success.kirikae.question.query;

/**
 * @author lzf
 **/
public class KirikaeQuestionQuery {

    private Integer start;

    private Integer size;

    //确认项目
    private String confirmProject;

    //确认内容
    private String confirmContent;

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
