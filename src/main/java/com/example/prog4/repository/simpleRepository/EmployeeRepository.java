package com.example.prog4.repository.simpleRepository;

import com.example.prog4.repository.employeeRepository.entity.Employee;

public interface EmployeeRepository {
    Employee findById(String employeeId);
    Employee save(Employee employee);
}
