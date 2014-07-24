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
@RequestMapping("/api/statistics")
public class StatisticsController {

    @RequestMapping(value = "/passrate/{product}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> passrate(@PathVariable String product) {
        System.out.println(product);
        List<TestPassrate> list = new ArrayList<TestPassrate>();  
        TestPassrate testPassrate = new TestPassrate();  
        testPassrate.setPeriod("2010 Q1");
        testPassrate.setSuccess(100);
        testPassrate.setUnstable(10);
        testPassrate.setFailed(12);
        list.add(testPassrate);  
        
        TestPassrate testPassrate2 = new TestPassrate();  
        testPassrate2.setPeriod("2010 Q2");
        testPassrate2.setSuccess(110);
        testPassrate2.setUnstable(12);
        testPassrate2.setFailed(15);
        list.add(testPassrate2);
        
        TestPassrate testPassrate3 = new TestPassrate();  
        testPassrate3.setPeriod("2010 Q3");
        testPassrate3.setSuccess(150);
        testPassrate3.setUnstable(25);
        testPassrate3.setFailed(22);
        list.add(testPassrate3);
        
        //todo: you can use DB data like UserController.
        
        Map<String, Object> modelMap = new HashMap<String, Object>(3);  
        modelMap.put("total", "1");  
        modelMap.put("data", list);  
        modelMap.put("success", "true");  
        return modelMap;  
    }

}