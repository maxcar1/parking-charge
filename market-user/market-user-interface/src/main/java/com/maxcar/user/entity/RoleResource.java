package com.maxcar.user.entity;

import java.io.Serializable;

public class RoleResource implements Serializable {
    private String rolePermissionsId;

    private String roleId;

    private String resourceId;

    public String getRolePermissionsId() {
        return rolePermissionsId;
    }

    public void setRolePermissionsId(String rolePermissionsId) {
        this.rolePermissionsId = rolePermissionsId == null ? null : rolePermissionsId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }
}