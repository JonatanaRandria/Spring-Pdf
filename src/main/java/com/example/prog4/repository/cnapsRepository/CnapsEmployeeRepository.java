package com.example.prog4.repository.cnapsRepository;

import com.example.prog4.repository.cnapsRepository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CnapsEmployeeRepository extends JpaRepository<Employee, String> {
}
