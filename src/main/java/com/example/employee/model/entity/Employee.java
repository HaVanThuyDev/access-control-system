package com.example.employee.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="EMPLOYEE")
public class Employee extends BaseModifiedEntity{
    @Id
    @Column(name="MNV", nullable=false)
    private Long mnv;
    @Column(name ="NAME")
    private String name;
    @Column (name ="BIRTH_DATE")
    private Date birthDate;
    @Column (name ="ADDRESS")
    private String address;
    @Column ( name = "PHONE")
    private String phone;
    @Column (name ="GENDER")
    private String gender;
    @Column(name = "LEVEL")
    private String level;
    @Column(name ="EXPERTISE")
    private String expertise;

}
