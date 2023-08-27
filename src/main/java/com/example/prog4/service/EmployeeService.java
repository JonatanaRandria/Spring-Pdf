package com.example.prog4.service;

import com.example.prog4.model.EmployeeFilter;

import com.example.prog4.repository.baseRepository.dao.EmployeeManagerDao;
import com.example.prog4.repository.baseRepository.entity.Employee;
import com.example.prog4.repository.domainRepository.EmployeeRepository;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeManagerDao employeeManagerDao;


    public Employee getOne(String id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAll(EmployeeFilter filter) {
        Sort sort = Sort.by(filter.getOrderDirection(), filter.getOrderBy().toString());
        Pageable pageable = PageRequest.of(filter.getIntPage() - 1, filter.getIntPerPage(), sort);
        return employeeManagerDao.findByCriteria(
                filter.getLastName(),
                filter.getFirstName(),
                filter.getCountryCode(),
                filter.getSex(),
                filter.getPosition(),
                filter.getEntrance(),
                filter.getDeparture(),
                pageable
        );
    }

    public void saveOne(Employee employee) {
        employeeRepository.save(employee);
    }
}
