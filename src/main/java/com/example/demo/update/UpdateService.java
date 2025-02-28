package com.example.demo.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateService {

	@Autowired
	private UpdateMapper mapper;
	
	public List<Update> findUserById(int id){
		return mapper.findUserById(id);
	}
	
}
