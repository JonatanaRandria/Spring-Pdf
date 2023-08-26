package com.example.prog4.repository.baseRepository;

import com.example.prog4.repository.cnapsRepository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "baseEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<com.example.prog4.repository.baseRepository.entity.Employee, String> {

    Optional<Employee> findByEndToEndId(String endToEndId);
}
