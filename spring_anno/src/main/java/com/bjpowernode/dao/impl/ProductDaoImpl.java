package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.ProductDao;
import com.bjpowernode.pojo.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

//@Component("productDao") // 值是实例的唯一标识，默认是类名首字母小写
@Repository
public class ProductDaoImpl implements ProductDao {

    //@Autowired
    @Resource
    private QueryRunner qr;

    public void save(Product product) {
        String sql = "insert into product values(?,?,?,?,?)";
        try {
            qr.update(sql, product.getId(), product.getName(), product.getPrice(), product.getNum(), product.getDescription());
        } catch (SQLException e) {
            e.printStackTrace(); // 打印异常

            // 调用者可以捕获到异常，以便进行事务处理
            throw new RuntimeException(e);
        }
    }

    public List queryList() {
        String sql = "select * from product";
        try {
            return qr.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
