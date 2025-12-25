package com.example.employee.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="DIM _PERMISSON")

public class Permission {
    @Id
    @GeneratedValue
    @Column(name ="ID")
    private long id;
    @Column(name="RESOURCE")
    private String resource;
    @Column(name ="ACTION")
    private String action;

}
