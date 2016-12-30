package com.wsc.audit;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */
public class AuditItem {
    private List<AuditOpinionDetail> auditOpinionDetail;

    public List<AuditOpinionDetail> getAuditOpinionDetail() {
        return auditOpinionDetail;
    }

    public void setAuditOpinionDetail(List<AuditOpinionDetail> auditOpinionDetail) {
        this.auditOpinionDetail = auditOpinionDetail;
    }
}
