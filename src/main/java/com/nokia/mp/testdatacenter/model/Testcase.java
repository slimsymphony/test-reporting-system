package com.nokia.mp.testdatacenter.model;

public class Testcase {
    private int id;
    private String caseName;
    private String product;
    private String featureGroup;
    private String feature;
    private String path;
    private int qcid;
    private int qcIdentifier;
    private String file;
    private String directory;
    private String pkg;
    private String clazz;
    private String method;
    private String caseId;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFeatureGroup() {
        return featureGroup;
    }

    public void setFeatureGroup(String featureGroup) {
        this.featureGroup = featureGroup;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getQcid() {
        return qcid;
    }

    public void setQcid(int qcid) {
        this.qcid = qcid;
    }

    public int getQcIdentifier() {
        return qcIdentifier;
    }

    public void setQcIdentifier(int qcIdentifier) {
        this.qcIdentifier = qcIdentifier;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
