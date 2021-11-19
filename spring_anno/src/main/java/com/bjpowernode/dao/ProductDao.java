package com.bjpowernode.dao;

import com.bjpowernode.pojo.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void save(Product product);
    List queryList();
}
