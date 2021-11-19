package com.bjpowernode.service;

import com.bjpowernode.pojo.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    List queryList();
}
