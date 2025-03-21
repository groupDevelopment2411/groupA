package com.example.demo.delete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//controllerからmapperを通してDB操作をする
@Service
public class DeleteService {
    @Autowired
    private DeleteMapper deleteMapper;
    
    //社員情報を取得する
    public List<DeleteEmployee> getEmployeesByIds(List<Integer> ids) {
        return deleteMapper.findByIds(ids);
    }
    
    // 社員情報を削除する
    public void deleteEmployeesByIds(List<Integer> ids) {
        deleteMapper.deleteByIds(ids);
    }
    
    public boolean employeeExists(Integer id) {
    	return deleteMapper.findById(id) != null;
    }
    
    public DeleteEmployee findByUsername(String username) {
    	return deleteMapper.findByUsername(username);
    }
}