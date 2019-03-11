package com.success.kirikae.instruction.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 切替指示书
 *
 * @author lzf
 **/

public class KirikaeInstruction {

    private Integer id;

    private Integer orderId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderKirikae;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date customerKirikae;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    private String factory;

    private String productionBatch;

    private Integer customerDesignChangeNotification;

    private Integer customerTechnologyApproval;

    private Integer certificationApproval;

    private Integer isirProcession;

    private Integer isirMarking;

    private Integer isirNotificationIssued;

    private Integer createBy;

    private Date createTime;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getOrderKirikae() {
        return orderKirikae;
    }

    public void setOrderKirikae(Date orderKirikae) {
        this.orderKirikae = orderKirikae;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getCustomerKirikae() {
        return customerKirikae;
    }

    public void setCustomerKirikae(Date customerKirikae) {
        this.customerKirikae = customerKirikae;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public Integer getCustomerDesignChangeNotification() {
        return customerDesignChangeNotification;
    }

    public void setCustomerDesignChangeNotification(Integer customerDesignChangeNotification) {
        this.customerDesignChangeNotification = customerDesignChangeNotification;
    }

    public Integer getCustomerTechnologyApproval() {
        return customerTechnologyApproval;
    }

    public void setCustomerTechnologyApproval(Integer customerTechnologyApproval) {
        this.customerTechnologyApproval = customerTechnologyApproval;
    }

    public Integer getCertificationApproval() {
        return certificationApproval;
    }

    public void setCertificationApproval(Integer certificationApproval) {
        this.certificationApproval = certificationApproval;
    }

    public Integer getIsirProcession() {
        return isirProcession;
    }

    public void setIsirProcession(Integer isirProcession) {
        this.isirProcession = isirProcession;
    }

    public Integer getIsirMarking() {
        return isirMarking;
    }

    public void setIsirMarking(Integer isirMarking) {
        this.isirMarking = isirMarking;
    }

    public Integer getIsirNotificationIssued() {
        return isirNotificationIssued;
    }

    public void setIsirNotificationIssued(Integer isirNotificationIssued) {
        this.isirNotificationIssued = isirNotificationIssued;
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

    public String getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(String productionBatch) {
        this.productionBatch = productionBatch;
    }
}
