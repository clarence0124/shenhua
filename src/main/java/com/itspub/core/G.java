package com.itspub.core;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/5/27.
 */
public class G {

    static Properties ps;

    static {
        ps = new Properties();
        try {
            ps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("/").getPath().replaceAll("%20", " ") + "/var.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("加载配置文件出错");
        }
    }

    public static final String LOGIN_FLAG = "token";

    public static final String SESSION_ID = "jsessionid";

    public static final String SERVER_TOKEN = getVar("SERVER_TOKEN");

    public static final String DOMAIN = getVar("DOMAIN");

    public static final String HOST = getVar("HOST");

    public static final boolean USE_JNDI = Boolean.valueOf(getVar("USE_JNDI")).booleanValue();

    public static final String DATASOURCE_JNDI = getVar("DS.JNDI");

    public static final String DATASOURCE_DRIVER = getVar("DS.DRIVER");

    public static final String DATASOURCE_URL = getVar("DS.URL");

    public static final String DATASOURCE_USERNAME = getVar("DS.USERNAME");

    public static final String DATASOURCE_PASSWORD = getVar("DS.PASSWORD");

    public static String getBasePath(HttpServletRequest req) {
        return req.getScheme() + "://" + StringUtils.notNullAndEmpty(HOST, getHost(req)) + req.getContextPath();
    }

    public static String getHost(HttpServletRequest req) {
        return req.getHeader("host");
    }

    public static String getVar(String key) {
        return ps.getProperty(key);
    }
}
