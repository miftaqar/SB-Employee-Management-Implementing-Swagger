package com.ifteqars.emp.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ifteqars.emp.exception.ResourceNotFoundException;
import com.ifteqars.emp.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public ResponseEntity<Employee> getEmployeeByID(int employeeID) throws ResourceNotFoundException;

	public Employee createOrUpdateEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public Map<String, Boolean> deleteEmployee(Employee employee);

	
}