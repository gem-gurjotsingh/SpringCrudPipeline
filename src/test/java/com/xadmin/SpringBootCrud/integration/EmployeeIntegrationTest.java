package com.xadmin.SpringBootCrud.integration;

import com.xadmin.SpringBootCrud.SpringBootCrudApplication;
import com.xadmin.SpringBootCrud.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootCrudApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class EmployeeIntegrationTest extends TestContainerAbstract {

    private final HttpHeaders headers = new HttpHeaders();
    private String urlPrefix;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @PostConstruct
    public void setup() {
        urlPrefix = "http://localhost:" + port + "/employee";
    }

    @Test
    @Order(1)
    public void shouldSaveEmployee() {
        Employee requestRecord = new Employee(1, "Gurjot", "Java");
        String url = urlPrefix;
        HttpEntity<Employee> responseEntity = new HttpEntity<>(requestRecord, headers);
        ResponseEntity<Employee> response = restTemplate.exchange(url, HttpMethod.POST, responseEntity, Employee.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @Order(2)
    public void shouldGetAllHotel() {
        String url = urlPrefix;
        ResponseEntity<Employee[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Employee[].class);
        Employee[] resultArray = response.getBody();
        List<Employee> result = Arrays.asList(resultArray);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(result);
        assertEquals(1, result.get(0).getId());
        assertEquals("Gurjot", result.get(0).getName());
    }
}
