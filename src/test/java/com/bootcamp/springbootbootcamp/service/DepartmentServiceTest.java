package com.bootcamp.springbootbootcamp.service;

import java.util.Optional;

import com.bootcamp.springbootbootcamp.entity.Department;
import com.bootcamp.springbootbootcamp.exception.DepartmentNotFoundException;
import com.bootcamp.springbootbootcamp.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testFindByDepartmentName() {
        String departmentName = "CSE";
        Department department = Department.builder()
                .name("CSE")
                .address("India")
                .code("CSE201")
                .build();

        Mockito.when(departmentRepository.findByNameIgnoreCase(departmentName))
                .thenReturn(department);
        Department found = departmentService.fetchDepartmentByName(departmentName);

        Mockito.verify(departmentRepository, Mockito.times(1)).findByNameIgnoreCase(departmentName);
        assertThat(departmentName).isEqualTo(found.getName());
    }

    @Test
    void testFindByNameNotFound() {
        String departmentName = "MATH";
        Mockito.when(departmentRepository.findByNameIgnoreCase(departmentName))
                .thenReturn(null);
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertThat(found).isNull();
    }

    @Test
    void testUpdateDepartmentNotFound() {
        Long id = 1L;
        Department department = Department.builder()
                .name("CSE")
                .address("India")
                .code("CSE201")
                .build();

        Mockito.when(departmentRepository.findById(id)).thenReturn(Optional.empty());

        assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.update(id, department))
                .withMessage("Department Not Found!");
    }
}