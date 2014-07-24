package com.nokia.mp.testdatacenter.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.nokia.mp.testdatacenter.model.Testset;

public interface TestsetService {
    public Testset findTestsetById(int testsetId);
    public List<Testset> findAllTestsets();
    public Map<String, String> updateTestset(String testsetURL)throws IOException, DocumentException;
}
