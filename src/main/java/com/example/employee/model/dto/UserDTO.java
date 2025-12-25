package com.example.employee.model.dto;

import com.example.employee.model.dto.reponse.ResponseBase;
import com.example.employee.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends ResponseBase {
    private Long id;
    private  String name;
    private String gmail;
    private  String password;
    private Role role;
}
