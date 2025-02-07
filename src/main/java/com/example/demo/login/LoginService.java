package com.example.demo.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	public List<LoginEmployee> selectAll(){
		return loginMapper.selectAll();
	}
	
	public boolean authenticate(int id, String password) {
		LoginEmployee employee = loginMapper.findByIdAndPassword(id, password);
		return employee != null;
	}
}