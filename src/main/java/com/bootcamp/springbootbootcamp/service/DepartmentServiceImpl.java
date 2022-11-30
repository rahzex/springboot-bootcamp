package com.bootcamp.springbootbootcamp.service;

import java.util.List;
import java.util.Optional;

import com.bootcamp.springbootbootcamp.entity.Department;
import com.bootcamp.springbootbootcamp.exception.DepartmentNotFoundException;
import com.bootcamp.springbootbootcamp.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override public Department fetch(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department Not Found!"));
    }

    @Override public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override public Department update(Long id, Department department) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            log.info("Department with id : {} was not found", id);
        }
        return departmentOptional.map(value -> departmentRepository.save(Department.builder()
                .id(id)
                .name(department.getName() != null ? department.getName() : departmentOptional.get().getName())
                .address(department.getAddress() != null ? department.getAddress() : value.getAddress())
                .code(department.getCode() != null ? department.getCode() : value.getCode())
                .build()))
                .orElseThrow(() -> new DepartmentNotFoundException("Department Not Found!"));
    }

    @Override public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByNameIgnoreCase(departmentName);
    }
}
