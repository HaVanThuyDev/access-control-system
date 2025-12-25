package com.example.employee.repository;

import com.example.employee.model.entity.RolePermission;
import com.example.employee.repository.custome.RolePermissionScopeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionScopeRepository extends JpaRepository<RolePermission, Long>, RolePermissionScopeCustomRepository {
}
