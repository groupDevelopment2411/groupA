package com.example.demo.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.EmployeeRepository;
import com.example.demo.login.LoginEmployee;

@Controller
public class DeleteController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/delete")
	public String showDelectionForm(Model m,@RequestParam int id) {
		LoginEmployee employee = employeeRepository.findById(id).orElse(null);
		m.addAttribute("employee",employee);
		return "delete";
	}
}
