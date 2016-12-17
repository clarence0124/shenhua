package com.wsc;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/17.
 */
public class EstimateListWrapper {

    private String id;
    private Integer subprojId;
    private Integer tempId;
    private BigDecimal mineSum;
    private BigDecimal civilSum;
    private BigDecimal installSum;
    private BigDecimal equipmentSum;
    private BigDecimal feeSum;
    private BigDecimal otherSum;
    private String designCompany;
    private String qualificationNo;
    private BigDecimal totalSum;
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

    public BigDecimal getMineSum() {
        return mineSum;
    }

    public void setMineSum(BigDecimal mineSum) {
        this.mineSum = mineSum;
    }

    public BigDecimal getCivilSum() {
        return civilSum;
    }

    public void setCivilSum(BigDecimal civilSum) {
        this.civilSum = civilSum;
    }

    public BigDecimal getInstallSum() {
        return installSum;
    }

    public void setInstallSum(BigDecimal installSum) {
        this.installSum = installSum;
    }

    public BigDecimal getEquipmentSum() {
        return equipmentSum;
    }

    public void setEquipmentSum(BigDecimal equipmentSum) {
        this.equipmentSum = equipmentSum;
    }

    public BigDecimal getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(BigDecimal feeSum) {
        this.feeSum = feeSum;
    }

    public BigDecimal getOtherSum() {
        return otherSum;
    }

    public void setOtherSum(BigDecimal otherSum) {
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

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
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
