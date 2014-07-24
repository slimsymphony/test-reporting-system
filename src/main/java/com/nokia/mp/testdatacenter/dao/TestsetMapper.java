/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.Testset;

/**
 * 
 * @author Duan Karl
 */
@Repository("testsetMapper")
public interface TestsetMapper {

    public void insertTestset(Testset testset);
    public Testset getTestset(String name);
    public Testset findTestsetById(int testsetId);
    public List<Testset> findAllTestsets();
}
