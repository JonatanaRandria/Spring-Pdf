package com.example.prog4.repository.domainRepository;

import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.baseRepository.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements com.example.prog4.repository.domainRepository.EmployeeRepository {
    private final com.example.prog4.repository.baseRepository.EmployeeRepository baseEmployeeRepository;
    private final com.example.prog4.repository.cnapsRepository.EmployeeRepository cnapsEmployeeRepository;


    public EmployeeRepositoryImpl(
            @Qualifier("baseEmployeeRepository") com.example.prog4.repository.baseRepository.EmployeeRepository baseEmployeeRepository,
            @Qualifier("cnapsEmployeeRepository") com.example.prog4.repository.cnapsRepository.EmployeeRepository cnapsEmployeeRepository
    ) {
        this.baseEmployeeRepository = baseEmployeeRepository;
        this.cnapsEmployeeRepository = cnapsEmployeeRepository;
    }


    @Override
    public Employee findById(String employeeId) {
        Employee employee = baseEmployeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee with id = " + employeeId + " not found"));
        if (employee.getEndToEndId() != null) {
            Optional<com.example.prog4.repository.cnapsRepository.entity.Employee> cnapsEmployee = cnapsEmployeeRepository.findById(employee.getEndToEndId());
            cnapsEmployee.ifPresent(value -> employee.setCnaps(value.getCnaps()));
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        employee.setCnaps(null);
        return baseEmployeeRepository.save(employee);
    }
}
