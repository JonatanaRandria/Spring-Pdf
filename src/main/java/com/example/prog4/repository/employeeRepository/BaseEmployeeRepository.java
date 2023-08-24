package com.example.prog4.repository.employeeRepository;

import com.example.prog4.repository.cnapsRepository.entity.CnapsEmployee;
import com.example.prog4.repository.employeeRepository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseEmployeeRepository extends JpaRepository<Employee, String> {

    Optional<CnapsEmployee> findByEndToEndId(String endToEndId);
}
