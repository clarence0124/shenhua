package com.wsc;

import com.itspub.core.DataSourceHolder;
import com.itspub.core.DbUtils;
import com.itspub.core.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public class ProjectService {

    public static List<ProjectInfo> listProjectInfo(Integer page, Integer rows) {
        final String sql = "select o.id, o.projectcode projectCode, o.projectname projectName, o.createEmployee from ProjectInfo o where o.isDelete = 0";
        try (final Connection connection = DataSourceHolder.getConnection()) {
            List<ProjectInfo> list = DbUtils.loanResultSet(connection, sql, null, new ResultSetHandler<ProjectInfo>() {
                @Override
                public List<ProjectInfo> doWitResultSet(ResultSet rs) throws SQLException {
                    List<ProjectInfo> list = new ArrayList<>();
                    while(rs.next()) {
                        ProjectInfo pi = new ProjectInfo();
                        pi.setId(rs.getString("id"));
                        pi.setProjectName(rs.getString("projectName"));
                        pi.setProjectCode(rs.getString("projectCode"));
                        pi.setCreateEmployee(rs.getString("createEmployee"));
                        list.add(pi);
                    }
                    return list;
                }
            });
            return list;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 列出用户名
     * @return
     * @throws SQLException
     */
    public static List<String> listUserName() throws SQLException {
        try (Connection connection = DataSourceHolder.getConnection()) {
            try (Statement statement = connection.createStatement()) {

                try (ResultSet rs = statement.executeQuery("select accounts from s_account")) {
                    List<String> list = new ArrayList<>();
                    while(rs.next()) {
                        list.add(rs.getString(1));
                    }
                    return list;
                }
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
