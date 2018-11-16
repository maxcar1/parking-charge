package com.maxcar.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Resource implements Serializable {
    private String resourceId;

    private String parentId;

    private String resourceName;

    private String resourceUrl;

    private String resourceDesc;

    private Integer resourceLevel;

    private Integer ind;

    private Integer isvalid;

    private Integer version;

    private Date registerTime;
    private Boolean checked;//临时变量，用来判定用户是否已拥有该菜单
    private List<Resource> childList;//临时变量，用来存放子集合
    private List<ResourceOption> resourceOptions;//临时变量，用来存放，角色资源操作权限
    public List<ResourceOption> getResourceOptions() {
        return resourceOptions;
    }

    public void setResourceOptions(List<ResourceOption> resourceOptions) {
        this.resourceOptions = resourceOptions;
    }


    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    private Integer resourceType;



    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<Resource> getChildList() {
        return childList;
    }

    public void setChildList(List<Resource> childList) {
        this.childList = childList;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc == null ? null : resourceDesc.trim();
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public Integer getInd() {
        return ind;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}