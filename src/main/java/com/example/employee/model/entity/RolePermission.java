package com.example.employee.model.entity;


import com.example.employee.model.enums.Role;
import com.example.employee.model.enums.Scope;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RolePermission {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column (name ="ROLE_ID")
    private Role role;
    @ManyToOne
    private Permission permission;
    @Enumerated(EnumType.STRING)
    @Column(name =" SCOPE")
    private Scope scope;



}
