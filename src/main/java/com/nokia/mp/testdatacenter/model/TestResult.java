package com.nokia.mp.testdatacenter.model;

public class TestResult {

    private int caseId;
    private String result;
    private int execId;
    private long duration;
    private String message;
    private String detail;
    private int bugId;
    private String bugInfo;
    private String reference;
    private String oriresult;
    private int subid;
    private String scriptId;


    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getExecId() {
        return execId;
    }

    public void setExecId(int execId) {
        this.execId = execId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public String getBugInfo() {
        return bugInfo;
    }

    public void setBugInfo(String bugInfo) {
        this.bugInfo = bugInfo;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOriresult() {
        return oriresult;
    }

    public void setOriresult(String oriresult) {
        this.oriresult = oriresult;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

}
