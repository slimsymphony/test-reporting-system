/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.TestsetCase;

/**
 * 
 * @author Duan Karl
 */
@Repository("testsetCaseMapper")
public interface TestsetCaseMapper {

    public void insertTestsetCase(TestsetCase testsetCase);

    public TestsetCase getTestsetCase(@Param(value="testsetId") int testsetId,@Param(value="testcaseId") int testcaseId);
}
