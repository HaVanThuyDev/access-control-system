package com.example.employee.model.entity;


import com.example.employee.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name = "USERS")

public class User extends  BaseStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private  Long id;
    @Column( name ="NAME", nullable = false )
    private  String name;
    @Column ( name= "GMAIL", nullable = false)
    private  String gmail;
    @Column(name="PHONE" ,nullable = false)
    private  String phone;
    @Column ( name = "PASSWORD")
    private  String password;
    @Column  (name ="ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;




}
