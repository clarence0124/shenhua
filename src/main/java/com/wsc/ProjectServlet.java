package com.wsc;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
@WebServlet(urlPatterns = "/project")
public class ProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");

        try {
            switch (cmd) {
                case "list": {
                    List<String> datas = ProjectService.listUserName();
                    String s = JSONUtils.toJSONString(datas);
                    req.setAttribute("jsonDatas", s);
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                    return;
                }
                case "listSubProject": {
                    /*QName SERVICE_NAME = new QName("http://webService.ebs.ecm.shinfo.com/", "WbsTemplateServiceImplService");
                    WbsTemplateServiceImplService ss = new WbsTemplateServiceImplService(WbsTemplateServiceImplService.WSDL_LOCATION, SERVICE_NAME);
                    WbsTemplateServiceImpl port = ss.getWbsTemplateServiceImplPort();
                    String subProjectList = port.getSubProjectList(null);
                    String jsonString = "{\"rows\":" + subProjectList + "}";*/
                    Map<String, Object> obj = new HashMap<>();
                    obj.put("id", "xx");
                    obj.put("subProjName", "xxxx");
                    obj.put("projectName", "总项目名称A");
                    obj.put("industryTypeName", "板块A");

                    Map<String, Object> obj2 = new HashMap<>();
                    obj2.put("id", "oo");
                    obj2.put("subProjName", "ooo");
                    obj.put("projectName", "总项目名称B");
                    obj.put("industryTypeName", "板块B");

                    List<Object> list = new ArrayList<>();
                    list.add(obj);
                    list.add(obj2);

                    Map<String, Object> json = new HashMap<>();
                    json.put("rows", list);
                    String jsonString = JSON.toJSONString(json);

                    resp.getWriter().write(jsonString);
                    return;
                }
                case "subProjectChoose": {
                    req.setAttribute("projectId", req.getParameter("projectId"));
                    req.getRequestDispatcher("/subProjectChoose.jsp").forward(req, resp);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
