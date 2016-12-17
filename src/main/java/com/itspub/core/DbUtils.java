package com.itspub.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public class DbUtils {



    public static <T> List<T> loanResultSet(Connection conn, String sql, Object[] params, ResultSetHandler<T> handler) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            if (null != params) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i+1, params[i]);
                }
            }
            try (ResultSet rs = preparedStatement.executeQuery()) {
                return handler.doWitResultSet(rs);
            }
        }
    }
}
