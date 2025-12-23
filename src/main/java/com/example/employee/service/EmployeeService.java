package com.example.employee.service;


import com.example.employee.model.dto.EmployeeDTO;
import com.example.employee.model.dto.reponse.EmployeeReponseDTO;
import com.example.employee.model.dto.request.EmployeeRequestDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public interface EmployeeService {
    EmployeeDTO create (EmployeeRequestDTO request);
    EmployeeDTO findById(Long id);
    List<EmployeeReponseDTO> findAll();
    EmployeeDTO update(Long id, EmployeeRequestDTO request);
    List<EmployeeDTO> deleteById(Long id);
    List<EmployeeDTO> search(String name, String address, String phone);


}
