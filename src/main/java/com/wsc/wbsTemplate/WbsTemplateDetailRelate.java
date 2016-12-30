package com.wsc.wbsTemplate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/12/26.
 */
@Table
@Entity
public class WbsTemplateDetailRelate {

    @Id
    @Column(length = 32)
    private String projectStructureId;

    @Column(length = 64)
    private String wbsTemplateDetailId;

    public String getProjectStructureId() {
        return projectStructureId;
    }

    public void setProjectStructureId(String projectStructureId) {
        this.projectStructureId = projectStructureId;
    }

    public String getWbsTemplateDetailId() {
        return wbsTemplateDetailId;
    }

    public void setWbsTemplateDetailId(String wbsTemplateDetailId) {
        this.wbsTemplateDetailId = wbsTemplateDetailId;
    }
}
