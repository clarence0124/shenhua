package com.itspub.core;

/**
 * Created by Administrator on 2016/6/13.
 */
public class StringUtils {

    public static String notNull(String str) {
        return (null != str) ? str : "";
    }

    public static String notNull(String str, String candidate) {
        return (null != str) ? str : StringUtils.notNull(candidate);
    }

    public static String notNullAndEmpty(String str, String candidate) {
        return notNull(str).equals("") ? candidate : str;
    }
}
