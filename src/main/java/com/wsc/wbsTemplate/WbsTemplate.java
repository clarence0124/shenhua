package com.wsc.wbsTemplate;

/**
 * Created by Administrator on 2016/12/22.
 */
public class WbsTemplate {

    private Integer id;
    private Integer tempId;
    private Integer pid;
    private String code;
    private String outlineNo;
    private String name;
    private String materialFlag;
    private String civilFlag;
    private String installFlag;
    private String equipmentFlag;
    private String feeFlag;
    private String otherFlag;
    private String unit;
    private Integer brotherOrderNo;
    private String leaf;
    private Integer nodeDepth;

    private String state = "open";
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getMaterialFlag() {
        return materialFlag;
    }

    public void setMaterialFlag(String materialFlag) {
        this.materialFlag = materialFlag;
    }

    public String getCivilFlag() {
        return civilFlag;
    }

    public void setCivilFlag(String civilFlag) {
        this.civilFlag = civilFlag;
    }

    public String getInstallFlag() {
        return installFlag;
    }

    public void setInstallFlag(String installFlag) {
        this.installFlag = installFlag;
    }

    public String getEquipmentFlag() {
        return equipmentFlag;
    }

    public void setEquipmentFlag(String equipmentFlag) {
        this.equipmentFlag = equipmentFlag;
    }

    public String getFeeFlag() {
        return feeFlag;
    }

    public void setFeeFlag(String feeFlag) {
        this.feeFlag = feeFlag;
    }

    public String getOtherFlag() {
        return otherFlag;
    }

    public void setOtherFlag(String otherFlag) {
        this.otherFlag = otherFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getBrotherOrderNo() {
        return brotherOrderNo;
    }

    public void setBrotherOrderNo(Integer brotherOrderNo) {
        this.brotherOrderNo = brotherOrderNo;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public Integer getNodeDepth() {
        return nodeDepth;
    }

    public void setNodeDepth(Integer nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
