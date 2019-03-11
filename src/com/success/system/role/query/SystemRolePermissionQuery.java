package com.success.system.role.query;

/**
 * @author lzf
 **/
public class SystemRolePermissionQuery {

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
    private Integer roleId;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
