package com.example.prog4.repository.cnapsRepository;

import com.example.prog4.repository.cnapsRepository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "cnapsEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
