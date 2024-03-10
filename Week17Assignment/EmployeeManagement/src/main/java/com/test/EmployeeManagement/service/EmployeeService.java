package com.test.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.test.EmployeeManagement.model.Employee;
import com.test.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;

	public void add(Employee employee) {
		repo.save(employee);
	}

	public void update(Employee employee) {
		repo.save(employee);
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}

	public List<Employee> showAll() {
		return repo.findAll();
	}

	public Employee getById(int id) {
		Optional<Employee> opt = repo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}
		return opt.get();
	}

	public List<Employee> filter(String searchKey) {
		Employee dummy = new Employee();
		dummy.setFirstName(searchKey);
		;
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("first_name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("id", "last_name", "department", "email", "role");
		Example<Employee> example = Example.of(dummy, exampleMatcher);

		return repo.findAll(example);
	}

}
