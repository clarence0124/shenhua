package com.itspub.base

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository
import javax.annotation.Resource

/**
 * Created by Administrator on 2016/12/17.
 */
@Repository
class SqlDao : ISqlDao {

    @Resource
    var sessionFactory: SessionFactory? = null

    fun getSession(): Session? = sessionFactory?.currentSession

    override fun <T> listByAliasToBean(sql: String, params: Array<Object>?, beanType: Class<T>): List<T>? {
        return SqlQuery(getSession(), sql).withParams(params).listByAliasToBean(beanType)
    }

    override fun <T> listByAliasToBean(sql: String, params: Array<Object>?, curPage: Int, pageSize: Int, beanType: Class<T>): List<T>? {
        return SqlQuery(getSession(), sql).withParams(params).pagination(curPage, pageSize).listByAliasToBean(beanType)
    }
}