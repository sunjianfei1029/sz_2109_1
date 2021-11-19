package com.bjpowernode.service.impl;

import com.bjpowernode.dao.AccountDao;
import com.bjpowernode.service.AccountService;
import com.bjpowernode.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private JDBCUtils jdbcUtils;

    public void transfer(String from, String to, int money) {
        Connection conn = null;

        try {
            System.out.println("开启事务");
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false); // 设置手动提交事务：开启事务

            accountDao.updateBalance(from, -money); // 减钱
            //System.out.println(1/0);
            accountDao.updateBalance(to, money); // 加钱
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("回滚事务");
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                System.out.println("提交事务");
                conn.commit();
                // 释放连接
                jdbcUtils.release();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
