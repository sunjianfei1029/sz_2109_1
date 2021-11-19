package com.bjpowernode.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class JDBCUtils {
    @Autowired
    private DataSource dataSource;

    // ThreadLocal: 将对象和线程绑定，可以保证在同一个线程中获取到相同的实例！
    // 3个方法：set\get\remove
    private static final ThreadLocal<Connection> TL = new ThreadLocal<>();

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = TL.get(); // 获取线程上绑定的对象
            if (conn == null) {
                // 当前线程中该方法被第1次调用
                conn = dataSource.getConnection();
                TL.set(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(conn);
        return conn;
    }

    public void release() {
        try {
            // 不是真正的关闭，而是还给连接池
            getConnection().close();
            TL.remove(); // 手动解除绑定
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
