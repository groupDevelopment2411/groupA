package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public boolean authenticate(int id, String password) {
		Employee employee = employeeRepository.findByIdAndPassword(id, password);
		return employee != null;
	}
}