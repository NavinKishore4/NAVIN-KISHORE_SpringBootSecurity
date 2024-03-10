package com.test.EmployeeManagement;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.EmployeeManagement.security.AppUser;
import com.test.EmployeeManagement.security.AppUserService;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

	@Autowired
	AppUserService appUserService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Set<String> authAdmin = new HashSet<String>();
		authAdmin.add("Admin");

		Set<String> authUser = new HashSet<String>();
		authUser.add("User");

		PasswordEncoder en = new BCryptPasswordEncoder();

		AppUser appAdmin = new AppUser(1, "admin", "admin", en.encode("admin"), authAdmin);
		appUserService.add(appAdmin);

		AppUser appUser = new AppUser(2, "user", "user", en.encode("user"), authUser);
		appUserService.add(appUser);

	}

}
