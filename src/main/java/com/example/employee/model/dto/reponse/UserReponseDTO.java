package com.example.employee.model.dto.reponse;

import com.example.employee.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserReponseDTO  {
    private  String name;
    private Role role;
    private String token;
    public UserReponseDTO(String name,Role role, String token) {
        this.name = name;
        this.role = role;
        this.token =token;

    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
