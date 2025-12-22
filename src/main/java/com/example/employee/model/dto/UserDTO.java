package com.example.employee.model.dto;

import com.example.employee.model.dto.reponse.ResponseBase;
import com.example.employee.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends ResponseBase {
    private  String username;
    private String gmail;
    private  String password;
    public UserDTO(User user) {
        this.username = user.getName();
        this.gmail = user.getGmail();
        this.password = user.getPassword();
    }
}
