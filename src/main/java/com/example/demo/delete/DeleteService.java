package com.example.demo.delete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
	@Autowired
	private DeleteMapper deleteMapper;

	public List<DeleteEmployee> selectAll(){
		return deleteMapper.selectAll();
	}
}
