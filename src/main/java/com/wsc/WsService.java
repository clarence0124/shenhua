package com.wsc;

import com.alibaba.fastjson.JSONArray;
import com.shinfo.ecm.ebs.webService.WbsTemplateServiceImpl;
import com.shinfo.ecm.ebs.webService.WbsTemplateServiceImplService;
import com.wsc.subProject.SubProject;
import com.wsc.wbsTemplate.WbsTemplateCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
@Service
public class WsService {

    private WbsTemplateServiceImpl wbsTemplateService = new WbsTemplateServiceImplService().getWbsTemplateServiceImplPort();

    public String getSubProjectList(String projName) {

        List<SubProject> list = new ArrayList<>();
        SubProject s1 = new SubProject();
        s1.setId(1);
        s1.setProjectName("神华煤炭工程");
        s1.setSubProjName("神延煤炭公司西湾露天煤矿");
        s1.setIndustryTypeName("分类1");
        list.add(s1);

        SubProject s2 = new SubProject();
        s2.setId(1201612309);
        s2.setProjectName("神华煤炭工程");
        s2.setSubProjName("陕西神延煤炭有限责任公司西湾露天煤矿");
        s2.setIndustryTypeName("分类2");
        list.add(s2);

        return JSONArray.toJSONString(list);
        /*String subProjectList = wbsTemplateService.getSubProjectList(projName);
        return subProjectList;*/
    }

    public String getWbsTemplateList() {
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
        return JSONArray.toJSONString(categories);

        // return wbsTemplateService.getWbsTemplateList();
    }

    public String getSubProjectName(Integer subProjectId) {
        String subProjectList = getSubProjectList(null);
        List<SubProject> subProjects = JSONArray.parseArray(subProjectList, SubProject.class);
        for (SubProject subProject : subProjects) {
            if (subProject.getId().intValue() == subProjectId.intValue()) {
                return subProject.getSubProjName();
            }
        }
        return null;
    }

    public String[] getSubProjectTypeName(String industryType, String disciplineType) {
        String wbsTemplateList = this.getWbsTemplateList();
        List<WbsTemplateCategory> categories = JSONArray.parseArray(wbsTemplateList, WbsTemplateCategory.class);
        for (WbsTemplateCategory category : categories) {
            if (category.getIndustryType().equals(industryType) && category.getDisciplineType().equals(disciplineType)) {
                return new String[]{category.getIndustryTypeName(), category.getDisciplineTypeName()};
            }
        }
        return new String[0];
    }
}
