//package com.example.employee.security;
//
//import com.example.employee.model.enums.Role;
//
//import java.util.List;
//
//public final class RolePermissionMapper {
//
//    private RolePermissionMapper() {
//    }
//
//    public static List<String> getPermissions(Role role) {
//
//        return switch (role) {
//            case ADMIN -> List.of(
//                    "USER_VIEW_SELF",
//                    "USER_UPDATE_SELF",
//                    "USER_DELETE",
//                    "EMPLOYEE_DELETE"
//            );
//
//            case MANAGER -> List.of(
//                    "USER_DELETE"
//            );
//
//            case EMPLOYEE -> List.of(
//                    "USER_VIEW_SELF",
//                    "USER_UPDATE_SELF"
//            );
//        };
//    }
//}
