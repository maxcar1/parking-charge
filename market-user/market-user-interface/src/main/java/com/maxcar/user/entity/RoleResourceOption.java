package com.maxcar.user.entity;

import java.io.Serializable;

public class RoleResourceOption implements Serializable{
    private String roleResourceOptionId;

    private String roleId;

    private String resourceOptionId;

    public String getRoleResourceOptionId() {
        return roleResourceOptionId;
    }

    public void setRoleResourceOptionId(String roleResourceOptionId) {
        this.roleResourceOptionId = roleResourceOptionId == null ? null : roleResourceOptionId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceOptionId() {
        return resourceOptionId;
    }

    public void setResourceOptionId(String resourceOptionId) {
        this.resourceOptionId = resourceOptionId == null ? null : resourceOptionId.trim();
    }
}