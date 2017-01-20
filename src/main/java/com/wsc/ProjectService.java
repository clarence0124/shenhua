package com.wsc;

import com.itspub.framework.dao.SqlDao;
import com.test.User;
import com.wsc.budget.BudgetVo;
import com.wsc.estimate.EstimateDetail;
import com.wsc.estimate.EstimateDetailWrapper;
import com.wsc.estimate.EstimateListWrapper;
import com.wsc.estimate.EstimateVo;
import com.wsc.wbsTemplate.WbsTemplate;
import com.wsc.wbsTemplate.WbsTemplateCategory;
import com.wsc.wbsTemplate.WbsTemplateDetailRelate;
import com.wsc.wbsTemplate.WbsTemplateRelate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
@Transactional
@Service
public class ProjectService {

    @Resource
    private SqlDao sqlDao;
    public List<ProjectInfo> listProjectInfo(Integer page, Integer rows) {
        String sql2 = "select flag status from userInfos";
        List<User> users = this.sqlDao.listByAliasToBean(User.class, sql2);

        final String sql = "select cast(o.id as varchar) id, o.projectcode projectCode, o.projectname projectName, o.createEmployee from ProjectInfo o where o.isDelete = 0";
        List<ProjectInfo> projectInfos = this.sqlDao.listByAliasToBean(ProjectInfo.class, sql, page, rows);
        return projectInfos;
    }

    /**
     * 列出用户名
     * @return
     * @throws SQLException
     */
    public static List<String> listUserName() throws SQLException {
        return Collections.emptyList();
    }

    private ProjectPhase getFirstProjectPhase(Integer projectId) {
        ProjectPhase pp = this.sqlDao.getByAliasToBean(ProjectPhase.class, "select top 1 id, name, ischoose isChoose, istargetcost isTarget from ProjectPhase where projectinfo_id = ? order by ordnum", new Object[]{projectId});
        return pp;
    }

    public EstimateVo getEstimateVo(Integer subProjectId, String projectStructureId) {
        EstimateVo vo = new EstimateVo();

        ProjectStructure ps = this.sqlDao.getByAliasToBean(ProjectStructure.class, "select id, nodeCode, projectinfo_id projectInfoId from ProjectStructure ps where id = ?", new Object[]{projectStructureId});
        if (null == ps) {
            throw new IllegalStateException("没有找到对应的工程节点");
        }

        ProjectPhase pp = getFirstProjectPhase(ps.getProjectInfoId());
        if (null == pp) {
            throw new IllegalStateException("没有找到概算阶段内容");
        }

        String sql = "select cast(ps.id as varchar) id\n" +
                "\t, isnull(pspm.compMoney1, 0) civilSum\n" +
                "\t, isnull(pspm.compMoney4, 0) installSum, isnull(pspm.compMoney3, 0) equipmentSum\n" +
                "\t, cast((isnull(pspm.compMoney5, 0) + ISNULL(pspm.compMoney6, 0) + ISNULL(pspm.compMoney7, 0)) as float) feeSum\n" +
                "\t, isnull(pspm.compMoney8, 0) otherSum\n" +
                "\t, isnull(compSumMoney, 0) totalSum\n" +
                " from projectStructure ps \n" +
                "  left join ProjectStructurePhaseMoney pspm on ps.id = pspm.projectstructure_id and ps.id = ?\n" +
                "  left join ProjectPhase pp on pp.id = pspm.projectphase_id and pp.id = ?";

        EstimateListWrapper estimateListWrapper = sqlDao.getByAliasToBean(EstimateListWrapper.class, sql, new Object[]{projectStructureId, pp.getId()});
        estimateListWrapper.setSubprojId(subProjectId);
        vo.setEstimateList(estimateListWrapper);

        ProjectPhase lpp = this.sqlDao.getByAliasToBean(ProjectPhase.class, "select top 1 id, name, ischoose isChoose, istargetcost isTarget from ProjectPhase where projectinfo_id = ? order by ordnum desc", new Object[]{ps.getProjectInfoId()});

        String detailListSql = "select\n" +
                "\t cast(ps.id as varchar) id, cast(ps.parentId as varchar) pid, ps.ordnum brotherOrderNo\n" +
                "\t, ps.nodename name, ps.professionaltype spec, ps.scaleUnit unit, pspm.scaleValue amount\n" +
                "\t, case ps.nodetype when 3 then '1' else '0' end leafFlag \n" +
                "\t, pspm.compMoney1 civilEcost \n" +
                "\t, pspm.compMoney4 installCost \n" +
                "\t, pspm.compMoney3 equipmentEcost \n" +
                "\t, pspm.compMoney2 emiSum \n" +
                "\t, pspm.compMoney8 feeEcost \n" +
                "  from ProjectStructure ps \n" +
                "  left join ProjectStructurePhaseMoney pspm on ps.id = pspm.projectstructure_id and pspm.projectphase_id = ?\n" +
                "  left join ProjectPhase pp on pp.id = pspm.projectphase_id\n" +
                "  where ps.projectinfo_id = ? and ps.nodeCode like ?";
        EstimateDetailWrapper estimateDetailWrapper = new EstimateDetailWrapper();
        List<EstimateDetail> details = sqlDao.listByAliasToBean(EstimateDetail.class, detailListSql, new Object[]{lpp.getId(), ps.getProjectInfoId(), ps.getNodeCode() + ".%"});
        estimateDetailWrapper.setDetails(details);

        estimateListWrapper.setEstimateDetail(estimateDetailWrapper);
        vo.setEstimateList(estimateListWrapper);
        return vo;
    }

    public BudgetVo getBudgetVo(String contractId) {
        String sql = "select cast(cl.id as varchar) id, cl.code, cl.name, cast(cl.parentid as varchar) pid, substring(convert(varchar, cl.signDate, 112), 1, 6) monthly, \n" +
                "  cl.bidPrice preTender, cl.money bcost, cl.buildcompany contractName,\n" +
                "  cl.startdate startDate, cl.enddate completionDate, cl.signDate signedDate, cl.remark remark\n" +
                "   from ContractList cl where cl.id = ?";
        BudgetVo vo = this.sqlDao.getByAliasToBean(BudgetVo.class, sql, new Object[]{contractId});
        return vo;
    }

    private ProjectStructure getProjectRootStructure(Integer projectId) {
        ProjectStructure ps = this.sqlDao.getByAliasToBean(ProjectStructure.class, "select id, nodeCode, nodeName, projectinfo_id projectInfoId from ProjectStructure ps where projectinfo_id = ? and parentId is null", new Object[]{projectId});
        return ps;
    }

    public List<ProjectStructure> listChildrenProjectStructureByStructureId(Integer projectStructureId, Integer phaseId, boolean recursion) {
        List<ProjectStructure> pss = this.sqlDao.listByAliasToBean(ProjectStructure.class, "select id, nodeCode, nodeName, projectinfo_id projectInfoId from ProjectStructure ps where parentId = ? and phaseIds like ? order by ordNum", new Object[]{projectStructureId, "%" + phaseId + "%"});
        if (recursion) {
            for (ProjectStructure ps : pss) {
                ps.setChildren(listChildrenProjectStructureByStructureId(ps.getId(), phaseId, recursion));
            }
        }
        return pss;
    }

    public ProjectStructure getRootProjectStructureByProjectId(Integer projectId, boolean recursion) {
        ProjectPhase pp = getFirstProjectPhase(projectId);
        if (null == pp) {
            throw new IllegalStateException("没有找到概算阶段内容");
        }
        ProjectStructure root = getProjectRootStructure(projectId);
        root.setState(null);
        List<ProjectStructure> children = listChildrenProjectStructureByStructureId(root.getId(), pp.getId(), recursion);
        for (ProjectStructure child : children) {
            child.setState(null);
        }
        root.setChildren(children);
        return root;
    }

    public List<WbsTemplateCategory> listWbsTemplate() {
        List<WbsTemplateCategory> categories = new ArrayList<>();
        WbsTemplateCategory c1 = new WbsTemplateCategory();
        c1.setIndustryType("1");
        c1.setIndustryTypeName("板块大类名称1");
        c1.setDisciplineType("1");
        c1.setDisciplineTypeName("板块小类名称1");

        WbsTemplateCategory c2 = new WbsTemplateCategory();
        c2.setIndustryType("2");
        c2.setIndustryTypeName("板块大类名称2");
        c2.setDisciplineType("2");
        c2.setDisciplineTypeName("板块小类名称2");

        categories.add(c1);
        categories.add(c2);
        return categories;
    }

    public List<WbsTemplate> listWbsTemplateDetail(String industryType, String disciplineType) {
        List<WbsTemplate> roots = new ArrayList<>();

        WbsTemplate root = new WbsTemplate();
        root.setName("根1");
        root.setId(1);
        root.setCode("code1");
        roots.add(root);

        WbsTemplate stem1 = new WbsTemplate();
        stem1.setName("子11");
        stem1.setId(11);
        stem1.setCode("11");
        stem1.setPid(1);
        roots.add(stem1);

        WbsTemplate stem2 = new WbsTemplate();
        stem2.setName("子12");
        stem2.setId(12);
        stem2.setCode("12");
        stem2.setPid(1);
        roots.add(stem2);

        WbsTemplate stem3 = new WbsTemplate();
        stem3.setName("子121");
        stem3.setId(121);
        stem3.setCode("121");
        stem3.setPid(12);
        roots.add(stem3);
        return roots;
    }

    public List<WbsTemplate> listWbsTemplateDetail(String projectId) {
        WbsTemplateRelate wbsTemplateRelate = this.getWbsTemplateRelate(projectId);
        return this.listWbsTemplateDetail(wbsTemplateRelate.getIndustryType(), wbsTemplateRelate.getDisciplineType());
    }

    private WbsTemplateRelate getWbsTemplateRelate(String projectId) {
        return this.sqlDao.getByAliasToBean(WbsTemplateRelate.class, "select * from WbsTemplateRelate where projectId = ?", new Object[]{projectId});
    }

    public WbsTemplateRelate saveWbsTemplateRelate(String projectId, WbsTemplateRelate r) {
        WbsTemplateRelate wtr = getWbsTemplateRelate(projectId);
        if (wtr != null && (!r.getIndustryType().equals(wtr.getIndustryType()) || !r.getDisciplineType().equals(wtr.getDisciplineType()))) {
            this.sqlDao.execUpdate("update WbsTemplateRelate set disciplineType = ?, industryType = ? where projectId = ?", new Object[]{r.getDisciplineType(), r.getIndustryType(), projectId});
        } else if (null == wtr) {
            this.sqlDao.execUpdate("insert into WbsTemplateRelate(projectId, disciplineType, industryType) values (?, ?, ?)", new Object[]{projectId, r.getDisciplineType(), r.getIndustryType()});
        }
        return getWbsTemplateRelate(projectId);
    }


    public List<ProjectStructure> listProjectStructureByProjectId(String projectId) {
        String sql = "select id, nodeName, nodeCode, projectInfo_id projectInfoId, parentid parentId from ProjectStructure where projectInfo_id = ?";
        List<ProjectStructure> ps = this.sqlDao.listByAliasToBean(ProjectStructure.class, sql, new Object[]{projectId});
        return ps;
    }

    private WbsTemplateDetailRelate getWbsTemplateDetailRelate(String projectStructureId) {
        return this.sqlDao.getByAliasToBean(WbsTemplateDetailRelate.class, "select * from WbsTemplateDetailRelate where projectStructureId = ?", new Object[]{projectStructureId});
    }


    public WbsTemplateDetailRelate saveWbsTemplateDetailRelate(String projectStructureId, WbsTemplateDetailRelate dr) {
        WbsTemplateDetailRelate wtdr = getWbsTemplateDetailRelate(projectStructureId);
        if (wtdr != null && (!dr.getWbsTemplateDetailId().equals(wtdr.getWbsTemplateDetailId()))) {
            this.sqlDao.execUpdate("update WbsTemplateDetailRelate set wbsTemplateDetailId = ? where projectStructureId = ?", new Object[]{wtdr.getWbsTemplateDetailId(), projectStructureId});
        } else if (null == wtdr) {
            this.sqlDao.execUpdate("insert into WbsTemplateDetailRelate(wbsTemplateDetailId, projectStructureId) values (?, ?)", new Object[]{dr.getWbsTemplateDetailId(), dr.getProjectStructureId()});
        }
        return getWbsTemplateDetailRelate(projectStructureId);
    }
}
