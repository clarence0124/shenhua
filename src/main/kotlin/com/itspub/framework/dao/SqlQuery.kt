package com.itspub.framework.dao

import org.hibernate.SQLQuery
import org.hibernate.Session
import org.hibernate.transform.ResultTransformer

/**
 * Created by Administrator on 2016/12/17.
 */
class SqlQuery constructor(session: Session, queryString: String) {

    var query: SQLQuery = session.createSQLQuery(queryString)

    fun withParams(params: Array<Any>): SqlQuery {
        params.forEachIndexed { i, x ->
            query.setParameter(i, x)
        }
        return this
    }

    fun pagination(curPage: Int, pageSize: Int): SqlQuery {
        query.firstResult = ((curPage - 1) * pageSize + 1)
        query.maxResults = (pageSize)
        return this
    }

    private fun resultTransformer(transformer: ResultTransformer): SqlQuery {
        query.setResultTransformer(transformer)
        return this
    }

    fun <T> listAutoCast(): java.util.List<T> {
        return query.list() as java.util.List<T>
    }

    fun <T> getAutoCast(): T {
        return query.uniqueResult() as T
    }

    fun <T> listByAliasToBean(beanType: Class<T>): java.util.List<T> {
        resultTransformer(CamelCaseAliasToBeanTransformer(beanType))
        return query.list() as java.util.List<T>
    }

    fun <T> getByAliasToBean(beanType: Class<T>): T? {
        resultTransformer(CamelCaseAliasToBeanTransformer(beanType))
        return query.uniqueResult() as? T
    }

    fun execUpdate(): Int {
        return query.executeUpdate()
    }

}