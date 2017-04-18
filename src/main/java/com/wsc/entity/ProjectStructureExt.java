package com.wsc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 三算系统和全过程系统概算工程结构关联表
 * Created by Administrator on 2017/4/18.
 */
@Entity
@Table
public class ProjectStructureExt {

    @Id
    @Column
    private Integer projectStructureId;

    @Column
    private Integer estimateTemplateId;

    public ProjectStructureExt() {

    }

    public ProjectStructureExt(Integer projectStructureId, Integer estimateTemplateId) {
        this.projectStructureId = projectStructureId;
        this.estimateTemplateId = estimateTemplateId;
    }

    public Integer getProjectStructureId() {
        return projectStructureId;
    }

    public void setProjectStructureId(Integer projectStructureId) {
        this.projectStructureId = projectStructureId;
    }

    public Integer getEstimateTemplateId() {
        return estimateTemplateId;
    }

    public void setEstimateTemplateId(Integer estimateTemplateId) {
        this.estimateTemplateId = estimateTemplateId;
    }
}
