package com.nokia.mp.testdatacenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nokia.mp.testdatacenter.dao.ProductMapper;
import com.nokia.mp.testdatacenter.model.Product;
import com.nokia.mp.testdatacenter.service.ProductService;

@Service("productService") 
public class ProductServiceImpl implements ProductService {
    
    @Resource(name="productMapper")
    private ProductMapper productMapper;
    
    public List<Product> findProducts() {
        List<Product> productList =productMapper.findProducts();
        return productList;
    }

}
