package com.wsc;

import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ProjectStructure {

    private Integer id;
    private String nodeName;
    private String nodeCode;
    private Integer projectInfoId;

    private Integer parentId;
    private List<ProjectStructure> children;

    private String state = "open";
    private String tempId;

    @Transient
    private Integer estimateTemplateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<ProjectStructure> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectStructure> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public Integer getEstimateTemplateId() {
        return estimateTemplateId;
    }

    public void setEstimateTemplateId(Integer estimateTemplateId) {
        this.estimateTemplateId = estimateTemplateId;
    }
}
