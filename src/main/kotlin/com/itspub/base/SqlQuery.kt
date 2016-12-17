package com.itspub.base

import org.hibernate.SQLQuery
import org.hibernate.Session
import org.hibernate.transform.ResultTransformer

/**
 * Created by Administrator on 2016/12/17.
 */
class SqlQuery constructor(val session: Session?, val queryString: String?) {

    var query: SQLQuery? = session?.createSQLQuery(queryString)

    fun withParams(params: Array<Object>?): SqlQuery {
        params?.forEachIndexed { i, x ->
            query?.setParameter(i, x)
        }
        return this
    }

    fun pagination(curPage: Int, pageSize: Int): SqlQuery {
        query?.firstResult = ((curPage - 1) * pageSize + 1)
        query?.maxResults = (pageSize)
        return this
    }

    private fun resultTransformer(transformer: ResultTransformer): SqlQuery {
        query?.setResultTransformer(transformer)
        return this
    }

    fun <T> listByAliasToBean(beanType: Class<T>): List<T>? {
        resultTransformer(CamelCaseAliasToBeanTransformer.toBean(beanType))
        return query?.list() as? List<T>
    }

    fun <T> getByAliasToBean(beanType: Class<T>): T? {
        resultTransformer(CamelCaseAliasToBeanTransformer.toBean(beanType))
        return query?.uniqueResult() as? T
    }
}