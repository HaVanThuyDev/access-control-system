package com.example.employee.service.impl;

import com.example.employee.model.dto.EmployeeDTO;
import com.example.employee.model.dto.reponse.EmployeeReponseDTO;
import com.example.employee.model.dto.request.EmployeeRequestDTO;
import com.example.employee.model.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return new EmployeeDTO(employee);
    }

    @Override
    public List<EmployeeReponseDTO> findAll() {
        return employeeService.findAll();
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeRequestDTO request) {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
            if (request.getName() != null && request.getName().isBlank()) employee.setName(request.getName());
            if (request.getDate() != null) {
                employee.setDate(request.getDate());
            }
            if (request.getAddress() != null && request.getAddress().isBlank()) employee.setAddress(request.getAddress());
            if (request.getPhone() != null && request.getPhone().isBlank()) employee.setPhone(request.getPhone());
            if (request.getGender() != null && request.getGender().isBlank()) employee.setGender(request.getGender());
            if (request.getLevel() != null && request.getLevel().isBlank()) employee.setLevel(request.getLevel());
            if (request.getExperience() != null && request.getExperience().isBlank()) employee.setExpertise(request.getExperience());
            Employee updatedEmployee = employeeRepository.save(employee);
            return new EmployeeDTO(updatedEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeDTO> deleteById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
        return new ArrayList<>();
    }

    @Override
    public List<EmployeeDTO> search(String name, String address, String phone) {
        return List.of();
    }

    @Override
    public EmployeeDTO create (EmployeeRequestDTO request) {
        try{
            Employee employee = new Employee();
            employee.setName(request.getName());
            employee.setDate(request.getDate());
            employee.setAddress(request.getAddress());
            employee.setPhone(request.getPhone());
            employee.setGender(request.getGender());
            employee.setLevel(request.getLevel());
            employee.setExpertise(request.getExperience());
            Employee savedEmployee = employeeRepository.save(employee);
            return new EmployeeDTO(savedEmployee);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
