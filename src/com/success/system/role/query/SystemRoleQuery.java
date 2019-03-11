package com.success.system.role.query;

/**
 * @author lzf
 **/
public class SystemRoleQuery {

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
    private String roleName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
