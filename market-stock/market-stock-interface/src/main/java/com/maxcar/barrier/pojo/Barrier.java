package com.maxcar.barrier.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Barrier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String barrierId;//道闸ID

    private String gatewayIp;//网关IP

    private String subnetMask;//子网掩码

    private String macAddress;//物理地址

    private String clientIp;//客户端ip

    private String serverIp;//服务器ip

    private String clientPort;//客户端端口

    private String serverPort;//服务器端口

    private String barrierType;//道闸类型（0：内场，1：外场）

    private String barrierPosition;//道闸位置

    private String inOutCar;//11:已入场,12:已出场,13:已售出,14:售出已出场,15:已下架,16:市场员工车,17:商户员工车,18:无

    private List inOutCarList;

    private Integer inOutType;//0:入口,1:出口

    private String parkLimit;//车位限制(0:不限制，1:限制)

    private Integer isvalid;//是否有效

    private String status;//状态标识允许出入车辆(0:不控制,1:控制,

    private String remark;

    private Date updateTime;//修改时间

    private Date createTime;//创建时间



    private String staticSpeech;//静态欢迎词默认必填

    private String marketId;//市场ID

    private String mqttTopic;

    private String remoteHost;

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public Short getManagerFlag() {
        return managerFlag;
    }

    public void setManagerFlag(Short managerFlag) {
        this.managerFlag = managerFlag;
    }

    private Short managerFlag;//临时，用户是否为管理员属性

    public String getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    public List getInOutCarList() {
        return inOutCarList;
    }

    public void setInOutCarList(List inOutCarList) {
        this.inOutCarList = inOutCarList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarrierId() {
        return barrierId;
    }

    public void setBarrierId(String barrierId) {
        this.barrierId = barrierId == null ? null : barrierId.trim();
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp == null ? null : gatewayIp.trim();
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask == null ? null : subnetMask.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort == null ? null : clientPort.trim();
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort == null ? null : serverPort.trim();
    }

    public String getBarrierType() {
        return barrierType;
    }

    public void setBarrierType(String barrierType) {
        this.barrierType = barrierType == null ? null : barrierType.trim();
    }

    public String getBarrierPosition() {
        return barrierPosition;
    }

    public void setBarrierPosition(String barrierPosition) {
        this.barrierPosition = barrierPosition == null ? null : barrierPosition.trim();
    }

    public String getInOutCar() {
        return inOutCar;
    }

    public void setInOutCar(String inOutCar) {
        this.inOutCar = inOutCar == null ? null : inOutCar.trim();
    }

    public Integer getInOutType() {
        return inOutType;
    }

    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }

    public String getParkLimit() {
        return parkLimit;
    }

    public void setParkLimit(String parkLimit) {
        this.parkLimit = parkLimit == null ? null : parkLimit.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStaticSpeech() {
        return staticSpeech;
    }

    public void setStaticSpeech(String staticSpeech) {
        this.staticSpeech = staticSpeech == null ? null : staticSpeech.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}