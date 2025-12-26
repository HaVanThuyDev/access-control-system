package com.example.employee.controller;

import com.example.employee.model.SuccessResponse;
import com.example.employee.model.dto.EmployeeDTO;
import com.example.employee.model.dto.reponse.EmployeeReponseDTO;
import com.example.employee.model.dto.reponse.ResponseDTO;
import com.example.employee.model.dto.request.EmployeeRequestDTO;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/employee")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public SuccessResponse<List<EmployeeReponseDTO>> findAll() {
        return new SuccessResponse<>(
                "succeess",
                employeeService.findAll()
        );
    }
    @PostMapping("/add")
    public ResponseEntity <?> create (@RequestBody EmployeeRequestDTO request) {
        try {
            employeeService.create(request);
            return ResponseEntity.ok("add Employee success");
        }catch (IllegalAccessError ex) {
            return ResponseEntity.badRequest().body("error"+ex.getMessage());

        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body(" system error has occurred!");

        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity <?> update (@PathVariable Long id, @RequestBody EmployeeRequestDTO request) {
        try {
            employeeService.update(id, request);
            return ResponseEntity.ok("update Employee success");
        }catch (IllegalAccessError ex) {
            return ResponseEntity.badRequest().body("error"+ex.getMessage());
        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Employee update failed.!");
        }

    }
    @PostMapping("/delete/{id}")
    public ResponseEntity <?> delete (@PathVariable Long id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok("delete Employee success");
        }catch (IllegalAccessError ex) {
            return ResponseEntity.badRequest().body("error"+ex.getMessage());
        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Employee delete failed.!");
        }
    }
    @GetMapping("/search/")
    public List<EmployeeReponseDTO> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone
    ) {
        return employeeService.search(name, address, phone);
    }

    @PostMapping("/details/{id}")
    public ResponseEntity<ResponseDTO<EmployeeDTO>> getDetails (@PathVariable Long id) {
        EmployeeReponseDTO dto = employeeService.getDetails(id);
        return ResponseEntity.ok(
                ResponseDTO.build(200, "Success", 1L, dto)
        );

    }
}
