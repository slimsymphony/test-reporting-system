/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.Execution;

/**
 * 
 * @author Duan Karl
 */
@Repository("executionMapper")
public interface ExecutionMapper {

    public void insertExecution(Execution exectuion);

    public Execution getExecution(String name);
    public List<Execution> findExecutionByAllConditions(@Param(value="testsetId")int testsetId,@Param(value="productid")int productid,@Param(value="starttime")Timestamp starttime,@Param(value="endtime")Timestamp endtime);
    public List<Execution> findAllExecutions();
    public List<Execution> findExecutionsByEndTime(Timestamp endtime);
    public List<Execution> findExecutionsByTestsetAndStartTime(@Param(value="testsetId")int testsetId,@Param(value="starttime")Timestamp starttime);
    public List<Execution> findExecutionsByTestsetAndProductAndStartTime(@Param(value="testsetId")int testsetId,@Param(value="productid")int productid,@Param(value="starttime")Timestamp starttime);
    public List<Execution> findExecutionsByTestsetAndProductAndEndTime(@Param(value="testsetId")int testsetId,@Param(value="productid")int productid,@Param(value="endtime")Timestamp endtime);
    public List<Execution> findExecutionsByTestsetAndEndTime(@Param(value="testsetId")int testsetId,@Param(value="endtime")Timestamp endtime);
    public List<Execution> findExecutionsByTestsetAndProduct(@Param(value="testsetId")int testsetId,@Param(value="productid")int productid);
    public List<Execution> findExecutionsByTestsetAndStartTimeAndEndTime(@Param(value="testsetId")int testsetId,@Param(value="starttime")Timestamp starttime,@Param(value="endtime")Timestamp endtime);
    public List<Execution> findExecutionsByStartTime(Timestamp starttime);
    public List<Execution> findExecutionsByTestset(@Param(value="testsetId")int testsetId);
    public List<Execution> findExecutionsByProduct(@Param(value="productid")int productid);
    public List<Execution> findExecutionsByStartTimeAndEndTime(@Param(value="starttime")Timestamp starttime,@Param(value="endtime")Timestamp endtime);
    public List<Execution> findExecutionsByProductAndStartTimeAndEndTime(@Param(value="productid")int productid,@Param(value="starttime")Timestamp starttime,@Param(value="endtime")Timestamp endtime);
    public List<Execution> findExecutionsByProductAndStartTime(@Param(value="productid")int productid,@Param(value="starttime")Timestamp starttime);
    public List<Execution> findExecutionsByProductAndEndTime(@Param(value="productid")int productid,@Param(value="endtime")Timestamp endtime);
}
