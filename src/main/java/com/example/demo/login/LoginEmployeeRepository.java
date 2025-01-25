package com.example.demo.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginEmployeeRepository extends JpaRepository<LoginEmployee, Integer> {
    LoginEmployee findByIdAndPassword(int id, String password);
}

