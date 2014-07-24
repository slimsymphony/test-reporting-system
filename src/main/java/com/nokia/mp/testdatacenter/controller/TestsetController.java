package com.nokia.mp.testdatacenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nokia.mp.testdatacenter.model.Testset;
import com.nokia.mp.testdatacenter.service.TestsetService;

/**
 * 
 * @author Duan Karl
 */
@Controller
@RequestMapping("/api/testset")
public class TestsetController {
    
    private Log logger = LogFactory.getLog(TestsetController.class);
    @Resource(name="testsetService")
    private TestsetService testsetService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> updateTestset(String testsetURL) {

        Map<String, String> map = new HashMap<String, String>();
        try {
            map.putAll(testsetService.updateTestset(testsetURL));
        } catch (Exception e) {
            logger.info("updateTestset() got exception: "+e.getMessage());
            map.put("isSuccess", "false");
        }
        return map;
    }

    


    @RequestMapping(value = "/findAllTestsets", method = RequestMethod.GET)
    public @ResponseBody  List<Map<String,String>> findAllTestsets() {
        List<Map<String,String>> testsetMaps = new ArrayList<Map<String,String>>();
        List<Testset> testsets = testsetService.findAllTestsets();
        for (Testset testset : testsets) {
            Map<String, String> testsetMap = new HashMap<String, String>();
            testsetMap.put("id", testset.getName());
            testsetMap.put("text", testset.getName());
            testsetMaps.add(testsetMap);
        }
        return testsetMaps;
    }

}