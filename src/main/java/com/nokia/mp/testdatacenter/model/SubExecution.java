package com.nokia.mp.testdatacenter.model;

import java.sql.Date;

public class SubExecution {
    private int execid;
    private int subid;
    private String report;
    private Date exectime;
    private String url;

    public int getExecid() {
        return execid;
    }

    public void setExecid(int execid) {
        this.execid = execid;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Date getExectime() {
        return exectime;
    }

    public void setExectime(Date exectime) {
        this.exectime = exectime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
