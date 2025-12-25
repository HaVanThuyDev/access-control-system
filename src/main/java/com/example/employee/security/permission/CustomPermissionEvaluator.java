package com.example.employee.security.permission;

import com.example.employee.model.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final EmployeeRepository employeeRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (!"Employee".equalsIgnoreCase(targetType)) {
            return false;
        }

        Employee employee = employeeRepository.findById((Long) targetId).orElseThrow();
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String perm = permission.toString();
        if ("EMPLOYEE_READ".equals(perm)) {

            if (hasAuthority(authentication, "EMPLOYEE.READ:ALL")) {
                return true;
            }
            return hasAuthority(authentication, "EMPLOYEE.READ:OWN")
                    && employee.getMnv().equals(user.getUserId());
        }

        if ("EMPLOYEE_UPDATE".equals(perm)) {
            if (hasAuthority(authentication, "EMPLOYEE.UPDATE:ALL")) {
                return true;
            }

            return hasAuthority(authentication, "EMPLOYEE.UPDATE:OWN")
                    && employee.getMnv().equals(user.getUserId());
        }

        return false;
    }

    @Override
    public boolean hasPermission(
            Authentication authentication,
            Object targetDomainObject,
            Object permission
    ) {
        return false;
    }

    private boolean hasAuthority(Authentication authentication, String authority) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }
}
