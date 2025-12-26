package com.example.employee.repository.custome;

import com.example.employee.model.dto.RolePermissionDTO;
import com.example.employee.model.enums.Role;
import com.example.employee.repository.projection.RolePermissionProjection;
import java.util.List;

public interface RolePermissionScopeCustomRepository {
    List<RolePermissionDTO> findPermissionsByRole(Role role );
}
