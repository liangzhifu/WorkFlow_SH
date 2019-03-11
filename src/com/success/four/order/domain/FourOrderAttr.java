package com.success.four.order.domain;

/**
 * @author lzf
 **/

public class FourOrderAttr {

    private Integer id;

    private Integer orderId;

    private Integer attrId;

    private Integer attrChecked;

    private String attrValue;

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

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getAttrChecked() {
        return attrChecked;
    }

    public void setAttrChecked(Integer attrChecked) {
        this.attrChecked = attrChecked;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

}
