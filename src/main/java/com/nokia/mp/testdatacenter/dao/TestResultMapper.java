/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.TestResult;

/**
 * 
 * @author larryang
 */
@Repository("testResultMapper")
public interface TestResultMapper {

    public void insertTestResult(TestResult testResult);

    public TestResult getTestResult(String caseId);
}
