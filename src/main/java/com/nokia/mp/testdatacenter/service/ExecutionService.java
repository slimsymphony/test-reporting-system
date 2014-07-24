package com.nokia.mp.testdatacenter.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

public interface ExecutionService {
    public List<List<String>> findExecutionByConditions(String testset,String product,String startDate,String endDate) throws ParseException;
    public Map<String, String> addExecution(String reportURL,String name,String starttime,String endtime,String product)throws DocumentException, IOException;
}
