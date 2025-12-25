package com.example.employee.service;

import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;

public interface UserService {
    UserReponseDTO login(String email, String password);
    User register(UserRequestDTO request);
}
