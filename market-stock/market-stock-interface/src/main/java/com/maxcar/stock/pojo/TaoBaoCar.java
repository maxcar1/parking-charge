package com.maxcar.stock.pojo;

import java.io.Serializable;

public class TaoBaoCar implements Serializable {
    private Integer id;

    private String brandName;

    private String brandPid;

    private String brandVid;

    private String seriesName;

    private String seriesPid;

    private String seriesVid;

    private String yearName;

    private String yearPid;

    private String yearVid;

    private String modelName;

    private String modelPid;

    private String modelVid;

    private String aliValue;

    private String aliCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandPid() {
        return brandPid;
    }

    public void setBrandPid(String brandPid) {
        this.brandPid = brandPid == null ? null : brandPid.trim();
    }

    public String getBrandVid() {
        return brandVid;
    }

    public void setBrandVid(String brandVid) {
        this.brandVid = brandVid == null ? null : brandVid.trim();
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    public String getSeriesPid() {
        return seriesPid;
    }

    public void setSeriesPid(String seriesPid) {
        this.seriesPid = seriesPid == null ? null : seriesPid.trim();
    }

    public String getSeriesVid() {
        return seriesVid;
    }

    public void setSeriesVid(String seriesVid) {
        this.seriesVid = seriesVid == null ? null : seriesVid.trim();
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName == null ? null : yearName.trim();
    }

    public String getYearPid() {
        return yearPid;
    }

    public void setYearPid(String yearPid) {
        this.yearPid = yearPid == null ? null : yearPid.trim();
    }

    public String getYearVid() {
        return yearVid;
    }

    public void setYearVid(String yearVid) {
        this.yearVid = yearVid == null ? null : yearVid.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelPid() {
        return modelPid;
    }

    public void setModelPid(String modelPid) {
        this.modelPid = modelPid == null ? null : modelPid.trim();
    }

    public String getModelVid() {
        return modelVid;
    }

    public void setModelVid(String modelVid) {
        this.modelVid = modelVid == null ? null : modelVid.trim();
    }

    public String getAliValue() {
        return aliValue;
    }

    public void setAliValue(String aliValue) {
        this.aliValue = aliValue == null ? null : aliValue.trim();
    }

    public String getAliCode() {
        return aliCode;
    }

    public void setAliCode(String aliCode) {
        this.aliCode = aliCode == null ? null : aliCode.trim();
    }
}
