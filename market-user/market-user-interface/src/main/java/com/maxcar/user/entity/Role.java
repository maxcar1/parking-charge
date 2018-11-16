package com.maxcar.user.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {
    private String roleId;

    @NotNull(message="角色名称不能为空")
    private String roleName;


    private String marketId;

    private String roleDesc;

    private Integer isvalid;

    private Integer version;

    private Date registerTime;
    private List<Resource> resources;//临时变量，菜单集合
    private List<ResourceOption> resourceOptions;//临时变量，操作权限集合

    public Short getManagerFlag() {
        return managerFlag;
    }

    public void setManagerFlag(Short managerFlag) {
        this.managerFlag = managerFlag;
    }

    private Short managerFlag;//临时，用户是否为管理员属性

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<ResourceOption> getResourceOptions() {
        return resourceOptions;
    }

    public void setResourceOptions(List<ResourceOption> resourceOptions) {
        this.resourceOptions = resourceOptions;
    }


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
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
    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}