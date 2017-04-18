package com.wsc.estimate;

/**
 * Created by Administrator on 2016/12/17.
 */
public class EstimateDetail {
    private String id;
    private String pid;
    private String estimateId;
    private Integer brotherOrderNo;
    private Integer tempNodeId;
    private String tempNodeName;
    private String outlineNo;
    private String name;
    private String code;
    private String spec;
    private Double amount;
    private String unit;
    private String leafFlag;
    private Integer nodeDepth;
    private String nodePath;
    private String nodePathId;

    private Double indictaor = 0d;
    private Double mineEcost = 0d;
    private Double civilEcost = 0d;
    private Double equipmentEcost = 0d;
    private Double installEcost = 0d;
    private Double materialCost = 0d;
    private Double installCost = 0d;
    private Double emiSum = 0d;
    private Double feeEcost = 0d;
    private Double otherEcost = 0d;
    private String subitemName;
    private String subItemTempId;
    private String subItemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId;
    }

    public Integer getBrotherOrderNo() {
        return brotherOrderNo;
    }

    public void setBrotherOrderNo(Integer brotherOrderNo) {
        this.brotherOrderNo = brotherOrderNo;
    }

    public Integer getTempNodeId() {
        return tempNodeId;
    }

    public void setTempNodeId(Integer tempNodeId) {
        this.tempNodeId = tempNodeId;
    }

    public String getTempNodeName() {
        return tempNodeName;
    }

    public void setTempNodeName(String tempNodeName) {
        this.tempNodeName = tempNodeName;
    }

    public String getOutlineNo() {
        return outlineNo;
    }

    public void setOutlineNo(String outlineNo) {
        this.outlineNo = outlineNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(String leafFlag) {
        this.leafFlag = leafFlag;
    }

    public Integer getNodeDepth() {
        return nodeDepth;
    }

    public void setNodeDepth(Integer nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getNodePathId() {
        return nodePathId;
    }

    public void setNodePathId(String nodePathId) {
        this.nodePathId = nodePathId;
    }

    public Double getIndictaor() {
        return indictaor;
    }

    public void setIndictaor(Double indictaor) {
        this.indictaor = indictaor;
    }

    public Double getMineEcost() {
        return mineEcost;
    }

    public void setMineEcost(Double mineEcost) {
        this.mineEcost = mineEcost;
    }

    public Double getCivilEcost() {
        return civilEcost;
    }

    public void setCivilEcost(Double civilEcost) {
        this.civilEcost = civilEcost;
    }

    public Double getEquipmentEcost() {
        return equipmentEcost;
    }

    public void setEquipmentEcost(Double equipmentEcost) {
        this.equipmentEcost = equipmentEcost;
    }

    public Double getInstallEcost() {
        return installEcost;
    }

    public void setInstallEcost(Double installEcost) {
        this.installEcost = installEcost;
    }

    public Double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }

    public Double getInstallCost() {
        return installCost;
    }

    public void setInstallCost(Double installCost) {
        this.installCost = installCost;
    }

    public Double getEmiSum() {
        return emiSum;
    }

    public void setEmiSum(Double emiSum) {
        this.emiSum = emiSum;
    }

    public Double getFeeEcost() {
        return feeEcost;
    }

    public void setFeeEcost(Double feeEcost) {
        this.feeEcost = feeEcost;
    }

    public Double getOtherEcost() {
        return otherEcost;
    }

    public void setOtherEcost(Double otherEcost) {
        this.otherEcost = otherEcost;
    }

    public String getSubitemName() {
        return subitemName;
    }

    public void setSubitemName(String subitemName) {
        this.subitemName = subitemName;
    }

    public String getSubItemTempId() {
        return subItemTempId;
    }

    public void setSubItemTempId(String subItemTempId) {
        this.subItemTempId = subItemTempId;
    }

    public String getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(String subItemId) {
        this.subItemId = subItemId;
    }
}
