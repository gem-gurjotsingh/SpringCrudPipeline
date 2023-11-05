package com.xadmin.SpringBootCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.xadmin.SpringBootCrud.bean.Employee;
import com.xadmin.SpringBootCrud.repository.EmployeeRepository;

@Service
@EnableCaching
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Get all employees return the employees list
	 * @return
	 */
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * Get employee will return the employee with emp id
	 * @param employeeId
	 * @return
	 */
	public Employee getEmployee(final int employeeId) {
		return employeeRepository.findById(employeeId).orElseThrow(
				() -> new IllegalArgumentException("Employee Id: " + employeeId + " not found")
		);
	}

	/**
	 * Add a new employee in the list
	 * @param employeeDetails
	 */
	public Employee saveEmployee(final Employee employeeDetails) {
		return employeeRepository.save(employeeDetails);
	}
}