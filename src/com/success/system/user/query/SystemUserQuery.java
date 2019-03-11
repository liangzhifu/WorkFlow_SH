package com.success.system.user.query;

/**
 * @author lzf
 **/
public class SystemUserQuery {

    /**
     * 分页使用，开始位置
     */
    private Integer start;

    /**
     * 分页使用，数量
     */
    private Integer size;

    /**
     * 工号
     */
    private String userCode;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 分公司ID
     */
    private Integer companyId;

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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
