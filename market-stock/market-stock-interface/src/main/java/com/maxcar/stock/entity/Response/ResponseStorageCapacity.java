package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class ResponseStorageCapacity implements Serializable {

    // 序号
    private int no;
    // 区域
    private String areaName;
    //  车位数
    private int carPark = 0;
    //  展厅面积
    private int exhibition = 0;
    //  办公室面积
    private int office = 0;
    //  库存数
    private int repertoryCar;
    //  商户名
    private String tenantName;
    //  剩余车位数
    private int parkRemaining;
    //  车位饱和度
    private String parkPercent;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getCarPark() {
        return carPark;
    }

    public void setCarPark(int carPark) {
        this.carPark = carPark;
    }

    public int getExhibition() {
        return exhibition;
    }

    public void setExhibition(int exhibition) {
        this.exhibition = exhibition;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getRepertoryCar() {
        return repertoryCar;
    }

    public void setRepertoryCar(int repertoryCar) {
        this.repertoryCar = repertoryCar;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public int getParkRemaining() {
        return parkRemaining;
    }

    public void setParkRemaining(int parkRemaining) {
        this.parkRemaining = parkRemaining;
    }

    public String getParkPercent() {
        return parkPercent;
    }

    public void setParkPercent(String parkPercent) {
        this.parkPercent = parkPercent;
    }
}
