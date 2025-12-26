package com.example.employee.repository.custome;

import com.example.employee.model.dto.RolePermissionDTO;
import com.example.employee.model.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class RolePermissionScopeCustomRepositoryImpl implements RolePermissionScopeCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RolePermissionDTO> findPermissionsByRole(Role role) {

        String jpql = """
        SELECT new com.example.employee.model.dto.RolePermissionDTO(
            p.resource,
            p.action,
            rps.scope
                      )
        FROM RolePermission rps
        JOIN rps.permission p
        WHERE rps.role = :role
    """;

        return entityManager
                .createQuery(jpql, RolePermissionDTO.class)
                .setParameter("role", role)
                .getResultList();
    }



}
