package com.takata.common.shiro;

import com.takata.system.user.domain.SystemUser;

/**
 * @author lzf
 **/

public class Principal {

    private Integer id;

    private String userCode;

    private String userName;

    private Integer companyId;

    public Principal(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.userCode = systemUser.getUserCode();
        this.userName = systemUser.getUserName();
        this.companyId = systemUser.getCompanyId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
