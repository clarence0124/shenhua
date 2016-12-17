package com.itspub.core;

import com.alibaba.druid.pool.DruidDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/27.
 */
public class DataSourceHolder {

    private static DataSourceHolder dataSourceHolder = new DataSourceHolder();
    private DataSource dataSource = null;

    private DataSource getDruidDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(G.DATASOURCE_DRIVER);
        ds.setUrl(G.DATASOURCE_URL);
        ds.setUsername(G.DATASOURCE_USERNAME);
        ds.setPassword(G.DATASOURCE_PASSWORD);
        try {
            ds.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ds.setMaxActive(20);
        ds.setInitialSize(1);
        ds.setMaxWait(60000);
        ds.setMinIdle(10);
        ds.setTimeBetweenEvictionRunsMillis(60000);
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setMaxOpenPreparedStatements(20);
        return ds;
    }

    private DataSourceHolder() {
        try {
            if (G.USE_JNDI) {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx .lookup(G.DATASOURCE_JNDI);
                if (null == dataSource) throw new IllegalStateException("can not get Connection");
            } else {
                dataSource = getDruidDataSource();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSourceHolder.dataSource.getConnection();
    }
}
