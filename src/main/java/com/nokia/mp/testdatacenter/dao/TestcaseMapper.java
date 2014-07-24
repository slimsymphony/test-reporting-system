/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.Testcase;

/**
 * 
 * @author larryang
 */
@Repository("testcaseMapper")
public interface TestcaseMapper {

    public void insertTestcase(Testcase testcase);

    public Testcase getTestcase(String caseId);
}
