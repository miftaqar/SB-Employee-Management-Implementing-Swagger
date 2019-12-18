package com.ifteqars.emp.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ifteqars.emp.model.Employee;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

	// Autowire the JdbcTemplate
	@Autowired
	JdbcTemplate jdbcTemplate;

	// Autowire the environment
	@Autowired
	Environment environment;

	@Override
	public List<Employee> getAllEmployees() {
		log.info("getAllEmployees() method started");

		// call the query from application.yml file
		String sql = environment.getProperty("dbQuery.getAllEmployees");

		log.info("SQL Query for getAllEmployees: " + sql);

		List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));

		log.info("getAllEmployees() method completed");
		return employees;
	}

	@Override
	public Employee getEmployeeByID(int employeeID) {
		log.info("In getEmployeeByID  method of EmployeeRepositoryImpl class...");

		// call the query from application.yml file
		String sql = environment.getProperty("dbQuery.getEmployeeByID");

		log.info("SQL Query for getEmployeeByID: " + sql);

		Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[] { employeeID },
				new BeanPropertyRowMapper<Employee>(Employee.class));

		return employee;
	}

	@Override
	public int createEmployee(Employee employee) {
		log.info("In createEmployee  method of EmployeeRepositoryImpl class...");

		// call the query from application.yml file
		String sql = environment.getProperty("dbQuery.createEmployee");
		
		log.info("SQL Query for createEmployee: " + sql);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(sql,
				new Object[] { employee.getFirstName(), employee.getLastName(), employee.getEmailID() }, keyHolder);

		log.info("Generated Key: " + (int) keyHolder.getKey());
		return (int) keyHolder.getKey();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Boolean> deleteEmployee(Integer employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

}
