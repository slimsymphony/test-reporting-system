package com.nokia.mp.testdatacenter.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nokia.mp.testdatacenter.model.Execution;
import com.nokia.mp.testdatacenter.model.TestResult;
import com.nokia.mp.testdatacenter.model.Testcase;



public class XmlUtil {

    private XmlUtil() {
    }

    @SuppressWarnings("unchecked")
    public static void parseNjuint(Execution exectuion) throws DocumentException {
        Document doc = DocumentHelper.parseText(exectuion.getReport());
        List<Element> list = doc.getRootElement().selectNodes(Constants.TESTSUITE_NODE );
        long totaltimeCost = 0;
        int passCnt=0;
        int failCnt=0;
        int noresultCnt=0;
        String testsetName = doc.getRootElement().attributeValue("name");
        if (testsetName != null) {
            exectuion.setTestsetName(testsetName);
        }
        String robtiumRunTime = doc.getRootElement().attributeValue("time");
        if (robtiumRunTime != null) {
            float second = Float.parseFloat(robtiumRunTime) * 1000;
            long secondToMillis = Math.round(second);
            totaltimeCost = secondToMillis;
        }
        ArrayList<TestResult> testResults = new ArrayList<TestResult>();
        for (Element el : list) {
            List<Element> cases = el.selectNodes(Constants.TESTCASE_NODE);
            for (Element cas : cases) {
                TestResult result=new TestResult();
                String time = cas.attributeValue("time");
                if (StringUtils.contains(time, "s")) {
                    long timeCost = getTimeCost(time);
                    result.setDuration(timeCost);
                    totaltimeCost += timeCost;
                }
                Element fn = (Element) cas.selectSingleNode("failure");
                Element na = (Element) cas.selectSingleNode("na");
                if (fn != null) {
                    result.setDetail(fn.attributeValue("detail"));
                    result.setMessage(fn.attributeValue("message"));
                    result.setResult(Constants.TESTCASE_RESULT.FAILURE.toString());
                    failCnt++;
                } else if (na != null) {
                    result.setResult(Constants.TESTCASE_RESULT.NORESULT.toString());
                    result.setDetail(na.attributeValue("detail"));
                    result.setMessage(na.attributeValue("message"));
                    noresultCnt++;
                } else {
                    result.setResult(Constants.TESTCASE_RESULT.SUCCESS.toString());
                    passCnt++;
                }
                String script = cas.attributeValue("script");
                if (script != null) {
                    String[] splitScriptStr = StringUtils.split(script, "\\");
                    script = splitScriptStr[splitScriptStr.length - 1];
                    result.setScriptId(script);
                }
                testResults.add(result);
            }
        }
        exectuion.setFailCnt(failCnt);
        exectuion.setNoresultCnt(noresultCnt);
        exectuion.setPassCnt(passCnt);
        exectuion.setTestResults(testResults);
        exectuion.setDuration(totaltimeCost);
    }

    public static long getTimeCost(String time) {
        long timeCost = 0;
        if (StringUtils.contains(time, "h")) {
            String hourStr = StringUtils.split(time, "h")[0].trim();
            long hourToMillis = TimeUnit.HOURS.toMillis(Long.parseLong(hourStr));
            long minuteToMillis = 0;
            long secondToMillis = 0;
            if (StringUtils.contains(time, "m")) {
                String minuteStr = StringUtils.substringBetween(time, "h", "m").trim();
                minuteToMillis = TimeUnit.MINUTES.toMillis(Long.parseLong(minuteStr));
            }
            if (StringUtils.contains(time, "s")) {
                String secondStr = StringUtils.substringBetween(time, "m", "s").trim();
                float second = Float.parseFloat(secondStr) * 1000;
                secondToMillis = Math.round(second);
            }
            timeCost = hourToMillis + minuteToMillis + secondToMillis;
        } else if (StringUtils.contains(time, "m")) {
            String minuteStr = StringUtils.split(time, "m")[0].trim();
            long minuteToMillis = TimeUnit.MINUTES.toMillis(Long.parseLong(minuteStr));
            long secondToMillis = 0;
            if (StringUtils.contains(time, "s")) {
                String secondStr = StringUtils.substringBetween(time, "m", "s").trim();
                float second = Float.parseFloat(secondStr) * 1000;
                secondToMillis = Math.round(second);
            }
            timeCost = minuteToMillis + secondToMillis;
        } else if (StringUtils.contains(time, "s")) {
            String secondStr = StringUtils.split(time, "s")[0].trim();
            float second = Float.parseFloat(secondStr) * 1000;
            long secondToMillis = Math.round(second);
            timeCost = secondToMillis;
        }
        return timeCost;
    }

    public static String getDurationAsString(long milliseconds) {

        String duration = "UNKNOWN";
        boolean enableHours = false;
        boolean enableMillis = false;
        boolean enableMinutes = false;
        // Hours
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        if (0 < hours) {
            enableHours = true;
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
        if (!enableHours && 0 < minutes) {
            // Enable minutes
            enableMinutes = true;
        } else {
            // Enable milliseconds
            enableMillis = true;
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds);
        // Create duration string
        if (enableHours) {
            // Create time with seconds as the most accurate unit
            duration = String.format("%d h %d m %d s", hours, minutes, seconds);
        } else if (enableMinutes) {
            // Create time without hours or minutes
            duration = String.format("%d m %d s", minutes, seconds);
        } else if (enableMillis) {
            // Create time without hours or minutes
            duration = String.format("%d.%03d s", seconds, milliseconds);
        } else {
            // No implementation required
        }
        return duration;
    }
    
    
    public static List<Testcase> parseTestset( String testsetContent ) throws DocumentException{
        Document doc = DocumentHelper.parseText( testsetContent );
        @SuppressWarnings("unchecked")
        List<Element> cases = doc.getRootElement().elements();
        List<Testcase> testcases=new ArrayList<Testcase>();
        
            for ( Element caze : cases ) {
                Testcase testcase = new Testcase();
                String caseName = caze.attributeValue("name");
                Element testscript = caze.element("testscript");
                String directory = testscript.attributeValue("directory");
                String[] directory_array = StringUtils.split(directory, "\\");
                directory=directory_array[directory_array.length-1];
                String file = testscript.attributeValue("file");
                String clazz = testscript.attributeValue("class");
                String method = testscript.attributeValue("method");
                String caseId = directory+"."+file+"."+clazz+"."+method;
                testcase.setCaseName(caseName);
                testcase.setClazz(clazz);
                testcase.setDirectory(directory);
                testcase.setFile(file);
                testcase.setMethod(method);
                testcase.setCaseId(caseId);
                testcases.add(testcase);
            }
            return testcases;
        }
        
}
