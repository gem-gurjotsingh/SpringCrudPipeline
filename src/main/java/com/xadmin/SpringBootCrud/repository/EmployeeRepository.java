package com.xadmin.SpringBootCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xadmin.SpringBootCrud.bean.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
}