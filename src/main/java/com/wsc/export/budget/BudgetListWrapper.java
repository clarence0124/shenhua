package com.wsc.export.budget;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/21.
 */
public class BudgetListWrapper {

    private String id;
    private String code;
    private String name;
    private String pid;
    private String monthly;
    private Double preTender;
    private Double bidPrice;
    private Double bcost;
    private Double workAmount;
    private String workUnit;
    private String contractName;
    private Date startDate;
    private Date completionDate;
    private String firstPartySigner;
    private Date singedDate;
    private String formOfPric;
    private String remark;
    private Double measureAmount;
    private Double registrationAmount;
    private Double taxAmount;
    private Double otherAmount;

    private BudgetDetailWrapper budget;

    public BudgetDetailWrapper getBudget() {
        return budget;
    }

    public void setBudget(BudgetDetailWrapper budget) {
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public Double getPreTender() {
        return preTender;
    }

    public void setPreTender(Double preTender) {
        this.preTender = preTender;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getBcost() {
        return bcost;
    }

    public void setBcost(Double bcost) {
        this.bcost = bcost;
    }

    public Double getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(Double workAmount) {
        this.workAmount = workAmount;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getFirstPartySigner() {
        return firstPartySigner;
    }

    public void setFirstPartySigner(String firstPartySigner) {
        this.firstPartySigner = firstPartySigner;
    }

    public Date getSingedDate() {
        return singedDate;
    }

    public void setSingedDate(Date singedDate) {
        this.singedDate = singedDate;
    }

    public String getFormOfPric() {
        return formOfPric;
    }

    public void setFormOfPric(String formOfPric) {
        this.formOfPric = formOfPric;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getMeasureAmount() {
        return measureAmount;
    }

    public void setMeasureAmount(Double measureAmount) {
        this.measureAmount = measureAmount;
    }

    public Double getRegistrationAmount() {
        return registrationAmount;
    }

    public void setRegistrationAmount(Double registrationAmount) {
        this.registrationAmount = registrationAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(Double otherAmount) {
        this.otherAmount = otherAmount;
    }
}
