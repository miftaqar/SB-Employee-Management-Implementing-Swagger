package com.ifteqars.emp.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifteqars.emp.exception.ResourceNotFoundException;
import com.ifteqars.emp.model.Employee;
import com.ifteqars.emp.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@Api(value = "Employee Management System")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// Get Method for all Employees
	@GetMapping("/employees")
	@ApiOperation(value = "view a list of employees", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is forbidden") })
	public List<Employee> getAllEmployees() {
		log.debug("getAllEmployees() method started from controller");

		// Invoke the service class method
		List<Employee> employees = employeeService.getAllEmployees();

		log.debug("getAllEmployees() method completed from controller");
		return employees;

	}

	// Get Method for Single Employee
	@GetMapping("/employees/{employeeID}")
	@ApiOperation(value = "Get Employee by ID")
	public ResponseEntity<Employee> getEmployeeByID(
			@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable int employeeID)
			throws ResourceNotFoundException {
		log.debug("getEmployeeByID() method started from controller");

		log.debug("getEmployeeByID() method completed from controller");
		return employeeService.getEmployeeByID(employeeID);
	}

	// Post Method for Employee
	@PostMapping("/employees")
	@ApiOperation(value = "Add an employee")
	public Employee createEmployee(
			@ApiParam(value = "Employee object will be stored in database", required = true) @Valid @RequestBody Employee employee) {
		log.debug("createEmployee() method started from controller");

		Employee employeeInserted = employeeService.createOrUpdateEmployee(employee);

		log.info("New Employee Inserted with ID::" + employeeInserted.getEmployeeID());

		log.debug("createEmployee() method completed from controller");

		return employeeInserted;

	}

	// Put Method for Employee
	@PutMapping("/employees/{employeeID}")
	@ApiOperation(value = "Update an employee")
	public ResponseEntity<Employee> updateEmployee(
			@ApiParam(value = "Employee id to update Employee object", required = true) @PathVariable int employeeID,
			@ApiParam(value = "Update Employee Object", required = true) @Valid @RequestBody Employee employeeDetails)
			throws ResourceNotFoundException {
		log.debug("updateEmployee() method started from controller");

		ResponseEntity<Employee> employee = employeeService.getEmployeeByID(employeeID);

		employee.getBody().setEmailID(employeeDetails.getEmailID());
		employee.getBody().setFirstName(employeeDetails.getFirstName());
		employee.getBody().setLastName(employeeDetails.getLastName());

		final Employee updatedEmployee = employeeService.updateEmployee(employee.getBody());

		log.debug("updateEmployee() method completed from controller");
		return ResponseEntity.ok().body(updatedEmployee);

	}

	// Delete Method for Employee
	@DeleteMapping("/employees/{employeeID}")
	@ApiOperation(value = "Deletes an employee")
	public Map<String, Boolean> deleteEmployee(
			@ApiParam(value = "Employee Id to be deleted from database", required = true) @PathVariable int employeeID)
			throws ResourceNotFoundException {
		log.debug("updateEmployee() method started from controller");

		ResponseEntity<Employee> employee = employeeService.getEmployeeByID(employeeID);

		Map<String, Boolean> responseMap = employeeService.deleteEmployee(employee.getBody());

		log.debug("updateEmployee() method completed from controller");
		return responseMap;

	}
}
