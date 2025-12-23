package com.example.employee.model.entity;


import com.example.employee.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseStatusEntity {
    @Column(name ="STATUS")
    private int status;
    private Status status1;

}
