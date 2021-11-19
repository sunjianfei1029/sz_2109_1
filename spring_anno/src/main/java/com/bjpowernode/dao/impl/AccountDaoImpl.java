package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.AccountDao;
import com.bjpowernode.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private QueryRunner qr;
    @Autowired
    private JDBCUtils jdbcUtils;

    public void updateBalance(String no, int money) {
        String sql = "update account set balance=balance+? where no=?";
        try {
            /*
                方法底层通过连接池对象从池中随机抓取连接对象，和业务层中获取的连接一定不是同一个!
                不指定连接时，操作完成之后，连接会被关闭！
             */
            //qr.update(sql, money, no);

            /*
            // 使用指定的连接完成操作，连接不会自动关闭
             */
            qr.update(jdbcUtils.getConnection(),sql, money, no);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
