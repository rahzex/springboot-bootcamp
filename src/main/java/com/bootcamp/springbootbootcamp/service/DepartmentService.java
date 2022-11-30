package com.bootcamp.springbootbootcamp.service;

import java.util.List;

import com.bootcamp.springbootbootcamp.entity.Department;

public interface DepartmentService {

    Department save(Department department);

    List<Department> fetchAllDepartments();

    Department fetch(Long id);

    void delete(Long id);

    Department update(Long id, Department department);

    Department fetchDepartmentByName(String departmentName);
}
