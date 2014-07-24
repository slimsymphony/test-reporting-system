/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.Product;

/**
 * 
 * @author Duan Karl
 */
@Repository("productMapper")
public interface ProductMapper {

    public void insertProduct(Product product);
    public List<Product> findProducts();
    public Product findProductById(int productId);
    public Product findProductByName(String name);
}
