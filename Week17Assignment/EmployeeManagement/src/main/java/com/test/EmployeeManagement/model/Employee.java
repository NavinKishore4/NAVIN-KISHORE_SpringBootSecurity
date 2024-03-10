package com.test.EmployeeManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	//primary key for Data Base
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String department;
	private String role;
	private String email;

}
