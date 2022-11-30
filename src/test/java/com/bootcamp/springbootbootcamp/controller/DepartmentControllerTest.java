package com.bootcamp.springbootbootcamp.controller;

import com.bootcamp.springbootbootcamp.entity.Department;
import com.bootcamp.springbootbootcamp.exception.DepartmentNotFoundException;
import com.bootcamp.springbootbootcamp.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getDepartment() throws Exception {
        Department department = Department.builder()
                .name("CSE")
                .code("CSE201")
                .address("India")
                .build();
        Long id = 1L;

        Mockito.when(departmentService.fetch(id)).thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/departments/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(department.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(department.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(department.getCode()));
    }

    @Test
    void saveDepartment() throws Exception {
        Department department = Department.builder()
                .name("CSE")
                .code("CSE201")
                .address("India")
                .build();

        Mockito.when(departmentService.save(department)).thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(department.getName()));
    }

    @Test
    void updateDepartment() throws Exception {
        Department department = Department.builder()
                .name("CSE")
                .code("CSE201")
                .address("India")
                .build();
        Long id = 1L;

        Mockito.when(departmentService.update(id, department)).thenThrow(new DepartmentNotFoundException("Department Not Found!"));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/departments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Department Not Found!"));
    }
}