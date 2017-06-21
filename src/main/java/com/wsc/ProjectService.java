package com.wsc;

import com.alibaba.fastjson.JSONArray;
import com.itspub.framework.dao.SqlDao;
import com.wsc.entity.ProjectExt;
import com.wsc.entity.ProjectStructureExt;
import com.wsc.estimate.EstimateDetail;
import com.wsc.estimate.EstimateDetailWrapper;
import com.wsc.estimate.EstimateListWrapper;
import com.wsc.estimate.EstimateVo;
import com.wsc.export.budget.BudgetVo;
import com.wsc.wbsTemplate.WbsTemplate;
import com.wsc.wbsTemplate.WbsTemplateCategory;
import com.wsc.wbsTemplate.WbsTemplateDetailRelate;
import com.wsc.wbsTemplate.WbsTemplateRelate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/12/15.
 */
@Transactional
@Service
public class ProjectService {

    @Resource
    private SqlDao sqlDao;
    public List<ProjectInfo> listProjectInfo(Integer page, Integer rows) {
        final String sql = "select cast(o.id as varchar) id, o.projectcode projectCode, o.projectname projectName, o.createEmployee, pe.subProjectName, pe.industryTypeName, pe.disciplineTypeName" +
                " from ProjectInfo o left join ProjectExt pe on o.id = pe.projectId where isnull(o.isDelete, 0) = 0";
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
        estimateDetailWrapper.setDetail(details);

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

    private Integer sort(Integer i, Integer j) {
        if (null == i && null != j) {
            return 1;
        } else if (null == i && null != j) {
            return -1;
        } else {
            return i - j;
        }
    }

    private WbsTemplateRelate getWbsTemplateRelate(String projectId) {
        return this.sqlDao.getByAliasToBean(WbsTemplateRelate.class, "select * from WbsTemplateRelate where projectId = ?", new Object[]{projectId});
    }


    public List<ProjectStructure> listProjectStructureByProjectId(Integer projectId, Integer phaseId) {
        String sql = "select ps.id, ps.nodeName, ps.nodeCode, ps.projectInfo_id projectInfoId, ps.parentid parentId, pse.estimateTemplateId from ProjectStructure ps" +
                " left join ProjectStructureExt pse on ps.id = pse.projectStructureId where ps.projectInfo_id = ? and ps.phaseIds like ? order by" +
                " len(ps.nodeCode)- len(REPLACE(ps.nodeCode, '.', '')), cast(SUBSTRING(ps.nodeCode, len(ps.nodeCode) - charindex('.', reverse(ps.nodeCode)) + 2, LEN(ps.nodeCode)) as int)";
        List<ProjectStructure> ps = this.sqlDao.listByAliasToBean(ProjectStructure.class, sql, new Object[]{projectId, "%," + phaseId + ",%"});
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

    @Resource
    private WsService wsService;

    @Transactional
    public void relateSubProjectAndIndustryTypeName(String projectId, Integer subProjectId, String disciplineType, String industryType) {
        ProjectExt pe = getProjectExt(Integer.parseInt(projectId));
        String subProjectName = wsService.getSubProjectName(subProjectId);
        String[] typeNames = wsService.getSubProjectTypeName(industryType, disciplineType);

        if (null == pe) {
            String insertSql = "insert into ProjectExt(subProjectId, subProjectName, industryType, disciplineType, industryTypeName, disciplineTypeName, projectId) values (?, ?, ?, ?, ?, ?, ?)";
            sqlDao.execUpdate(insertSql, new Object[]{subProjectId, subProjectName, industryType, disciplineType, typeNames[0], typeNames[1], projectId});
        } else {
            String updateSql = "update ProjectExt set subProjectId = ?, subProjectName = ?, industryType = ?, disciplineType = ?, industryTypeName = ?, disciplineTypeName = ? where projectId = ?";
            sqlDao.execUpdate(updateSql, new Object[]{subProjectId, subProjectName, industryType, disciplineType, typeNames[0], typeNames[1], projectId});
        }

        String del = "delete from ProjectStructureExt where projectStructureId in (select id from ProjectStructure where projectInfo_id = ?)";
        sqlDao.execUpdate(del, new Object[]{projectId});
    }

    public Integer getEstimatePhaseId(Integer projectId) {
        String sql = "select id from ProjectPhase where projectInfo_id = ? and name like '%概算%'";
        return sqlDao.getAutoCast(sql, new Object[]{projectId});
    }

    private int updateProjectStructureExt(Integer projectStructureId, Integer estimateTemplateId) {
        String sql = "update ProjectStructureExt set estimateTemplateId = ? where projectStructureId = ?";
        return this.sqlDao.execUpdate(sql, new Object[]{estimateTemplateId, projectStructureId});
    }

    private int insertProjectStructureExt(Integer projectStructureId, Integer estimateTemplateId) {
        String sql = "insert into ProjectStructureExt(estimateTemplateId, projectStructureId) values (?, ?)";
        return this.sqlDao.execUpdate(sql, new Object[]{estimateTemplateId, projectStructureId});
    }

    /**
     * 设置全过程管理系统和三算系统的关联关系
     * @param projectStructureIds
     * @param estimateTemplateId
     * @return
     */
    @Transactional
    public List<ProjectStructureExt> setWbsTemplateDetailRelate(Integer[] projectStructureIds, Integer estimateTemplateId) {
        List<ProjectStructureExt> psExtList = new ArrayList<>(projectStructureIds.length);
        for (Integer psId : projectStructureIds) {
            int updateCount = updateProjectStructureExt(psId, estimateTemplateId);
            if (0 == updateCount) {
                updateCount = insertProjectStructureExt(psId, estimateTemplateId);
            }
            if (0 == updateCount) {
                throw new IllegalStateException();
            }
            psExtList.add(new ProjectStructureExt(psId, estimateTemplateId));
        }
        return psExtList;
    }

    public List<WbsTemplate> listWbsTemplateDetail(String projectId) {
        ProjectExt pe = this.getProjectExt(Integer.parseInt(projectId));

        String jsonStr = wsService.getWbsTemplateNodesByIndustryTypeAndDisciplineType(pe.getIndustryType(), pe.getDisciplineType());
        List<WbsTemplate> wtList = JSONArray.parseArray(jsonStr, WbsTemplate.class);

        Collections.sort(wtList, new Comparator<WbsTemplate>() {
            @Override
            public int compare(WbsTemplate o1, WbsTemplate o2) {
                Integer j = sort(o1.getNodeDepth(), o2.getNodeDepth());
                if (0 == j.intValue()) return sort(o1.getBrotherOrderNo(), o2.getBrotherOrderNo());
                else return j;
            }
        });
        return wtList;
    }

    private List<EstimateDetail> listCustomEstimateDetail(Integer phaseId, Integer wbsTemplateDetailId) {
        String sql = "select ('2_' + cast(ps.id as varchar)) id, ps.nodename name, pspm.scaleValue amount\n" +
                ", ps.scaleUnit unit, ps.professionaltype spec \n" +
                ", pspm.compMoney1 civilEcost, pspm.compMoney3 equipmentEcost, pspm.compMoney4 installEcost, (pspm.compMoney2 - pspm.compMoney3 - pspm.compMoney4) feeEcost, pspm.compMoney8 otherEcost\n" +
                " from ProjectStructureExt pse inner join ProjectStructure ps on pse.projectStructureId = ps.id\n" +
                " inner join ProjectStructurePhaseMoney pspm on pspm.projectphase_id = ? and pspm.projectstructure_id = ps.id where pse.estimateTemplateId = ?";
        return this.sqlDao.listByAliasToBean(EstimateDetail.class, sql, new Object[]{phaseId, wbsTemplateDetailId});
    }

    public List<EstimateDetail> listWbsTemplateDetailWithSum(String projectId) {

        List<EstimateDetail> rs = new ArrayList<>();
        Integer phaseId = this.getEstimatePhaseId(Integer.parseInt(projectId));
        String isPrefix = "1_";

        Map<String, EstimateDetail> map = new ConcurrentHashMap<>();
        List<WbsTemplate> list = listWbsTemplateDetail(projectId);
        for (WbsTemplate wbsTemplate : list) {
            EstimateDetail vo = new EstimateDetail();
            vo.setId(isPrefix + wbsTemplate.getId());
            vo.setName(wbsTemplate.getName());

            EstimateDetail parent = null;
            if (null != wbsTemplate.getPid() && 0 < wbsTemplate.getPid()) {
                vo.setPid(isPrefix + wbsTemplate.getPid());
                parent = map.get(vo.getPid());
            }
            vo.setLeafFlag("0");
            vo.setTempNodeId(null != wbsTemplate.getId() ? String.valueOf(wbsTemplate.getId()) : null);
            vo.setTempNodeName(wbsTemplate.getName());

            if (null != parent) {
                vo.setNodePathId(parent.getNodePathId() + "/" + vo.getId());
                vo.setNodePath(parent.getNodePath() + "->" + vo.getName());
                vo.setNodeDepth(parent.getNodeDepth() + 1);
            } else {
                vo.setNodePathId("/" + vo.getId());
                vo.setNodePath(vo.getName());
                vo.setNodeDepth(1);
            }

            rs.add(vo);

            map.put(vo.getId(), vo);

            if ("true".equals(wbsTemplate.getLeaf()) || "1".equals(wbsTemplate.getLeaf())) {
                List<EstimateDetail> leafs = this.listCustomEstimateDetail(phaseId, wbsTemplate.getId());
                if (0 < leafs.size()) {
                    EstimateDetail leafEstimateSum = new EstimateDetail();
                    for (EstimateDetail leaf : leafs) {
                        leaf.setPid(vo.getId());
                        leaf.setLeafFlag("1");
                        leaf.setNodePath(vo.getNodePath() + "/" + leaf.getId());
                        leaf.setNodePathId(vo.getNodePathId() + "->" + leaf.getName());
                        leaf.setNodeDepth(vo.getNodeDepth() + 1);

                        leafEstimateSum.setCivilEcost(leafEstimateSum.getCivilEcost() + leaf.getCivilEcost());
                        leafEstimateSum.setEquipmentEcost(leafEstimateSum.getEquipmentEcost() + leaf.getEquipmentEcost());
                        leafEstimateSum.setInstallEcost(leafEstimateSum.getInstallEcost() + leaf.getInstallEcost());
                        leafEstimateSum.setFeeEcost(leafEstimateSum.getFeeEcost() + leaf.getFeeEcost());
                        leafEstimateSum.setOtherEcost(leafEstimateSum.getOtherEcost() + leaf.getOtherEcost());
                    }

                    EstimateDetail p = vo;
                    while(null != p) {
                        p.setCivilEcost(p.getCivilEcost() + leafEstimateSum.getCivilEcost());
                        p.setEquipmentEcost(p.getEquipmentEcost() + leafEstimateSum.getEquipmentEcost());
                        p.setInstallEcost(p.getInstallEcost() + leafEstimateSum.getInstallEcost());
                        p.setFeeEcost(p.getFeeEcost() + leafEstimateSum.getFeeEcost());
                        p.setOtherEcost(p.getOtherEcost() + leafEstimateSum.getOtherEcost());
                        if (null != p.getPid()) {
                            p = map.get(p.getPid());
                        } else {
                            p = null;
                        }
                    }
                    rs.addAll(leafs);
                }
            }
        }
        return rs;
    }

    public ProjectExt getProjectExt(Integer projectId) {
        String sql = "select * from ProjectExt where projectId = ?";
        return this.sqlDao.getByAliasToBean(ProjectExt.class, sql, new Object[]{projectId});
    }

    @Transactional
    public void saveExportEstimateDetails(String projectId, String exportContent) {
        String sql = "delete from EstimateExportInfo where projectId = ?";
        this.sqlDao.execUpdate(sql, new Object[]{projectId});
        String sql1 = "insert into EstimateExportInfo(projectId, exportContent) values (?, ?)";
        this.sqlDao.execUpdate(sql1, new Object[]{projectId, exportContent});
    }
}
