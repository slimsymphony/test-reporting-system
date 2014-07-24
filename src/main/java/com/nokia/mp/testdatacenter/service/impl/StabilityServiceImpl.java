/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.nokia.mp.testdatacenter.dao.ExecutionMapper;
import com.nokia.mp.testdatacenter.dao.ProductMapper;
import com.nokia.mp.testdatacenter.dao.TestResultMapper;
import com.nokia.mp.testdatacenter.dao.TestcaseMapper;
import com.nokia.mp.testdatacenter.dao.TestsetMapper;
import com.nokia.mp.testdatacenter.model.Execution;
import com.nokia.mp.testdatacenter.model.Product;
import com.nokia.mp.testdatacenter.model.Testset;
import com.nokia.mp.testdatacenter.service.StabilityService;

/**
 *
 * @author b11wu
 */

@Service("stabilityStatusService")
public class StabilityServiceImpl implements StabilityService {
    @Resource(name="executionMapper")
    private ExecutionMapper executionMapper;
    @Resource(name="testsetMapper")
    private TestsetMapper testsetMapper;
    @Resource(name="testcaseMapper")
    private TestcaseMapper testcaseMapper;
    @Resource(name="testResultMapper")
    private TestResultMapper testResultMapper;
    @Resource(name="productMapper")
    private ProductMapper productMapper;


    public List<List<String>> findExecutionsByConditions(
            String testset,
            String product,
            String trigger,
            String startDate,
            String endDate) throws ParseException{
        List<Execution> executions=findExecutionByAllSortsOfConditions(testset, product, trigger, startDate, endDate);
        List<List<String>> tableData = new ArrayList<List<String>>();
        for (Execution execution : executions) {
            List<String> executionsStr = new ArrayList<String>();
            executionsStr.add(String.valueOf(execution.getId()));            // id
            executionsStr.add(execution.getName());          // execution name
            executionsStr.add(execution.getStatus());        // status
            executionsStr.add(String.valueOf(execution.getStarttime()));     // start time

            Product productInDB = productMapper.findProductById(execution.getProductid());
            executionsStr.add(productInDB.getName());        // product name

            Testset testsetInDBOut = testsetMapper.findTestsetById(execution.getTestsetId());
            executionsStr.add(testsetInDBOut.getName());     // testset name

            executionsStr.add(String.valueOf(execution.getPassCnt()));
            executionsStr.add(String.valueOf(execution.getFailCnt()));
            executionsStr.add(String.valueOf(execution.getNoresultCnt()));
            tableData.add(executionsStr);
        }
        return tableData;
    }

    private List<Execution> findExecutionByAllSortsOfConditions(String testset,String product,String trigger,String startDate,String endDate) throws ParseException {
        List<Execution> executions=null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")&&StringUtils.equalsIgnoreCase(trigger, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            executions = executionMapper.findExecutionsByStartTimeAndEndTime(startts, endts);
        }
        return executions;
    }

}
