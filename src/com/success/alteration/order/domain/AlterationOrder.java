package com.success.alteration.order.domain;

import com.success.four.order.domain.FourOrder;
import com.success.four.order.domain.FourOrderAttr;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.domain.KirikaeResume;

import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/

public class AlterationOrder {

    private Integer id;

    private Integer orderChannel;

    private Integer generateFour;

    private Integer deleteState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String orderState;

    private String invalidateText;

    private KirikaeOrder kirikaeOrder;

    private List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList;

    private List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList;

    private List<KirikaeResume> kirikaeResumeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getInvalidateText() {
        return invalidateText;
    }

    public void setInvalidateText(String invalidateText) {
        this.invalidateText = invalidateText;
    }

    public KirikaeOrder getKirikaeOrder() {
        return kirikaeOrder;
    }

    public void setKirikaeOrder(KirikaeOrder kirikaeOrder) {
        this.kirikaeOrder = kirikaeOrder;
    }

    public List<KirikaeOrderChangeContent> getKirikaeOrderChangeContentList() {
        return kirikaeOrderChangeContentList;
    }

    public void setKirikaeOrderChangeContentList(List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList) {
        this.kirikaeOrderChangeContentList = kirikaeOrderChangeContentList;
    }

    public List<KirikaeOrderPartsNumber> getKirikaeOrderPartsNumberList() {
        return kirikaeOrderPartsNumberList;
    }

    public void setKirikaeOrderPartsNumberList(List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList) {
        this.kirikaeOrderPartsNumberList = kirikaeOrderPartsNumberList;
    }

    public List<KirikaeResume> getKirikaeResumeList() {
        return kirikaeResumeList;
    }

    public void setKirikaeResumeList(List<KirikaeResume> kirikaeResumeList) {
        this.kirikaeResumeList = kirikaeResumeList;
    }

    public Integer getGenerateFour() {
        return generateFour;
    }

    public void setGenerateFour(Integer generateFour) {
        this.generateFour = generateFour;
    }
}
