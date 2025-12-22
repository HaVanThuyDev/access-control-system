package com.example.employee.service;

import com.example.employee.model.dto.UserDTO;

public interface UserService {
    UserDTO login(String email, String password);
}
