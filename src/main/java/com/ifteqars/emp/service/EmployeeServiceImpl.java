package com.ifteqars.emp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifteqars.emp.exception.ResourceNotFoundException;
import com.ifteqars.emp.model.Employee;
import com.ifteqars.emp.repository.EmployeeRepositoryJPA;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepositoryJPA employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		log.debug("getAllEmployees() method started from EmployeeServiceImpl");

		log.debug("getAllEmployees() method started from EmployeeServiceImpl");
		return employeeRepository.findAll();
	}

	public ResponseEntity<Employee> getEmployeeByID(int employeeID) throws ResourceNotFoundException {
		log.debug("getEmployeeByID() method started from EmployeeServiceImpl");

		Employee employee = employeeRepository.findById(employeeID)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeID not found for this id ::" + employeeID));

		log.debug("getEmployeeByID() method completed from EmployeeServiceImpl");
		return ResponseEntity.ok().body(employee);
	}

	public Employee createOrUpdateEmployee(Employee employee) {
		log.debug("createEmployee() method started from EmployeeServiceImpl");

		Employee employeeCreated = employeeRepository.save(employee);

		log.debug("createEmployee() method completed from EmployeeServiceImpl");
		return employeeCreated;
	}

	@Override
	public Map<String, Boolean> deleteEmployee(Employee employee) {
		log.debug("deleteEmployee() method started from EmployeeServiceImpl");

		employeeRepository.delete(employee);

		Map<String, Boolean> responseMap = new HashMap<String, Boolean>();

		responseMap.put("Deleted", Boolean.TRUE);

		log.debug("deleteEmployee() method completed from EmployeeServiceImpl");
		return responseMap;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		log.debug("updateEmployee() method started from EmployeeServiceImpl");

		Employee updatedEmployee = employeeRepository.save(employee);

		log.info("Employee Updated for EmployeeID: " + updatedEmployee.getEmployeeID());

		log.debug("updateEmployee() method completed from EmployeeServiceImpl");
		return updatedEmployee;
	}

}
