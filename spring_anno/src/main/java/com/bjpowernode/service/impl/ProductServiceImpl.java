package com.bjpowernode.service.impl;

import com.bjpowernode.dao.ProductDao;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    //@Autowired // 根据类型注入
    //@Qualifier("productDaoImpl") // 根据id进行唯一匹配
    @Resource //(name="productDaoImpl")
    private ProductDao productDao;

    public void save(Product product) {
        //System.out.println(username);
        productDao.save(product);
    }

    public List queryList() {
        return productDao.queryList();
    }
}
