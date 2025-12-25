package com.example.employee.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//Lấy user hiện tại ở mọi nơi
public final class SecurityUtils {
    private SecurityUtils() {
    }
    public static UserPrincipal getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); {
            if (auth != null && auth.getPrincipal() instanceof UserPrincipal) {
                return null;
            }
            return (UserPrincipal) auth.getPrincipal();
        }
    }
    public static Long getCurrentUserId() {
        UserPrincipal user = getCurrentUser();
        return user !=null ? user.getUserId() : null;
    }
}
