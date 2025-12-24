package com.example.employee.model.dto.reponse;

import com.example.employee.model.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class EmployeeReponseDTO extends ResponseBase {
    private Long mnv;
    private String name;
    private Date date;
    private String address;
    private String phone;
    private String gender;
    private String level;
    private String expertise;
    public EmployeeReponseDTO(Long mnv, String name, String date, String address, String phone, String gender, String level, String expertise) {
        this.mnv = mnv;
        this.name = name;
        this.date = new Date(Long.parseLong(date));
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.level = level;
        this.expertise = expertise;
    }
    public EmployeeReponseDTO(Employee employee) {
        this.mnv = employee.getMsv();
        this.name = employee.getName();
        this.date = employee.getDate();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.gender = employee.getGender();
        this.level = employee.getLevel();
        this.expertise = employee.getExpertise();
    }
}
