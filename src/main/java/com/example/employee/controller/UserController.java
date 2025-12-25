package com.example.employee.controller;


import com.example.employee.model.SuccessResponse;
import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
       UserReponseDTO user = userService.login(request.getGmail(),request.getPassword());
        return new SuccessResponse<>(
                "login success",
                user
        );
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO request)   {
        try {
            User user = userService.register(request);
            return  ResponseEntity.ok(" register success");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("error"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("register not success");
        }
    }
}
