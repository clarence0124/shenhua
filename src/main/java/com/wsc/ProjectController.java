package com.wsc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itspub.util.StringUtils;
import com.wsc.entity.ProjectExt;
import com.wsc.estimate.EstimateDetail;
import com.wsc.estimate.EstimateDetailWrapper;
import com.wsc.estimate.EstimateListWrapper;
import com.wsc.export.budget.BudgetVo;
import com.wsc.entity.ProjectStructureExt;
import com.wsc.estimate.EstimateVo;
import com.wsc.wbsTemplate.WbsTemplate;
import com.wsc.wbsTemplate.WbsTemplateCategory;
import com.wsc.wbsTemplate.WbsTemplateRelate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private WsService wsService;

    private Map success(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("success", true);
        return map;
    }

    private Map fail(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("success", false);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "listData")
    public String list(Integer page, Integer rows) {
        List<ProjectInfo> projectInfos = projectService.listProjectInfo(1, 10);
        Map<String, Object> map = new HashMap<>();
        map.put("total", projectInfos.size());
        map.put("rows", projectInfos);
        return JSON.toJSONString(map);
    }

    //http://localhost:8999/project/getEstimateVo?projectStructureId=99804&subProjectId=123
    @ResponseBody
    @RequestMapping(value = "getEstimateVo")
    public String getEstimateVo(Integer subProjectId, String projectStructureId) {
        try {
            EstimateVo vo = this.projectService.getEstimateVo(subProjectId, projectStructureId);
            return JSONObject.toJSONString(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //http://localhost:8999/project/getBudgetVo?contractId=471
    @ResponseBody
    @RequestMapping(value = "getBudgetVo")
    public String getBudgetVo(String contractId) {
        try {
            BudgetVo vo = this.projectService.getBudgetVo(contractId);
            return JSONObject.toJSONString(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "wbsTemplate")
    public String wbsTemplate() {
        List<WbsTemplateCategory> templates = this.projectService.listWbsTemplate();
        Map<String, Object> map = new HashMap<>();
        map.put("rows", templates);
        return JSONObject.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "wbsTemplateDetail")
    public String wbsTemplateDetail(String industryType, String disciplineType) {
        if (!StringUtils.hasText(industryType) || !StringUtils.hasText(disciplineType)) {
            return "{\"rows\": []}";
        }
        List<WbsTemplate> templates = this.projectService.listWbsTemplateDetail(industryType, disciplineType);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", templates);
        return JSONObject.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "{projectId}/wbsTemplateRelate", method = RequestMethod.PUT)
    public String saveWbsTemplateRelate(@PathVariable String projectId, WbsTemplateRelate r) {
        try {
            WbsTemplateRelate wbsTemplateRelate = this.projectService.saveWbsTemplateRelate(projectId, r);
            Map result = this.success("关联成功");
            result.put("wbsTemplateRelate", wbsTemplateRelate);
            return JSONObject.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.toJSONString(this.fail("关联失败：" + e.getMessage()));
        }
    }

    @RequestMapping(value = "/{projectId}/relateTemplatePage", method = RequestMethod.GET)
    public String relateTemplatePage(@PathVariable String projectId, String industryTypeName, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("industryTypeName", industryTypeName);
        return "relateTemplatePage";
    }

    @RequestMapping(value = "/{projectId}/relateTemplateDetailPage", method = RequestMethod.GET)
    public String relateTemplateDetailPage(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "relateTemplateDetailPage";
    }

    /**
     * 关联子项目的窗口
     * @param projectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{projectId}/relateSubProjectPage", method = RequestMethod.GET)
    public String relateSubProjectPage(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "relateSubProjectPage";
    }

    @ResponseBody
    @RequestMapping(value = "/{projectId}/relateSubProjectAndIndustryTypeName", method = RequestMethod.POST)
    public String relateSubProjectAndIndustryTypeName(@PathVariable String projectId, Integer subProjectId, String disciplineType, String industryType) {
        projectService.relateSubProjectAndIndustryTypeName(projectId, subProjectId, disciplineType, industryType);
        Map result = this.success("操作成功");
        return JSONObject.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "{projectId}/projectStructure")
    public String projectStructure(@PathVariable Integer projectId) {
        Integer phaseId = this.projectService.getEstimatePhaseId(projectId);
        List<ProjectStructure> templates = this.projectService.listProjectStructureByProjectId(projectId, phaseId);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", templates);
        return JSONObject.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "{projectId}/wbsTemplateDetail")
    public String wbsTemplateDetail(@PathVariable String projectId) {
        List<WbsTemplate> templates = this.projectService.listWbsTemplateDetail(projectId);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", templates);
        return JSONObject.toJSONString(map);
    }

    /**
     * 工程结构关联
     * @param projectStructureIds
     * @param estimateTemplateId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/wbsTemplateDetailRelate", method = RequestMethod.PUT)
    public String wbsTemplateDetailRelate(@RequestParam(value = "projectStructureIds[]") Integer[] projectStructureIds, Integer estimateTemplateId) {
        try {
            List<ProjectStructureExt> wtdr = this.projectService.setWbsTemplateDetailRelate(projectStructureIds, estimateTemplateId);
            Map result = this.success("关联成功");
            result.put("wtdr", wtdr);
            return JSONObject.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.toJSONString(this.fail("关联失败：" + e.getMessage()));
        }
    }

    /**
     * 导出页面
     * @param projectId
     * @param model
     * @return
     */
    @RequestMapping(value = "{projectId}/exportPage")
    public String exportPage(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "exportPage";
    }

    /**
     * 列出模板明细，带金额汇总
     * @param projectId
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "{projectId}/wbsTemplateDetailWithSum")
    public String wbsTemplateDetailWithSum(@PathVariable String projectId) throws IOException {
        List<EstimateDetail> templates = this.projectService.listWbsTemplateDetailWithSum(projectId);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", templates);
        return JSONObject.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "{projectId}/exportResult")
    public String exportResult(@PathVariable String projectId) throws IOException {
        List<EstimateDetail> details = this.projectService.listWbsTemplateDetailWithSum(projectId);
        EstimateVo vo = new EstimateVo();
        EstimateListWrapper wrapper = new EstimateListWrapper();

        ProjectExt pe = projectService.getProjectExt(Integer.parseInt(projectId));
        Integer subProjectId = pe.getSubProjectId();

        String estimateId = StringUtils.randomLetterAndNumber(16);
        wrapper.setId(estimateId);
        wrapper.setSubprojId(subProjectId);
        for (EstimateDetail template : details) {
            if (null == template.getPid()) {
                wrapper.setCivilSum(wrapper.getCivilSum() + template.getCivilEcost());
                wrapper.setEquipmentSum(wrapper.getEquipmentSum() + template.getEquipmentEcost());
                wrapper.setInstallSum(wrapper.getInstallSum() + template.getInstallEcost());
                wrapper.setFeeSum(wrapper.getFeeSum() + template.getFeeEcost());
                wrapper.setOtherSum(wrapper.getOtherSum() + template.getOtherEcost());
            }
            template.setEstimateId(estimateId);
        }
        wrapper.setTotalSum(wrapper.getCivilSum() + wrapper.getEquipmentSum() + wrapper.getInstallSum() + wrapper.getFeeSum() + wrapper.getOtherSum());

        EstimateDetailWrapper detailWrapper = new EstimateDetailWrapper();
        detailWrapper.setDetails(details);
        wrapper.setEstimateDetail(detailWrapper);

        vo.setEstimateList(wrapper);

        return JSONObject.toJSONString(vo, new DoubleFormatter()).toString();
    }
}
