package com.example.prog4.repository.cnapsRepository;

import com.example.prog4.repository.cnapsRepository.entity.CnapsEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnapsEmployeeRepository extends JpaRepository<CnapsEmployee, String> {
    Optional<CnapsEmployee> findByEndToEndId(String endToEndId);
}
