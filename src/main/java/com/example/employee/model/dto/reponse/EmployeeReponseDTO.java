package com.example.employee.model.dto.reponse;

import java.util.Date;

public class EmployeeReponseDTO extends ResponseBase {
    private String name;
    private Date date;
    private String phone;
    private String gender;
    public EmployeeReponseDTO(String name, String phone, String gender, Date date) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }
}
