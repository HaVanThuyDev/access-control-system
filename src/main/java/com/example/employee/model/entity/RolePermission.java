package com.example.employee.model.entity;


import com.example.employee.model.enums.Role;
import com.example.employee.model.enums.Scope;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name ="role_permission_scope")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column (name ="ROLE_ID")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name =" SCOPE")
    private Scope scope;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="permission_id")
    private Permission permission;



}
