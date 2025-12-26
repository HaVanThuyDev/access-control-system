package com.example.employee.controller;
import com.example.employee.model.SuccessResponse;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public SuccessResponse<List<UserReponseDTO>> getAll(){
        List<UserReponseDTO> user = userService.getAll();

        return  new SuccessResponse<>(
                "suceeee",
                user
        );
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok("delete user success");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("error"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("delete user error");
        }
    }
    @PostMapping("/search/")
    public List<UserReponseDTO> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        return userService.search(name, email);
    }
}
