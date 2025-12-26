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
    public EmployeeReponseDTO(Employee employee) {
        this.mnv = employee.getMnv();
        this.name = employee.getName();
        this.date = employee.getBirthDate();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.gender = employee.getGender();
        this.level = employee.getLevel();
        this.expertise = employee.getExpertise();
    }
}
