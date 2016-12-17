package com.itspub.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public interface ResultSetHandler<T> {
    List<T> doWitResultSet(ResultSet rs) throws SQLException;
}