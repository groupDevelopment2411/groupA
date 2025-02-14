package com.example.demo.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationMapper mapper;
	
	
	public void insert(Registration registration) {
		mapper.insert(registration);
	}
	
}
