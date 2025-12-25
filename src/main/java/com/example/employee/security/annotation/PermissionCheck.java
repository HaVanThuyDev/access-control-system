package com.example.employee.security.annotation;

import com.example.employee.model.enums.Action;
import com.example.employee.model.enums.ResourceType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionCheck {
    ResourceType resource();
    Action action();
}
