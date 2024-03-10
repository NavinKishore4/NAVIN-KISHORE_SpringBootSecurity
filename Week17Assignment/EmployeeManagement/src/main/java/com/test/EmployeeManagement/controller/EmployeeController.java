package com.test.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.EmployeeManagement.model.Employee;
import com.test.EmployeeManagement.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService repo;

	@GetMapping("/employee/show-all")
	public List<Employee> getAll() {
		return repo.showAll();
	}

	@PostMapping("/employee/addNew")
	public String addNew(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String department, @RequestParam String role, @RequestParam String email,
			Authentication authentication) {
		String acceptedRole = "admin";

		// Check if the current user has the role of "admin"
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(acceptedRole));

		if (isAdmin) {
			// Check if the employee with the given ID already exists
			Employee existingEmployee = repo.getById(id);
			if (existingEmployee != null) {
				return "Duplicate Id";
			} else {
				// Create a new Employee object and save it
				Employee newEmployee = new Employee(id, firstName, lastName, department, role, email);
				repo.add(newEmployee); // Corrected: Use newEmployee instead of existingEmployee
				return "Data Added";
			}
		} else {
			return "403 Forbidden";
		}
	}

	@PutMapping("/employee/update")
	public String update(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String department, @RequestParam String role, @RequestParam String email,
			Authentication authentication) {
		String acceptedRole = "admin";

		// Check if the current user has the role of "admin"
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(acceptedRole));

		if (isAdmin) {
			Employee e1 = new Employee(id, firstName, lastName, department, role, email);
			repo.update(e1);
			return "Data updated successfully";
		} else {
			return "403 Forbidden";
		}
	}

	@DeleteMapping("/employee/delete")
	public String delete(@RequestParam int id, Authentication authentication) {
		String acceptedRole = "admin";

		// Check if the current user has the role of "admin"
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(acceptedRole));

		if (isAdmin) {
			repo.deleteById(id);
			return "Data Deleted";
		} else {
			return "403 Forbidden";
		}
	}

	@GetMapping("/employee/getbyid")
	public Employee getById(@RequestParam int id) {
		return repo.getById(id);
	}

	@PostMapping("/employee/search")
	public List<Employee> searchEmployeesByName(@RequestParam String name) {
		// Call the repository method to search employees by name
		List<Employee> foundEmployees = repo.filter(name);
		return foundEmployees;
	}

}
