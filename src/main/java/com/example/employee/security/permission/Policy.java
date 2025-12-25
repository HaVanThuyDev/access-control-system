package com.example.employee.security.permission;


import com.example.employee.model.enums.Action;
import com.example.employee.model.enums.ResourceType;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.UserRepository;
import com.example.employee.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Policy {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    public boolean allow(ResourceType resource, Action action, Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserPrincipal user)) {
            return false;
        }
        if (hasAuthority(auth, resource + "." + action + ":ALL")) {
            return true;
        }
        if (hasAuthority(auth, resource + "." + action + ":OWN")) {
            if (id == null) {
                return false; // SEARCH / CREATE không có OWN
            }
            return isOwner(resource, id, user.getUserId());
        }
        return false;
    }

    private boolean isOwner(ResourceType resource, Long id, Long userId) {
        return switch (resource) {

            case EMPLOYEE -> employeeRepository.findById(id)
                    .map(e -> e.getMnv().equals(userId))
                    .orElse(false);

            case USER -> id.equals(userId);
            default -> false;

            // DEPARTMENT, ORDER, PRODUCT... thêm tương tự
        };
    }

    private boolean hasAuthority(Authentication auth, String authority) {
        return auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }
}

