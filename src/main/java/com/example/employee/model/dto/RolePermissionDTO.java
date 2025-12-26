package com.example.employee.model.dto;

import com.example.employee.model.enums.Scope;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RolePermissionDTO {
    private String resource;
    private String action;
    private Scope scope;
}
