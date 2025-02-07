package com.example.demo.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteController {
	@Autowired
	private DeleteService deleteService;
	
	@GetMapping("/delete")
	public String showDeleteForm() {
		return "delete";
	}
	
	
}
