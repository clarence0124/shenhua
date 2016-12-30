package com.itspub.util

import java.sql.Connection
import java.sql.SQLException

/**
 * Created by Administrator on 2016/12/28.
 */
object DbUtils {
    @JvmStatic
    @Throws(SQLException::class)
    fun <T> loanResultSet(conn: Connection, sql: String, params: Array<Any>, handler: ResultSetHandler<T>): List<T> {
        val ps = conn.prepareStatement(sql)
        try {
            params.forEachIndexed { i, any ->
                ps.setObject(i + 1, params[i])
            }
            val rs = ps.executeQuery()
            try {
                return handler.doWitResultSet(rs)
            } finally {
                rs?.close()
            }
        } finally {
            ps?.close()
        }
    }
}