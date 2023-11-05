package com.xadmin.SpringBootCrud.controller;

import com.xadmin.SpringBootCrud.bean.Employee;
import com.xadmin.SpringBootCrud.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Get All Employees API
	 * @return
	 */
	@Operation(operationId = "getAllEmployees", summary = "Get a list of all the employees")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the Employees"),
			@ApiResponse(responseCode = "5XX", description = "Mayday Mayday Mayday!!")})
	@GetMapping(produces = "application/json")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	/**
	 * Get All Employees API
	 * @return
	 */
	@Operation(operationId = "getEmployee", summary = "Get employee with employee id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the Employee"),
			@ApiResponse(responseCode = "400", description = "Employee with given id not found"),
			@ApiResponse(responseCode = "5XX", description = "Mayday Mayday Mayday!!")})
	@GetMapping(value = "{employeeId}", produces = "application/json")
	public Employee getEmployee(@Parameter(description = "Employee Id of the employee")
									   @PathVariable final int employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	/**
	 * Get All Employees API
	 * @return
	 */
	@Operation(operationId = "saveEmployee", summary = "Add new employee into the list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successfully Added the Employees"),
			@ApiResponse(responseCode = "5XX", description = "Mayday Mayday Mayday!!")})
	@PostMapping(produces = "application/json")
	public ResponseEntity<Object> saveEmployee(@Parameter(description = "Employee details")
											   @RequestBody final Employee employeeDetails) {
		employeeService.saveEmployee(employeeDetails);
		return ResponseEntity.noContent().build();
	}
}