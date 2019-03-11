package com.success.system.org.query;

/**
 * @author lzf
 **/
public class SystemOrgQuery {

    /**
     * 路径
     */
    private String orgPathCode;

    /**
     * 组织类型
     */
    private Integer orgType;

    public String getOrgPathCode() {
        return orgPathCode;
    }

    public void setOrgPathCode(String orgPathCode) {
        this.orgPathCode = orgPathCode;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }
}
