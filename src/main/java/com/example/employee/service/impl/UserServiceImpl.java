package com.example.employee.service.impl;


import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;
import com.example.employee.repository.UserRepository;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public  UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO login (String email, String password) {
        UserDTO user = userRepository.findByUsername(userRepository.findByUsername(email)).orElseThrow();
        if ( !user.getPassword().equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

    }



}
