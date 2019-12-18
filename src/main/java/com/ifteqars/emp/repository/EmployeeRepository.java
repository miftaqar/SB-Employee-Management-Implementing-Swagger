package com.ifteqars.emp.repository;

import java.util.List;
import java.util.Map;

import com.ifteqars.emp.model.Employee;

public interface EmployeeRepository {

	public List<Employee> getAllEmployees();

	public Employee getEmployeeByID(int employeeID);

	public int createEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public Map<String, Boolean> deleteEmployee(Integer employeeID);
}
