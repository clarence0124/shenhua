package com.itspub.base

/**
 * Created by Administrator on 2016/12/17.
 */
interface ISqlDao {

    fun <T> listByAliasToBean(sql: String, params: Array<Object>?, beanType: Class<T>): List<T>?
    fun <T> listByAliasToBean(sql: String, params: Array<Object>?, curPage: Int, pageSize: Int, beanType: Class<T>): List<T>?

}