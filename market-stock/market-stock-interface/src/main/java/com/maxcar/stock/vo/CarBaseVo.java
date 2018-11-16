package com.maxcar.stock.vo;

import java.io.Serializable;
import java.util.Date;

public class CarBaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String modelName;

    private Integer mileage;

    private Double evaluatePrice;

    private Date initialLicenceTime;

    private String color;

    private String src;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(Double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public Date getInitialLicenceTime() {
        return initialLicenceTime;
    }

    public void setInitialLicenceTime(Date initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
