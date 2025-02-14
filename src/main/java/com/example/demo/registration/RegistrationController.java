package com.example.demo.registration;

import java.sql.Date;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	
	@RequestMapping("/RegistrationFoam")
	public String RegistrationFoam() {
		return "RegistrationFoam";
	}
	
	
	@PostMapping("/RegistrationConfirm")
	public String Confirm(Model m,
			@RequestParam("name") String name,
			@RequestParam("age") String ageString,
			@RequestParam("password1") String password
			) {
		
		
		int age = Integer.parseInt(ageString);
		
		LocalDate today = LocalDate.now();
		Date startdate = Date.valueOf(today);
		
		Registration confirm = new Registration(name, age, password, startdate, null);
		
		m.addAttribute("confirm",confirm);
		
		return "RegistrationConfirm";
	}
	
	
	
	@PostMapping("/RegistrationComplete")
	public String insertEmployee(Model m,
			@RequestParam("name") String name,
			@RequestParam("age") String ageString,
			@RequestParam("password1") String password1,
			@RequestParam("startdate") Date startdate
			) {
		
		int age = Integer.parseInt(ageString);
		
		
//		データベースを更新
//		登録した日を入れる
		
		
		Registration registration = new Registration(name, age, password1, startdate, null);
		
		service.insert(registration);
		
		
		return "RegistrationComplete";
		
		
	}
	
	
	@RequestMapping("/backToForm")
	public String backToForm() {
		return "RegistrationFoam";
	}
	
	
	@RequestMapping("/backToSearch")
	public String backToSearch() {
		return "SearchTemporary";
	}
	
//	前の画面からデータを受け取ってヘッダを表示させる
	
    @RequestMapping("/Header")
    public String home(Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("loginTime", session.getAttribute("loginTime"));
        model.addAttribute("pageTitle", "Home");

        return "Header";
    }
    
	
//	ここから下はテストの為の仮ページ
	
    @PostMapping("/loginTest")
    public String loginTest(HttpServletRequest request,
    		@RequestParam("userid")String userid,
    		@RequestParam("password")String password
    		) {
    	
    	LocalDateTime localTime = LocalDateTime.now();
    	
//    	ここでセッションにログイン情報を入力
    	HttpSession session = request.getSession();
    	session.setAttribute("userid",userid);
    	session.setAttribute("loginTime",localTime.toString());
    	
    	
    	return "/SearchTemporary";
    	
    }
    
    
    @GetMapping("/loginTest")
    public String loginTestPage() {
        return "loginTest"; // loginTest.html を表示する
    }

    @GetMapping("/SearchTemporary")
    public String searchTemporary() {
        return "SearchTemporary";
    }
	
	
}
