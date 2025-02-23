package com.example.demo.delete;

import java.util.List;

public interface DeleteService {
    
    List<DeleteEmployee> getEmployeesByIds(List<Integer> ids);
    
    void deleteEmployeesByIds(List<Integer> ids);
    
    boolean employeeExists(Integer id); // IDが存在するか確認するメソッド
    
}