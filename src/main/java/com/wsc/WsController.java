package com.wsc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/17.
 */
@Controller
@RequestMapping(value = "/wsc")
public class WsController {

    @Resource
    protected WsService wsService;

    @ResponseBody
    @RequestMapping(value = "getSubProjectList")
    public String getSubProjectList(String projName) {
        return "{\"rows\":" + wsService.getSubProjectList(projName) + "}";
    }

    @ResponseBody
    @RequestMapping(value = "getWbsTemplateList")
    public String getWbsTemplateList(String industryTypeName) {
        return "{\"rows\":" + wsService.getWbsTemplateList(industryTypeName) + "}";
    }

    @ResponseBody
    @RequestMapping(value = "getWbsTemplateNodesByIndustryTypeAndDisciplineType")
    public String getWbsTemplateNodesByIndustryTypeAndDisciplineType(String industryType, String disciplineType) {
        try {
            return "{\"rows\":" + wsService.getWbsTemplateNodesByIndustryTypeAndDisciplineType(industryType, disciplineType) + "}";
        } catch (Exception e) {
            return "{\"rows\":[]}";
        }
    }
}
