/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.service;

import java.text.ParseException;
import java.util.List;

/**
 *
 * @author alex.2.wu
 */
public interface StabilityService {
    public List<List<String>> findExecutionsByConditions(String testset,String product,String trigger,String startDate,String endDate) throws ParseException;
}
