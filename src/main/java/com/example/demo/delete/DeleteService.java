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
    public DeleteEmployee getEmployeeById(int id) {
        return deleteMapper.findById(id);
    }

    //社員情報を取得する(複数)
    public List<DeleteEmployee> getEmployeeByIds(List<Integer> ids) {
        return deleteMapper.findByIds(ids);
    }
    
    // 社員情報を削除する
    public void deleteEmployeeById(int id) {
        deleteMapper.deleteById(id);
    }
        
    //社員情報を削除する(複数)
    public void deleteEmployeeByIds(List<Integer> ids) {
        deleteMapper.deleteByIds(ids);
    }
}