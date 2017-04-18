package com.wsc.estimate;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/17.
 */
public class EstimateListWrapper {

    private String id;
    private Integer subprojId;
    private Integer tempId;
    private Double mineSum = 0d;
    private Double civilSum = 0d;
    private Double installSum = 0d;
    private Double equipmentSum = 0d;
    private Double feeSum = 0d;
    private Double otherSum = 0d;
    private String designCompany;
    private String qualificationNo;
    private Double totalSum = 0d;
    private String estimator;
    private String rptUserName;
    private Date rptDate;
    private String approvalScale;
    private Date recordCreateTime;

    private EstimateDetailWrapper estimateDetail;

    public EstimateDetailWrapper getEstimateDetail() {
        return estimateDetail;
    }

    public void setEstimateDetail(EstimateDetailWrapper estimateDetail) {
        this.estimateDetail = estimateDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSubprojId() {
        return subprojId;
    }

    public void setSubprojId(Integer subprojId) {
        this.subprojId = subprojId;
    }

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public Double getMineSum() {
        return mineSum;
    }

    public void setMineSum(Double mineSum) {
        this.mineSum = mineSum;
    }

    public Double getCivilSum() {
        return civilSum;
    }

    public void setCivilSum(Double civilSum) {
        this.civilSum = civilSum;
    }

    public Double getInstallSum() {
        return installSum;
    }

    public void setInstallSum(Double installSum) {
        this.installSum = installSum;
    }

    public Double getEquipmentSum() {
        return equipmentSum;
    }

    public void setEquipmentSum(Double equipmentSum) {
        this.equipmentSum = equipmentSum;
    }

    public Double getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(Double feeSum) {
        this.feeSum = feeSum;
    }

    public Double getOtherSum() {
        return otherSum;
    }

    public void setOtherSum(Double otherSum) {
        this.otherSum = otherSum;
    }

    public String getDesignCompany() {
        return designCompany;
    }

    public void setDesignCompany(String designCompany) {
        this.designCompany = designCompany;
    }

    public String getQualificationNo() {
        return qualificationNo;
    }

    public void setQualificationNo(String qualificationNo) {
        this.qualificationNo = qualificationNo;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getEstimator() {
        return estimator;
    }

    public void setEstimator(String estimator) {
        this.estimator = estimator;
    }

    public String getRptUserName() {
        return rptUserName;
    }

    public void setRptUserName(String rptUserName) {
        this.rptUserName = rptUserName;
    }

    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getApprovalScale() {
        return approvalScale;
    }

    public void setApprovalScale(String approvalScale) {
        this.approvalScale = approvalScale;
    }

    public Date getRecordCreateTime() {
        return recordCreateTime;
    }

    public void setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
    }
}
