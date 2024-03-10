package com.test.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.EmployeeManagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
