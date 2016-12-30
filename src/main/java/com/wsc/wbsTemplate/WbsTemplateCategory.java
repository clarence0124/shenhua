package com.wsc.wbsTemplate;

/**
 * Created by Administrator on 2016/12/22.
 */
public class WbsTemplateCategory {

    private String tempId;
    private String disciplineType;
    private String disciplineTypeName;
    private String industryType;
    private String industryTypeName;

    public String getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(String disciplineType) {
        this.disciplineType = disciplineType;
    }

    public String getDisciplineTypeName() {
        return disciplineTypeName;
    }

    public void setDisciplineTypeName(String disciplineTypeName) {
        this.disciplineTypeName = disciplineTypeName;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getIndustryTypeName() {
        return industryTypeName;
    }

    public void setIndustryTypeName(String industryTypeName) {
        this.industryTypeName = industryTypeName;
    }

    public String getTempId() {
        return disciplineType + "," + industryType;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }
}
