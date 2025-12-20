package com.example.employee.model.dto.reponse;

import com.example.employee.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReponseDTO extends  ResponseBase {
    private  Long id;
    private  String name;
    private  String password;
    public UserReponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();

    }

}
