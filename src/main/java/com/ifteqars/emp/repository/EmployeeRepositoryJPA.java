package com.ifteqars.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifteqars.emp.model.Employee;

@Repository
public interface EmployeeRepositoryJPA extends JpaRepository<Employee, Integer> {

}
