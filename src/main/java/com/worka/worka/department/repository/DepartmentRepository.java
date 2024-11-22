package com.worka.worka.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.worka.worka.department.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {



}
