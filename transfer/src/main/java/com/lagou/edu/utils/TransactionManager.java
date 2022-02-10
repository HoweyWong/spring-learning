package com.lagou.edu.utils;

import java.sql.SQLException;

/**
 * @Description 事务管理器
 * @Author huanghao
 * @Date 2021-4-13
 * @Version 1.0
 */
public class TransactionManager {
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    // 事务方法
    /**
     * TODO 手动提交事务
     *
     * @Date 2021-4-13 22:58
     * @param  
     * @return void
     */
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }

    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }
}
