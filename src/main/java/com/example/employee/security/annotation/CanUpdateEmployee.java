package com.example.employee.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasPermission(#id, 'Employee', 'EMPLOYEE_UPDATE')")
public @interface CanUpdateEmployee {
}
