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
	
	public LoginEmployee findEmployeeByIdAndPassword(int id, String password) {
		return loginMapper.findByIdAndPassword(id, password);
	}
}