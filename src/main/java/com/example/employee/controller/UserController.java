package com.example.employee.controller;


import com.example.employee.model.SuccessResponse;
import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public SuccessResponse<UserReponseDTO> login( @RequestBody UserRequestDTO request)   {
       UserReponseDTO user = userService.login(request.getGmail(),request.getPass());
        return new SuccessResponse<>(
                "login success",
                user

        );
    }
}
