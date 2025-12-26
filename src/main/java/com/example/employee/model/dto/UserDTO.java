package com.example.employee.model.dto;

import com.example.employee.model.dto.reponse.ResponseBase;
import com.example.employee.model.entity.User;
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
    private String phone;
    private  String password;
    private Role role;

    public UserDTO(User user) {
      this.id = user.getId();
      this.name = user.getName();
      this.phone = user.getPhone();
      this.gmail = user.getGmail();
      this.role = user.getRole();

    }
}
