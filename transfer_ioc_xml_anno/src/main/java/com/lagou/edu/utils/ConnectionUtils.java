package com.lagou.edu.utils;

import com.lagou.edu.framework.annotation.MyRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-13
 * @Version 1.0
 */
@MyRepository("connectionUtils")
public class ConnectionUtils {
    // 存储当前线程的连接
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getCurrentThreadConn() throws SQLException {
        // 判断是否已有连接，无则从连接池获取
        Connection connection = threadLocal.get();
        if (connection == null){
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}
