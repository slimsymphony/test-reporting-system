package com.nokia.mp.testdatacenter.controller;

import com.nokia.mp.testdatacenter.model.Test;
import com.nokia.mp.testdatacenter.model.TestPassrate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author larryang
 */
@Controller
@RequestMapping("/api")
public class TestInquiryController {

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Map> listProducts() {

        //Below is just some test data, you may need to fetch from DB the way UserController do.
        List<Map> products = new ArrayList();
        Map product1 = new HashMap();
        product1.put("id", "libra3g");
        product1.put("text", "libra3g");
        Map product2 = new HashMap();
        product2.put("id", "athena");
        product2.put("text", "athena");
        
        products.add(product1);
        products.add(product2);
        
        //Todo: fetch data from DB the way User Controller do.
        
        return products;  
    }
    
    @RequestMapping(value = "/test/list/{product}/{testtype}", method = RequestMethod.GET)
    public @ResponseBody
    Map listTests(@PathVariable String product, @PathVariable String testtype) {
        System.out.println(product+";"+testtype);

        //Below is just some test data, you may need to fetch from DB the way UserController do.
        List tests = new ArrayList();
        List test1 = new ArrayList();
        test1.add("test1" + "_" + product + "_" + testtype);
        test1.add("unittest");
        List test2 = new ArrayList();
        test2.add("test2" + "_" + product + "_" + testtype);
        test2.add("granite");
        
        tests.add(test1);
        tests.add(test2);
        
        Map result = new HashMap();
        result.put("draw", 0);
        result.put("recordsTotal", 2);
        result.put("recordsFiltered", 2);
        result.put("data", tests);
        //Todo: fetch data from DB the way User Controller do.
        
        return result;  
    }

}