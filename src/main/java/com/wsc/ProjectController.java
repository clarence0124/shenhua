package com.wsc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wsc.budget.BudgetVo;
import com.wsc.estimate.EstimateVo;
import com.wsc.wbsTemplate.WbsTemplate;
import com.wsc.wbsTemplate.WbsTemplateCategory;
import com.wsc.wbsTemplate.WbsTemplateDetailRelate;
import com.wsc.wbsTemplate.WbsTemplateRelate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @RequestMapping(value = "{projectId}/projectStructurePage")
    public String projectStructurePage(@PathVariable Integer projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectStructure";
    }

    /*@ResponseBody
    @RequestMapping(value = "{projectId}/projectStructure")
    public String projectStructure(@PathVariable Integer projectId) {
        Map<String, Object> result = new HashMap<>();
        ProjectStructure root = this.projectService.getRootProjectStructureByProjectId(projectId, true);
        List<ProjectStructure> roots = new ArrayList<>();
        roots.add(root);
        result.put("rows", roots);
        return JSONObject.toJSONString(result);
    }
*/
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
    public String relateTemplatePage(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "relateTemplatePage";
    }

    @RequestMapping(value = "/{projectId}/relateTemplateDetailPage", method = RequestMethod.GET)
    public String relateTemplateDetailPage(@PathVariable String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "relateTemplateDetailPage";
    }

    @ResponseBody
    @RequestMapping(value = "{projectId}/projectStructure")
    public String projectStructure(@PathVariable String projectId) {
        List<ProjectStructure> templates = this.projectService.listProjectStructureByProjectId(projectId);
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

    @ResponseBody
    @RequestMapping(value = "/{projectStructureId}/wbsTemplateDetailRelate", method = RequestMethod.PUT)
    public String wbsTemplateDetailRelate(@PathVariable String projectStructureId, WbsTemplateDetailRelate dr) {
        try {
            WbsTemplateDetailRelate wtdr = this.projectService.saveWbsTemplateDetailRelate(projectStructureId, dr);
            Map result = this.success("关联成功");
            result.put("wtdr", wtdr);
            return JSONObject.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.toJSONString(this.fail("关联失败：" + e.getMessage()));
        }
    }
}
