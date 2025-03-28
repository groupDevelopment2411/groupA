package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController { 



    @GetMapping("/RegistrationFoam")  
    public String showRegisterPage() {
        return "register"; // register.htmlを表示
    }

    @GetMapping("/UpdateLogin")  
    public String showUpdatePage() {
        return "update"; // update.htmlを表示
    }

    @GetMapping("/delete/search")  
    public String showDeletePage() {
        return "delete"; // delete.htmlを表示
    }

    @GetMapping("/login")  
    public String showLoginPage() {
        return "login"; // login.htmlを表示
    }

    @GetMapping("/search")  
    public String showSearchPage() {
        return "search"; // search.htmlを表示
    }
}
