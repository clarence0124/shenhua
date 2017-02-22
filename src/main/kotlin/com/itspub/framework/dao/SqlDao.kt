package com.itspub.framework.dao

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository
import java.sql.SQLException
import java.util.List
import javax.annotation.Resource

/**
 * Created by Administrator on 2016/12/17.
 */
@Repository
class SqlDao() : ISqlDao {

    @Resource var sessionFactory: SessionFactory? = null

    fun currentSession(): Session = sessionFactory?.currentSession ?: throw IllegalStateException("can't get the hibernate currentSession")

    override fun <T> listByAliasToBean(beanType: Class<T>, sql: String, params: Array<Any>): java.util.List<T> {
        return SqlQuery(currentSession(), sql).withParams(params).listByAliasToBean(beanType)
    }

    override fun <T> listByAliasToBean(beanType: Class<T>, sql: String): java.util.List<T> {
        return SqlQuery(currentSession(), sql).listByAliasToBean(beanType)
    }

    override fun <T> listByAliasToBean(beanType: Class<T>, sql: String, curPage: Int, pageSize: Int, params: Array<Any>): java.util.List<T> {
        return SqlQuery(currentSession(), sql).withParams(params).pagination(curPage, pageSize).listByAliasToBean(beanType)
    }

    override fun <T> listByAliasToBean(beanType: Class<T>, sql: String, curPage: Int, pageSize: Int): java.util.List<T> {
        return SqlQuery(currentSession(), sql).pagination(curPage, pageSize).listByAliasToBean(beanType)
    }

    override fun <T> getByAliasToBean(beanType: Class<T>, sql: String, params: Array<Any>): T? {
        val list = listByAliasToBean(beanType, sql, params)
        return if (list.size > 0) list[0] else null
    }

    override fun <T> getByAliasToBean(beanType: Class<T>, sql: String): T? {
        val list = listByAliasToBean(beanType, sql)
        return if (list.size > 0) list[0] else null
    }

    override fun execUpdate(sql: String, params: Array<Any>): Int {
        return SqlQuery(currentSession(), sql).withParams(params).execUpdate()
    }

    override fun execBatchUpdate(sql: String, paramsList: Array<Array<Any>>): Array<Int> {
        val rs: Array<Int> = currentSession().doReturningWork { connection ->
            val ps = connection.prepareStatement(sql)
            try {
                for (params in paramsList) {
                    params.forEachIndexed { i, p ->  ps.setObject(i + 1, p)}
                    ps.addBatch()
                }
                ps.executeBatch().toTypedArray()
            } catch (e: SQLException) {
                throw IllegalStateException(e)
            } finally {
                try {
                    ps?.close()
                } catch(e: Exception) {
                }
            }
        }
        return rs
    }

    override fun <C> listAutoCast(sql: String, params: Array<Any>): List<C> {
        return SqlQuery(currentSession(), sql).withParams(params).listAutoCast()
    }

    override fun <C> getAutoCast(sql: String, params: Array<Any>): C {
        return SqlQuery(currentSession(), sql).withParams(params).getAutoCast()
    }
}