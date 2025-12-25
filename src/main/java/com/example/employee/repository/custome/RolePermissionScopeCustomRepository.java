package com.example.employee.repository.custome;

import com.example.employee.repository.projection.RolePermissionProjection;

import java.util.List;

public interface RolePermissionScopeCustomRepository {
    List<RolePermissionProjection> findPermissionsByRole(String roleId);
}
