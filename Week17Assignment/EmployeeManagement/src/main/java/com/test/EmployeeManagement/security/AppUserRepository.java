package com.test.EmployeeManagement.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

	// creating custom methods using jpa
	Optional<AppUser> findByName(String name);

}
