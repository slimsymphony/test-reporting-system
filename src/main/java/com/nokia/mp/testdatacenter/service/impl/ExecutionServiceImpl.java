package com.nokia.mp.testdatacenter.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.nokia.mp.testdatacenter.dao.ExecutionMapper;
import com.nokia.mp.testdatacenter.dao.ProductMapper;
import com.nokia.mp.testdatacenter.dao.TestResultMapper;
import com.nokia.mp.testdatacenter.dao.TestcaseMapper;
import com.nokia.mp.testdatacenter.dao.TestsetMapper;
import com.nokia.mp.testdatacenter.model.Execution;
import com.nokia.mp.testdatacenter.model.Product;
import com.nokia.mp.testdatacenter.model.TestResult;
import com.nokia.mp.testdatacenter.model.Testcase;
import com.nokia.mp.testdatacenter.model.Testset;
import com.nokia.mp.testdatacenter.service.ExecutionService;
import com.nokia.mp.testdatacenter.utils.Utils;
import com.nokia.mp.testdatacenter.utils.XmlUtil;

@Service("executionService") 
public class ExecutionServiceImpl implements ExecutionService {
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
    
    public List<List<String>> findExecutionByConditions(String testset,String product,String startDate,String endDate) throws ParseException{
        List<Execution> executions=findExecutionByAllSortsOfConditions(testset, product, startDate, endDate);
        List<List<String>> tableData = new ArrayList<List<String>>();
        for (Execution execution : executions) {
            List<String> executionsStr = new ArrayList<String>();
            executionsStr.add(execution.getName());
            Product productInDB = productMapper.findProductById(execution.getProductid());
            executionsStr.add(productInDB.getName());
            Testset testsetInDBOut = testsetMapper.findTestsetById(execution.getTestsetId());
            executionsStr.add(testsetInDBOut.getName());
            executionsStr.add(String.valueOf(execution.getPassCnt()));
            executionsStr.add(String.valueOf(execution.getFailCnt()));
            executionsStr.add(String.valueOf(execution.getNoresultCnt()));
            tableData.add(executionsStr);
        }
        return tableData;
    }
    
    private List<Execution> findExecutionByAllSortsOfConditions(String testset,String product,String startDate,String endDate) throws ParseException {
        Testset testsetInDB=null;
        List<Execution> executions=null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionByAllConditions(testsetId, productInDB.getId(), startts, endts);
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            executions = executionMapper.findAllExecutions();
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            executions = executionMapper.findExecutionsByEndTime(endts);
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            executions = executionMapper.findExecutionsByTestsetAndStartTime(testsetId, startts);
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByTestsetAndProductAndStartTime(testsetId, productInDB.getId(), startts);
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByTestsetAndProductAndEndTime(testsetId, productInDB.getId(), endts);
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            executions = executionMapper.findExecutionsByTestsetAndEndTime(testsetId, endts);
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByTestsetAndProduct(testsetId, productInDB.getId());
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            executions = executionMapper.findExecutionsByTestsetAndStartTimeAndEndTime(testsetId, startts, endts);
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            executions = executionMapper.findExecutionsByStartTime(startts);
        } else if (!StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            testsetInDB = testsetMapper.getTestset(testset);
            int testsetId = testsetInDB.getId();
            executions = executionMapper.findExecutionsByTestset(testsetId);
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByProduct(productInDB.getId());
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            executions = executionMapper.findExecutionsByStartTimeAndEndTime(startts, endts);
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByProductAndStartTimeAndEndTime(productInDB.getId(), startts, endts);
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && !StringUtils.equalsIgnoreCase(startDate, "noSelected") && StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date starttime = df.parse(startDate + " 00:00:00");
            Timestamp startts = new Timestamp(starttime.getTime());
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByProductAndStartTime(productInDB.getId(), startts);
        } else if (StringUtils.equalsIgnoreCase(testset, "all") && !StringUtils.equalsIgnoreCase(product, "all")
                && StringUtils.equalsIgnoreCase(startDate, "noSelected") && !StringUtils.equalsIgnoreCase(endDate, "noSelected")) {
            Date endtime = df.parse(endDate + " 23:59:59");
            Timestamp endts = new Timestamp(endtime.getTime());
            Product productInDB = productMapper.findProductByName(product);
            executions = executionMapper.findExecutionsByProductAndEndTime(productInDB.getId(), endts);
        } else {
            System.out.println("no suitable condition found");
        }
        
        return executions;
    }

    @Override
    public Map<String, String> addExecution(String reportURL,String name,String starttime,String endtime,String product) throws DocumentException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(reportURL)){
            String reportContent = getURLContent(reportURL);
            Execution execution = new Execution();
            execution.setName(name);
            execution.setStarttime(Utils.str2Timestamp(starttime));
            execution.setEndtime(Utils.str2Timestamp(endtime));
            Product productInDB = productMapper.findProductByName(product);
            execution.setProductid(productInDB.getId());
            execution.setReport(reportContent);
            processReportContent(execution);
            map.put("isSuccess", "true");
        }
        else {
            map.put("isSuccess", "false");
        }
        return map;
    }
    
    private String getURLContent(String urlStr) throws IOException {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String contentStr = "";
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                contentStr += inputLine;
            }
            br.close();
            return contentStr;
    }
    
    private void processReportContent(Execution execution) throws DocumentException {
        // add report parsing and store-to-DB process here.
        XmlUtil.parseNjuint(execution);
        String testsetName = execution.getTestsetName();
        Testset testset = testsetMapper.getTestset(testsetName);
        int testsetId = testset.getId();
        execution.setTestsetId(testsetId);
        executionMapper.insertExecution(execution);
        List<TestResult> testResults = execution.getTestResults();
        for (TestResult testResult : testResults) {
            String scriptId = testResult.getScriptId();
            Testcase testcase = testcaseMapper.getTestcase(scriptId);
            int testcaseId = testcase.getId();
            testResult.setCaseId(testcaseId);
            testResult.setExecId(execution.getId());
            testResultMapper.insertTestResult(testResult);
        }
    }
}
