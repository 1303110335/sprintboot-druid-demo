/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.druid.demo.handler;


import com.druid.demo.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xuleyan
 * @version JdbcTemplate.java, v 0.1 2020-09-24 10:47 下午
 */
public class JdbcTemplate2 {
    /**
     * 工具类，私有化无参构造函数
     */
    private JdbcTemplate2() {
    }

    /**
     * DML 操作模板方法
     * @param sql 执行操作的 SQL 语句
     * @param arguments SQL 语句参数
     */
    public static int update(String sql, Object... arguments) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement(sql);
            if (arguments != null && arguments.length > 0) {
                for (int i = 0; i < arguments.length; i++) {
                    pStatement.setObject(i + 1, arguments[i]);
                }
            }
            return pStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.releaseSqlConnection(null, pStatement, connection);
        }
        return 0;
    }

    /**
     * DQL 操作模板
     * @param sql 执行操作的 SQL 语句
     * @param handler 对数据库返回结果集进行装箱的操作类
     * @param arguments SQL 语句参数
     * @return 返回数据库查询结果集
     */
    public static <T> T query(String sql, IResultSetHandler<T> handler, Object... arguments) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = connection.prepareStatement(sql);
            if (arguments != null && arguments.length > 0) {
                for (int i = 0; i < arguments.length; i++) {
                    pStatement.setObject(i + 1, arguments[i]);
                }
            }
            rSet = pStatement.executeQuery();
            return handler.handler(rSet);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DruidUtil.releaseSqlConnection(rSet, pStatement, connection);
        }
        return null;
    }
}