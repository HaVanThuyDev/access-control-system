package com.example.employee.service;

import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;

public interface UserService {
    UserReponseDTO login(String email, String password);
    UserReponseDTO register(UserRequestDTO requestDTO);
}
