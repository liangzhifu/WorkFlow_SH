package com.success.four.order.domain;

/**
 * @author lzf
 **/

public class FourOrder {

    private Integer id;

    private Integer orderId;

    private String changeAfterProductNo;

    private String changeBeforProductNo;

    private String realChangeTime;

    private String estimateChangeTime;

    private String releaseDate;

    private String issueNumber;

    private String productLine;

    private String carName;

    private String installationMat;

    private String changeContent;

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

    public String getChangeAfterProductNo() {
        return changeAfterProductNo;
    }

    public void setChangeAfterProductNo(String changeAfterProductNo) {
        this.changeAfterProductNo = changeAfterProductNo == null ? null : changeAfterProductNo.trim();
    }

    public String getChangeBeforProductNo() {
        return changeBeforProductNo;
    }

    public void setChangeBeforProductNo(String changeBeforProductNo) {
        this.changeBeforProductNo = changeBeforProductNo == null ? null : changeBeforProductNo.trim();
    }

    public String getRealChangeTime() {
        return realChangeTime;
    }

    public void setRealChangeTime(String realChangeTime) {
        this.realChangeTime = realChangeTime == null ? null : realChangeTime.trim();
    }

    public String getEstimateChangeTime() {
        return estimateChangeTime;
    }

    public void setEstimateChangeTime(String estimateChangeTime) {
        this.estimateChangeTime = estimateChangeTime == null ? null : estimateChangeTime.trim();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate == null ? null : releaseDate.trim();
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber == null ? null : issueNumber.trim();
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine == null ? null : productLine.trim();
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public String getInstallationMat() {
        return installationMat;
    }

    public void setInstallationMat(String installationMat) {
        this.installationMat = installationMat == null ? null : installationMat.trim();
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }
}
