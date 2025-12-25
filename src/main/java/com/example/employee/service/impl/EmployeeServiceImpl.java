package com.example.employee.service.impl;

import com.example.employee.model.dto.EmployeeDTO;
import com.example.employee.model.dto.reponse.EmployeeReponseDTO;
import com.example.employee.model.dto.request.EmployeeRequestDTO;
import com.example.employee.model.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.security.annotation.*;
import com.example.employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static com.example.employee.model.enums.Action.*;
import static com.example.employee.model.enums.ResourceType.EMPLOYEE;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @PermissionCheck(resource = EMPLOYEE, action = SEARCH)
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return new EmployeeDTO(employee);
    }

    @Override
    @PermissionCheck(resource = EMPLOYEE, action = SEARCH)
    public List<EmployeeReponseDTO> findAll() {
      long total = employeeRepository.count();
        return employeeRepository.findAll().stream().map(employee -> {
            EmployeeReponseDTO list = new EmployeeReponseDTO(employee);
            list.setTotal(total);
            return list;
        }).toList();
    }

    @Override
    @PermissionCheck(resource = EMPLOYEE, action = UPDATE)
    public EmployeeDTO update(Long id, EmployeeRequestDTO request) {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
            if (request.getName() != null && request.getName().isBlank()) employee.setName(request.getName());
            if (request.getBirthDate() != null) employee.setBirthDate(request.getBirthDate());
            if (request.getAddress() != null && request.getAddress().isBlank()) employee.setAddress(request.getAddress());
            if (request.getPhone() != null && request.getPhone().isBlank()) employee.setPhone(request.getPhone());
            if (request.getGender() != null && request.getGender().isBlank()) employee.setGender(request.getGender());
            if (request.getLevel() != null && request.getLevel().isBlank()) employee.setLevel(request.getLevel());
            if (request.getExpertise() != null && request.getExpertise().isBlank()) employee.setExpertise(request.getExpertise());
            Employee updatedEmployee = employeeRepository.save(employee);
            return new EmployeeDTO(updatedEmployee);
    }

    @Override
    @PermissionCheck(resource = EMPLOYEE, action = DELETE)
    public List<EmployeeDTO> deleteById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
        return new ArrayList<>();
    }
    @Override
    @PermissionCheck(resource = EMPLOYEE, action = SEARCH)
    public List<EmployeeReponseDTO> search(String name, String address, String phone) {
        List<Employee> employees = employeeRepository.searchBy(name, address, phone);
        return employees.stream().map(EmployeeReponseDTO::new).toList();
    }

    @Override
    @PermissionCheck(resource = EMPLOYEE, action = READ)
    public EmployeeReponseDTO getDetails(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        EmployeeReponseDTO dto = new EmployeeReponseDTO(employee);
        return dto;
    }

    @Override
    @PermissionCheck(resource = EMPLOYEE, action = CREATE)
    public EmployeeDTO create (EmployeeRequestDTO request) {
            Employee employee = new Employee();
        if (request.getName() != null && !request.getName().isBlank()) employee.setName(request.getName());
             employee.setBirthDate(request.getBirthDate());
        if (request.getAddress() != null && !request.getAddress().isBlank())
            employee.setAddress(request.getAddress());
        if (request.getPhone() != null && !request.getPhone().isBlank())
            employee.setPhone(request.getPhone());
        if (request.getGender() != null && !request.getGender().isBlank())
            employee.setGender(request.getGender());
        if (request.getLevel() != null && !request.getLevel().isBlank())
            employee.setLevel(request.getLevel());
        if (request.getExpertise() != null && !request.getExpertise().isBlank())
            employee.setExpertise(request.getExpertise());
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(savedEmployee);
    }
}
