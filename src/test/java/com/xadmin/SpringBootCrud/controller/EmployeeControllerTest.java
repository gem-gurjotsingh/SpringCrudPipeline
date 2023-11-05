package com.xadmin.SpringBootCrud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xadmin.SpringBootCrud.bean.Employee;
import com.xadmin.SpringBootCrud.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = AutoConfigureMockMvc.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        // Arrange
        Employee employee = new Employee(1, "Gurjot", "Java");
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        // Act and Assert
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        Employee employee = new Employee(1, "Gurjot", "Java");
        when(employeeService.getEmployee(1)).thenReturn(employee);

        // Act and Assert using MockMvc
        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gurjot"));
    }

    @Test
    void saveEmployeeTest() throws Exception {
        // Arrange
        Employee demo = new Employee(1, "Vinay", "Java");
        when(employeeService.saveEmployee(demo)).thenReturn(demo);

        // Act and Assert using MockMvc
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(demo)))
                .andExpect(status().isNoContent());
    }
}
