package com.example.prog4.repository.simpleRepository;

import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.cnapsRepository.CnapsEmployeeRepository;
import com.example.prog4.repository.cnapsRepository.entity.CnapsEmployee;
import com.example.prog4.repository.employeeRepository.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private com.example.prog4.repository.employeeRepository.EmployeeRepository employeeRepository;
    private CnapsEmployeeRepository cnapsEmployeeRepository;

    @Override
    public Employee findById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee with id = " + employeeId + " not found"));
        if(employee.getEndToEndId() != null){
            Optional<CnapsEmployee> cnapsEmployee = cnapsEmployeeRepository.findById(employee.getEndToEndId());
            cnapsEmployee.ifPresent(value -> employee.setCnaps(value.getCnaps()));
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        employee.setCnaps(null);
        return employeeRepository.save(employee);
    }
}
