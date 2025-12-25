package com.example.employee.repository.custome;

import com.example.employee.repository.projection.RolePermissionProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolePermissionScopeCustomRepositoryImpl
        implements RolePermissionScopeCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RolePermissionProjection> findPermissionsByRole(String roleId) {

        String jpql = """
            SELECT 
                p.resource AS resource,
                p.action   AS action,
                rps.scope  AS scope
            FROM RolePermission rps
            JOIN rps.permission p
            WHERE rps.role = :roleId
        """;

        return entityManager
                .createQuery(jpql, RolePermissionProjection.class)
                .setParameter("roleId", roleId)
                .getResultList();
    }
}
