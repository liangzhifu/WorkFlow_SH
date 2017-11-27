package com.takata.system.org.query;

/**
 * @author lzf
 **/

public class SystemOrgQuery {

    //分公司ID
    private Integer companyId;

    //路径
    private String orgPathCode;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getOrgPathCode() {
        return orgPathCode;
    }

    public void setOrgPathCode(String orgPathCode) {
        this.orgPathCode = orgPathCode;
    }
}
