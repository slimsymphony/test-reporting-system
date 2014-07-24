package com.nokia.mp.testdatacenter.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nokia.mp.testdatacenter.model.Product;
import com.nokia.mp.testdatacenter.service.ProductService;

/**
 *
 * @author Duan Karl
 */
@Controller
@RequestMapping("/api")
public class ProductController {
    
    @Resource(name="productService")
    private ProductService productService;
    
    @RequestMapping(value = "/product/findAllProducts", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String,String>> findAllProducts() {
        List<Map<String,String>> products = new ArrayList<Map<String,String>>();
        List<Product> productList = productService.findProducts();
        for (Product product : productList) {
            Map<String, String> productMap = new HashMap<String, String>();
            productMap.put("id", product.getName());
            productMap.put("text", product.getName());
            products.add(productMap);
        }
        return products;
    }
}