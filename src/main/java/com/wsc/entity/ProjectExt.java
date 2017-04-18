package com.wsc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/4/17.
 */
@Entity
@Table
public class ProjectExt {

    @Id
    @Column
    private Integer projectId;

    @Column
    private Integer subProjectId;

    @Column(length = 400)
    private String subProjectName;

    @Column(length = 10)
    protected String industryType;

    @Column(length = 100)
    protected String industryTypeName;

    @Column(length = 10)
    protected String disciplineType;

    @Column(length = 100)
    protected String disciplineTypeName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(Integer subProjectId) {
        this.subProjectId = subProjectId;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(String disciplineType) {
        this.disciplineType = disciplineType;
    }

    public String getIndustryTypeName() {
        return industryTypeName;
    }

    public void setIndustryTypeName(String industryTypeName) {
        this.industryTypeName = industryTypeName;
    }

    public String getDisciplineTypeName() {
        return disciplineTypeName;
    }

    public void setDisciplineTypeName(String disciplineTypeName) {
        this.disciplineTypeName = disciplineTypeName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }
}
