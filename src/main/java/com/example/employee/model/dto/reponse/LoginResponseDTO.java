package com.example.employee.model.dto.reponse;

import com.example.employee.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {
   private String name;
   private Role role;
   private String token;

}
