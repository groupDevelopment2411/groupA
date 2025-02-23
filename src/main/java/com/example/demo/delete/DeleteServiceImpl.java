package com.example.demo.delete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImpl implements DeleteService {
	@Autowired
	private DeleteMapper deleteMapper;

	@Override
	public List<DeleteEmployee> getEmployeesByIds(List<Integer> ids) {
		// TODO 自動生成されたメソッド・スタブ
		return deleteMapper.findByIds(ids);
	}

	@Override
	public void deleteEmployeesByIds(List<Integer> ids) {
		// TODO 自動生成されたメソッド・スタブ
		deleteMapper.deleteByIds(ids);
	}

	@Override
	public boolean employeeExists(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return deleteMapper.findById(id) != null;
	}

}
