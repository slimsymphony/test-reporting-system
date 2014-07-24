package com.nokia.mp.testdatacenter.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.nokia.mp.testdatacenter.dao.TestcaseMapper;
import com.nokia.mp.testdatacenter.dao.TestsetCaseMapper;
import com.nokia.mp.testdatacenter.dao.TestsetMapper;
import com.nokia.mp.testdatacenter.model.Testcase;
import com.nokia.mp.testdatacenter.model.Testset;
import com.nokia.mp.testdatacenter.model.TestsetCase;
import com.nokia.mp.testdatacenter.service.TestsetService;
import com.nokia.mp.testdatacenter.utils.Constants;
import com.nokia.mp.testdatacenter.utils.XmlUtil;

@Service("testsetService") 
public class TestsetServiceImpl implements TestsetService {
    
    private Log logger = LogFactory.getLog(TestsetServiceImpl.class);
    @Resource(name="testsetMapper")
    private TestsetMapper testsetMapper;
    
    @Resource(name="testcaseMapper")
    private TestcaseMapper testcaseMapper;
    @Resource(name="testsetCaseMapper")
    private TestsetCaseMapper testsetCaseMapper;
    
    public Testset findTestsetById(int testsetId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Testset> findAllTestsets(){
        List<Testset> testsets=testsetMapper.findAllTestsets();
        return testsets;
    }

    @Override
    public Map<String, String> updateTestset(String testsetURL) throws IOException, DocumentException{
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(testsetURL)) {
            processTestset(testsetURL);
            map.put("isSuccess", "true");
        }
        else {
            map.put("isSuccess", "false");
        }
        return map;
    }
    
    private void processTestset(String testsetURL) throws IOException, DocumentException {
        String testsetName = StringUtils.substringAfterLast(testsetURL, "/");
        Testset testset = findTestset(testsetName);
        int testsetId=0;
        if (testset == null) {
            logger.info("No " + testsetName + " exists, will insert");
            Testset testsetInDB = addTestset(testsetName);
            testsetId = testsetInDB.getId();
        } else {
            testsetId=testset.getId();
        }
        String testsetContent = getURLContent(testsetURL);
        processTestsetContent(testsetId,testsetContent);
    }
    
    private Testset findTestset(String name) {
        Testset testset = testsetMapper.getTestset(name);
        return testset;
    }

    private Testset addTestset(String name) {
        Testset testset = new Testset();
        testset.setName(name);
        testsetMapper.insertTestset(testset);
        return testset;
    }
    
    private void processTestsetContent(int testsetId, String testsetContent) throws DocumentException {
        List<Testcase> testcases = XmlUtil.parseTestset(testsetContent);
        for (Testcase testcase : testcases) {
            Testcase testcaseInDB = testcaseMapper.getTestcase(testcase.getCaseId());
            if (testcaseInDB == null) {
                testcaseMapper.insertTestcase(testcase);
                int testcaseId = testcase.getId();
                TestsetCase testsetCase = new TestsetCase();
                testsetCase.setTestcaseId(testcaseId);
                testsetCase.setTestsetId(testsetId);
                testsetCaseMapper.insertTestsetCase(testsetCase);
            } else {
                int testcaseId = testcaseInDB.getId();
                TestsetCase TestsetCaseInDB = testsetCaseMapper.getTestsetCase(testsetId, testcaseId);
                if (TestsetCaseInDB == null) {
                    TestsetCase testsetCase = new TestsetCase();
                    testsetCase.setTestcaseId(testcaseId);
                    testsetCase.setTestsetId(testsetId);
                    testsetCaseMapper.insertTestsetCase(testsetCase);
                }

            }
        }
    }
    
    private String getURLContent(String urlStr) throws IOException {
        String contentStr = "";
        if (StringUtils.startsWithIgnoreCase(urlStr, Constants.HTTP_PROTOCOL)) {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine = "";

            while ((inputLine = br.readLine()) != null) {
                contentStr += inputLine;
            }
            br.close();
        } else {
            urlStr = StringUtils.substringAfterLast(urlStr, Constants.URL_START);
            urlStr = Constants.URL_START_REPLACEMENT + urlStr;
            File testSetFile = new File(urlStr);
            StringBuffer sb = new StringBuffer();
            if (testSetFile.exists()) {
                logger.info("testSet exsits");
            }
            try {
                BufferedReader in = new BufferedReader(new FileReader(testSetFile));
                String str;
                while ((str = in.readLine()) != null) {
                    if (StringUtils.contains(str, "encoding=")) {
                        continue;
                    } else {
                        sb.append(str);
                    }
                }
                contentStr = sb.toString();
                in.close();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        return contentStr;
    }

}
