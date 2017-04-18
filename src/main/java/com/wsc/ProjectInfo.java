package com.wsc;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/15.
 */
public class ProjectInfo implements Serializable {

    private String id;
    private String projectName;
    private String projectCode;
    private String createEmployee;

    private String subProjectName;
    protected String industryTypeName;
    protected String disciplineTypeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCreateEmployee() {
        return createEmployee;
    }

    public void setCreateEmployee(String createEmployee) {
        this.createEmployee = createEmployee;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
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
}
