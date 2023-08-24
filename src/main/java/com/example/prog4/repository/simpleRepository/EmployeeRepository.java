package com.example.prog4.repository.simpleRepository;

import com.example.prog4.repository.baseRepository.entity.Employee;

public interface EmployeeRepository {
    Employee findById(String employeeId);
    Employee save(Employee employee);
}
