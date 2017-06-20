package com.wsc;

import com.alibaba.fastjson.JSONArray;
import com.itspub.util.StringUtils;
import com.shinfo.ecm.ebs.webService.*;
import com.wsc.subProject.SubProject;
import com.wsc.wbsTemplate.WbsTemplateCategory;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/17.
 */
@Service
public class WsService {


    public static final String WSDL_USER = "ZZSOAPSYXWJJ";
    public static final String WSDL_PASS = "Xzi&2@#bnT9aBRW7~9$/@hkNdS}4cu#EAjw>jmvG";

    private SIWbsTemplateWSGetWbsTemplateListSynOut wbsTemplateWsdlService = new SIWbsTemplateWSGetWbsTemplateListSynOutService().getHTTPPort();
    private SIWbsTemplateWSGetSubProjectListSynOut subjectWsdlService = new SIWbsTemplateWSGetSubProjectListSynOutService().getHTTPPort();
    private SIWbsTemplateWSImportWSExtimateDetailsSynOut importExtimateDetailWsdlService = new SIWbsTemplateWSImportWSExtimateDetailsSynOutService().getHTTPPort();
    private SIWbsTemplateWSGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeSynOut getWbsTemplateNodesByIndustryTypeAndDisciplineTypeService = new SIWbsTemplateWSGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeSynOutService().getHTTPPort();

    {
        Map<String, Object> requestContext = ((BindingProvider) wbsTemplateWsdlService).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, WSDL_USER);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, WSDL_PASS);

        requestContext = ((BindingProvider) subjectWsdlService).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, WSDL_USER);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, WSDL_PASS);

        requestContext = ((BindingProvider) importExtimateDetailWsdlService).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, WSDL_USER);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, WSDL_PASS);

        requestContext = ((BindingProvider) getWbsTemplateNodesByIndustryTypeAndDisciplineTypeService).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, WSDL_USER);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, WSDL_PASS);
    }

    public String getSubProjectList(String projName) {

        /*List<SubProject> list = new ArrayList<>();
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

        return JSONArray.toJSONString(list);*/
        String subProjectList = subjectWsdlService.getSubProjectList(projName);
        return !"".equals(subProjectList) ? subProjectList : "[]";
    }

    public String getWbsTemplateList(String industryTypeName) {
        /*List<WbsTemplateCategory> categories = new ArrayList<>();
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
        return JSONArray.toJSONString(categories);*/

        String str = wbsTemplateWsdlService.getWbsTemplateList();
        if (!StringUtils.hasText(str)) return "[]";

        List<WbsTemplateCategory> wbsList = JSONArray.parseArray(str, WbsTemplateCategory.class);
        if (StringUtils.hasText(industryTypeName)) {
            Iterator<WbsTemplateCategory> iterator = wbsList.iterator();
            while(iterator.hasNext()) {
                String curIndustryTypeName = iterator.next().getIndustryTypeName();
                if (null == curIndustryTypeName || !curIndustryTypeName.equals(industryTypeName)) {
                    iterator.remove();
                }
            }
        }
        return JSONArray.toJSONString(wbsList);
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
        String wbsTemplateList = this.getWbsTemplateList(null);
        List<WbsTemplateCategory> categories = JSONArray.parseArray(wbsTemplateList, WbsTemplateCategory.class);
        for (WbsTemplateCategory category : categories) {
            if (category.getIndustryType().equals(industryType) && category.getDisciplineType().equals(disciplineType)) {
                return new String[]{category.getIndustryTypeName(), category.getDisciplineTypeName()};
            }
        }
        return new String[0];
    }

    public String getWbsTemplateNodesByIndustryTypeAndDisciplineType(String industryType, String disciplineType) {
        GetWbsTemplateNodesByIndustryTypeAndDisciplineType params = new GetWbsTemplateNodesByIndustryTypeAndDisciplineType();
        params.setIndustryType(industryType);
        params.setDisciplineType(disciplineType);
        String str = getWbsTemplateNodesByIndustryTypeAndDisciplineTypeService.siWbsTemplateWSGetWbsTemplateNodesByIndustryTypeAndDisciplineTypeSynOut(params).getReturn();
        return (!StringUtils.hasText(str)) ? "[]" : str;
    }

    /**
     * 6.1.4	导入概算数据接口
     * @param estimateList
     * 要导入到三算系统的概算数据，json数据字符串
     * @param subProjectId
     * 三算系统子项目id
     * @throws DatatypeConfigurationException
     */
    public void importWSExtimateDetails(String estimateList, String subProjectId) throws DatatypeConfigurationException {
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        int totalLen = estimateList.length();
        int perLen = 1024 * 250; // 每次发送内容不得超过500K



        // 分块调用webservice接口传送模板数据
        {
            int less = totalLen % perLen;
            int blockSize = (totalLen / perLen) + (0 < less ? 1 : 0);
            for (int i = 0; i < blockSize; i++) {
                int sortNum = i+1;
                int start = i * perLen;
                ImportWSExtimateDetails details = new ImportWSExtimateDetails();
                if (i != blockSize - 1) {
                    String content = estimateList.substring(start, start + perLen);
                    details.setExtimateList(content);
                    details.setIsTheLast("0");
                    importExtimateDetailWsdlService.siWbsTemplateWSImportWSExtimateDetailsSynOut(details);
                } else {
                    String content = estimateList.substring(start, start + ((0 < less) ? less : perLen));
                    details.setExtimateList(content);
                    details.setIsTheLast("1");
                }
                details.setSubjId(subProjectId);
                details.setTimeStamp(xgcal);
                details.setType("1");
                details.setOrderNum(sortNum);
                importExtimateDetailWsdlService.siWbsTemplateWSImportWSExtimateDetailsSynOut(details);
            }
        }

        // wbsTemplateService.importWSExtimateDetails(estimateList, subProjectId, xgcal, 1);
    }
}
