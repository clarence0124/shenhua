package com.itspub.framework.dao

/**
 * Created by Administrator on 2016/12/17.
 */
interface ISqlDao {

    fun <T> listByAliasToBean(beanType: Class<T>, sql: String, params: Array<Any>): List<T>
    fun <T> listByAliasToBean(beanType: Class<T>, sql: String): List<T>
    fun <T> listByAliasToBean(beanType: Class<T>, sql: String, curPage: Int, pageSize: Int, params: Array<Any>): List<T>
    fun <T> listByAliasToBean(beanType: Class<T>, sql: String, curPage: Int, pageSize: Int): List<T>
    fun <T> getByAliasToBean(beanType: Class<T>, sql: String, params: Array<Any>): T?
    fun <T> getByAliasToBean(beanType: Class<T>, sql: String): T?

    fun execUpdate(sql: String, params: Array<Any>): Int
    fun execBatchUpdate(sql: String, paramsList: Array<Array<Any>>): Array<Int>
}