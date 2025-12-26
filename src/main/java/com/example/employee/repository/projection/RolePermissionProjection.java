package com.example.employee.repository.projection;

public interface RolePermissionProjection {
    String getResource();
    String getAction();
    String getScope();
}
