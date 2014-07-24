package com.nokia.mp.testdatacenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.nokia.mp.testdatacenter.service.ExecutionService;

/**
 * 
 * @author Duan Karl
 */
@Controller
@RequestMapping("/api/report")
public class ExecutionController {
    private Log logger = LogFactory.getLog(ContextLoader.class);
    
    @Resource(name="executionService")
    private ExecutionService executionService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> addExecution(String reportURL,String name,String starttime,String endtime,String product) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.putAll(executionService.addExecution(reportURL, name, starttime, endtime, product));
        } catch (Exception e) {
            map.put("isSuccess", "false");
            logger.info("addExecution() got exception: "+e.getMessage());
        }
        return map;
    }
    
    
    @RequestMapping(value = "/execution/list/{testset}/{product}/{startDate}/{endDate}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> findExecutionByAllConditions(@PathVariable String testset,@PathVariable String product,@PathVariable String startDate,@PathVariable String endDate) {
        logger.info("findExecutionByAllConditions is started...");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<List<String>> tableData = executionService.findExecutionByConditions( testset, product, startDate, endDate);
            map.put("draw", 0);
            map.put("recordsTotal", tableData.size());
            map.put("recordsFiltered", tableData.size());
            map.put("data", tableData);
        } catch (Exception e) {
            map.put("isSuccess", "false");
            logger.info("findExecutionByAllConditions() got exception: "+e.getMessage());
        }
        return map;
    }
    
}