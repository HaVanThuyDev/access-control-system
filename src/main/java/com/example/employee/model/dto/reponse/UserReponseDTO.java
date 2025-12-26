package com.example.employee.model.dto.reponse;

import com.example.employee.model.entity.User;
import com.example.employee.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserReponseDTO extends ResponseBase  {
    private Long id;
    private  String name;
    private  String email;
    private  String phone;
    private Role role;
    public UserReponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email =user.getGmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }
}
