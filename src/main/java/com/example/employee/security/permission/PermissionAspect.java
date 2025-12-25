package com.example.employee.security.permission;

import com.example.employee.model.enums.Action;
import com.example.employee.model.enums.ResourceType;
import com.example.employee.security.annotation.PermissionCheck;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {

    private final Policy policy;

    @Before("@annotation(permissionCheck)")
    public void checkPermission(
            JoinPoint joinPoint,
            PermissionCheck permissionCheck
    ) {
        ResourceType resource = permissionCheck.resource();
        Action action = permissionCheck.action();
        Long id = extractId(joinPoint.getArgs());
        if (!policy.allow(resource, action, id)) {
            throw new AccessDeniedException("Access Denied");
        }
    }

    private Long extractId(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        return null;
    }
}
