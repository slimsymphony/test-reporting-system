package com.nokia.mp.testdatacenter.model;

import java.sql.Timestamp;
import java.util.List;


public class Execution {
    private int id;
    private String name;
    private Timestamp starttime;
    private Timestamp endtime;
    private int passCnt;
    private int failCnt;
    private String url;
    private int testsetId;
    private String branch;
    private String manifest;
    private String repo;
    private int productid;
    private String platform;
    private String variant;
    private String relpath;
    private String testfilter;
    private long duration;
    private String verifytype;
    private int noresultCnt;
    private String sw;
    private String orisw;
    private String report;
    private String status;
    private String failReason;
    private String trigger;
    private String userComment;
    
    private String testsetName;
    private List<TestResult> testResults;
    


    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public int getPassCnt() {
        return passCnt;
    }

    public void setPassCnt(int passCnt) {
        this.passCnt = passCnt;
    }

    public int getFailCnt() {
        return failCnt;
    }

    public void setFailCnt(int failCnt) {
        this.failCnt = failCnt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTestsetId() {
        return testsetId;
    }

    public void setTestsetId(int testsetId) {
        this.testsetId = testsetId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getManifest() {
        return manifest;
    }

    public void setManifest(String manifest) {
        this.manifest = manifest;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getRelpath() {
        return relpath;
    }

    public void setRelpath(String relpath) {
        this.relpath = relpath;
    }

    public String getTestfilter() {
        return testfilter;
    }

    public void setTestfilter(String testfilter) {
        this.testfilter = testfilter;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getVerifytype() {
        return verifytype;
    }

    public void setVerifytype(String verifytype) {
        this.verifytype = verifytype;
    }

    public int getNoresultCnt() {
        return noresultCnt;
    }

    public void setNoresultCnt(int noresultCnt) {
        this.noresultCnt = noresultCnt;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getOrisw() {
        return orisw;
    }

    public void setOrisw(String orisw) {
        this.orisw = orisw;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    
    public String getTestsetName() {
        return testsetName;
    }

    public void setTestsetName(String testsetName) {
        this.testsetName = testsetName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
    

}
