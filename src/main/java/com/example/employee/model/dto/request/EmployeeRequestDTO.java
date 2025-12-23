package com.example.employee.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
    private String name;
    private Date date;
    private String address;
    private String phone;
    private String gender;
    private String level;
    private String experience;
    public EmployeeRequestDTO(String name, Date date, String address, String phone, String gender, String level, String experience) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.level = level;
        this.experience = experience;

    }
}
