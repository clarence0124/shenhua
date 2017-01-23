package com.itspub.util

import java.sql.ResultSet
import java.sql.SQLException

/**
 * Created by Administrator on 2016/12/29.
 */
interface ResultSetHandler<T> {
    @Throws(SQLException::class)
    fun doWithResultSet(rs: ResultSet): List<T>
}