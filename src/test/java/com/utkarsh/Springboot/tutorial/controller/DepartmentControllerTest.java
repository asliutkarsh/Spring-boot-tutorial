package com.utkarsh.Springboot.tutorial.controller;

import com.utkarsh.Springboot.tutorial.entity.Department;
import com.utkarsh.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    public Department department;



    @BeforeEach
    void setUp() {
        department =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Patna")
                        .departmentCode("IT-06")
                        .departmentId(1L)
                        .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputdepartment =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Patna")
                        .departmentCode("IT-75")
                        .build();
        Mockito.when(departmentService.saveDepartment(inputdepartment)).thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                "    \"departmentName\":\"IT\",\n" +
                "    \"departmentAddress\":\"Patna\",\n" +
                "    \"departmentCode\":\"IT-75\"\n" +
                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}