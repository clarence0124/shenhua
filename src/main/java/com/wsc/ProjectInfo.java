package com.wsc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/15.
 */
public class ProjectInfo implements Serializable {

    private String id;
    private String projectName;
    private String projectCode;
    private String createEmployee;

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
}
