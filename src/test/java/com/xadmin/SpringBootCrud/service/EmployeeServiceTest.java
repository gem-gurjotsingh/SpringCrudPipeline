package com.xadmin.SpringBootCrud.service;

import com.xadmin.SpringBootCrud.bean.Employee;
import com.xadmin.SpringBootCrud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AutoConfigureMockMvc.class)
@WebMvcTest(EmployeeService.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    Employee employee = new Employee();

    @BeforeEach
    void init() {
        employee = createSample(1, "Gurjot", "Java");
    }

    @Test
    void getAllEmployeesTest() {
        List<Employee> dummy = Arrays.asList(employee);
        when(employeeRepository.findAll()).thenReturn(dummy);
        List<Employee> finalList = employeeService.getAllEmployees();
        assertNotNull(finalList);
        assertEquals(1, finalList.size());
    }

    @Test
    void getEmployeeByIdTest() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmployee(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void saveEmployeeTest() {
        Employee demo = new Employee(1, "Vinay", "Java");
        when(employeeRepository.save(any(Employee.class))).thenReturn(demo);
        Employee employee1 = employeeService.saveEmployee(demo);
        assertNotNull(employee1);
        assertEquals("Vinay", employee1.getName());
        assertEquals(1, employee1.getId());
    }

    public Employee createSample(int id, String name, String department) {
        Employee demoEmployee = new Employee();
        demoEmployee.setId(id);
        demoEmployee.setName(name);
        demoEmployee.setDepartment(department);
        return demoEmployee;
    }
}