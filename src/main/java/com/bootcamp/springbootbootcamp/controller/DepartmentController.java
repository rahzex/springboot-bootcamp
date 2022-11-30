package com.bootcamp.springbootbootcamp.controller;

import java.util.List;

import com.bootcamp.springbootbootcamp.entity.Department;
import com.bootcamp.springbootbootcamp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable("id") Long id) {
        return departmentService.fetch(id);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.fetchAllDepartments();
    }

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.delete(id);
        return "Deleted " +id;
    }
}
