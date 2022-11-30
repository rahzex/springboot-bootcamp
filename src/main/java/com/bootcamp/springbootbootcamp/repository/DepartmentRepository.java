package com.bootcamp.springbootbootcamp.repository;

import com.bootcamp.springbootbootcamp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String departmentName);

    Department findByNameIgnoreCase(String departmentName);
}
