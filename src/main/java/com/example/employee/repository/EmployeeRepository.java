package com.example.employee.repository;

import com.example.employee.model.dto.reponse.EmployeeReponseDTO;
import com.example.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> searchBy(String name,String address,String phone);

}
