package com.example.employee.service;

import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.LoginResponseDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;

import java.util.List;

public interface UserService {
    LoginResponseDTO login(String email, String password);
    User register(UserRequestDTO request);
    List<UserReponseDTO> getAll();
    List<UserDTO> delete(Long id);
    List <UserReponseDTO> search(String name, String email);

}
