package com.nokia.mp.testdatacenter.utils;

public class Constants {

    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    // NJUNIT XML
    public static final String TESTSUITE_NODE = "testsuite";
    public static final String TESTCASE_NODE = "testcase";
    public static final String PROPERTIES_NODE = "properties";
    public static final String TESTSUITE_ATTR_NAME = "name";
    public static final String ARTIFACT_PATH = "artifact/";
    public static final String TESTCASE_NODE_ATTR_RELATIVEPATH = "relativepath";
    public static final String HTTP_PROTOCOL = "http://";
    public static final String URL_START = "/nokia/be_nmp";
    public static final String URL_START_REPLACEMENT = "//besmb01";
    public static final String XML_ENCODING_LINE = "﻿<?xml version=";

    public static enum TESTCASE_RESULT {
        SUCCESS, FAILURE, NORESULT
    }

}
