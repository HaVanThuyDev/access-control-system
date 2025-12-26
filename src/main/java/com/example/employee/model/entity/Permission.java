package com.example.employee.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="dim_premission")

public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="RESOURCE")
    private String resource;
    @Column(name ="ACTION")
    private String action;

}
