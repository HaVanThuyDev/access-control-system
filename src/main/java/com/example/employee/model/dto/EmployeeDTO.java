package com.example.employee.model.dto;


import com.example.employee.model.entity.BaseModifiedEntity;
import com.example.employee.model.entity.Employee;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends BaseModifiedEntity  {
    private long mnv;
    private String name;
    private Date birthDate;
    private String address;
    private String phone;
    private String gender;
    private String level;
    private String experience;
    public  EmployeeDTO(Employee employee){
        this.name =employee.getName();
        this.birthDate =employee.getBirthDate();
        this.address =employee.getAddress();
        this.phone =employee.getPhone();
        this.gender =employee.getGender();
        this.level =employee.getLevel();
        this.experience = employee.getExpertise();

    }
}
