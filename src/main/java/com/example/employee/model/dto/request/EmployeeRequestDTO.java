package com.example.employee.model.dto.request;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequestDTO {
    private String name;
    private Date birthDate;
    private String address;
    private String phone;
    private String gender;
    private String level;
    private String expertise;
}
