/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.model;

/**
 * POJO
 * @author larryang
 */
public class TestPassrate {
    
    private String period;
    private int success;
    private int unstable;
    private int failed;

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getUnstable() {
        return unstable;
    }

    public void setUnstable(int unstable) {
        this.unstable = unstable;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
