package com.success.kirikae.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
public class KirikaeOrder {

    private Integer id;

    private Integer orderId;

    private String tkNo;

    private String designChangeNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date salesDate;

    private String salesApproved;

    private String salesChecked;

    private String salesPrepared;

    private Integer salesCustomerProtocal;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date engineeringData;

    private String engineeringApproved;

    private String engineeringChecked;

    private String engineeringPrepared;

    private String customer;

    private String vehicleName;

    private String destination;

    private Integer isirProcessing;

    private Integer customerEngineering;

    private Integer customerEngineeringApproval;

    private Integer regulation;

    private Integer regulationApproval;

    private String interchangeabilityOld;

    private String interchangeabilityNew;

    private Integer serviceSuplied;

    private Integer designCosts;

    private Integer designCostsPay;

    private Integer customerEo;

    private Integer partsNumberChange;

    private Integer presenceRequired;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date designChangeTiming;

    private Integer desingChangeType;

    private String manufactureInternal;

    private String manufacturePrepared;

    private Integer kirikaeOrderType;

    private Integer kirikaeOrderState;

    private String distributionDepartment;

    private Integer dpcoiOrderId;

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

    public String getTkNo() {
        return tkNo;
    }

    public void setTkNo(String tkNo) {
        this.tkNo = tkNo;
    }

    public String getDesignChangeNo() {
        return designChangeNo;
    }

    public void setDesignChangeNo(String designChangeNo) {
        this.designChangeNo = designChangeNo;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public String getSalesApproved() {
        return salesApproved;
    }

    public void setSalesApproved(String salesApproved) {
        this.salesApproved = salesApproved;
    }

    public String getSalesChecked() {
        return salesChecked;
    }

    public void setSalesChecked(String salesChecked) {
        this.salesChecked = salesChecked;
    }

    public String getSalesPrepared() {
        return salesPrepared;
    }

    public void setSalesPrepared(String salesPrepared) {
        this.salesPrepared = salesPrepared;
    }

    public Integer getSalesCustomerProtocal() {
        return salesCustomerProtocal;
    }

    public void setSalesCustomerProtocal(Integer salesCustomerProtocal) {
        this.salesCustomerProtocal = salesCustomerProtocal;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getEngineeringData() {
        return engineeringData;
    }

    public void setEngineeringData(Date engineeringData) {
        this.engineeringData = engineeringData;
    }

    public String getEngineeringApproved() {
        return engineeringApproved;
    }

    public void setEngineeringApproved(String engineeringApproved) {
        this.engineeringApproved = engineeringApproved;
    }

    public String getEngineeringChecked() {
        return engineeringChecked;
    }

    public void setEngineeringChecked(String engineeringChecked) {
        this.engineeringChecked = engineeringChecked;
    }

    public String getEngineeringPrepared() {
        return engineeringPrepared;
    }

    public void setEngineeringPrepared(String engineeringPrepared) {
        this.engineeringPrepared = engineeringPrepared;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getIsirProcessing() {
        return isirProcessing;
    }

    public void setIsirProcessing(Integer isirProcessing) {
        this.isirProcessing = isirProcessing;
    }

    public Integer getCustomerEngineering() {
        return customerEngineering;
    }

    public void setCustomerEngineering(Integer customerEngineering) {
        this.customerEngineering = customerEngineering;
    }

    public Integer getCustomerEngineeringApproval() {
        return customerEngineeringApproval;
    }

    public void setCustomerEngineeringApproval(Integer customerEngineeringApproval) {
        this.customerEngineeringApproval = customerEngineeringApproval;
    }

    public Integer getRegulation() {
        return regulation;
    }

    public void setRegulation(Integer regulation) {
        this.regulation = regulation;
    }

    public Integer getRegulationApproval() {
        return regulationApproval;
    }

    public void setRegulationApproval(Integer regulationApproval) {
        this.regulationApproval = regulationApproval;
    }

    public String getInterchangeabilityOld() {
        return interchangeabilityOld;
    }

    public void setInterchangeabilityOld(String interchangeabilityOld) {
        this.interchangeabilityOld = interchangeabilityOld;
    }

    public String getInterchangeabilityNew() {
        return interchangeabilityNew;
    }

    public void setInterchangeabilityNew(String interchangeabilityNew) {
        this.interchangeabilityNew = interchangeabilityNew;
    }

    public Integer getServiceSuplied() {
        return serviceSuplied;
    }

    public void setServiceSuplied(Integer serviceSuplied) {
        this.serviceSuplied = serviceSuplied;
    }

    public Integer getDesignCosts() {
        return designCosts;
    }

    public void setDesignCosts(Integer designCosts) {
        this.designCosts = designCosts;
    }

    public Integer getDesignCostsPay() {
        return designCostsPay;
    }

    public void setDesignCostsPay(Integer designCostsPay) {
        this.designCostsPay = designCostsPay;
    }

    public Integer getCustomerEo() {
        return customerEo;
    }

    public void setCustomerEo(Integer customerEo) {
        this.customerEo = customerEo;
    }

    public Integer getPartsNumberChange() {
        return partsNumberChange;
    }

    public void setPartsNumberChange(Integer partsNumberChange) {
        this.partsNumberChange = partsNumberChange;
    }

    public Integer getPresenceRequired() {
        return presenceRequired;
    }

    public void setPresenceRequired(Integer presenceRequired) {
        this.presenceRequired = presenceRequired;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDesignChangeTiming() {
        return designChangeTiming;
    }

    public void setDesignChangeTiming(Date designChangeTiming) {
        this.designChangeTiming = designChangeTiming;
    }

    public Integer getDesingChangeType() {
        return desingChangeType;
    }

    public void setDesingChangeType(Integer desingChangeType) {
        this.desingChangeType = desingChangeType;
    }

    public String getManufactureInternal() {
        return manufactureInternal;
    }

    public void setManufactureInternal(String manufactureInternal) {
        this.manufactureInternal = manufactureInternal;
    }

    public String getManufacturePrepared() {
        return manufacturePrepared;
    }

    public void setManufacturePrepared(String manufacturePrepared) {
        this.manufacturePrepared = manufacturePrepared;
    }

    public Integer getKirikaeOrderType() {
        return kirikaeOrderType;
    }

    public void setKirikaeOrderType(Integer kirikaeOrderType) {
        this.kirikaeOrderType = kirikaeOrderType;
    }

    public Integer getKirikaeOrderState() {
        return kirikaeOrderState;
    }

    public void setKirikaeOrderState(Integer kirikaeOrderState) {
        this.kirikaeOrderState = kirikaeOrderState;
    }

    public String getDistributionDepartment() {
        return distributionDepartment;
    }

    public void setDistributionDepartment(String distributionDepartment) {
        this.distributionDepartment = distributionDepartment;
    }

    public Integer getDpcoiOrderId() {
        return dpcoiOrderId;
    }

    public void setDpcoiOrderId(Integer dpcoiOrderId) {
        this.dpcoiOrderId = dpcoiOrderId;
    }
}
