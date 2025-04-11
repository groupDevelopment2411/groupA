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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String RegistrationFoam(Model m) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "社員情報登録画面（入力）");
		
		return "RegistrationFoam";
	}
	
	@RequestMapping("/ReturnToRegistrationForm")
	public String ReturnToRegistrationForm(Model m,
			@RequestParam("name") String name,
			@RequestParam("age") String ageString,
			@RequestParam("password1") String password
			) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "社員情報登録画面（入力）");
		
	    m.addAttribute("name", name);
	    m.addAttribute("age", ageString);
	    m.addAttribute("password1", password);
	    
	    
	    // System.out.printlnで内容を確認
	    System.out.println("Userオブジェクトの初期値:");
	    System.out.println("名前: " + name);
	    System.out.println("年齢: " + ageString);
	    System.out.println("パスワード1: " + password);
	    
        
        return "ReturnToRegistrationForm";
        
	}
	
	@PostMapping("/RegistrationConfirm")
	public String Confirm(Model m,
			@RequestParam("name") String name,
			@RequestParam("age") String ageString,
			@RequestParam("password1") String password
			) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "確認画面");
		
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
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "登録完了");
		
//		データベースを更新
//		登録した日を入れる
		
		
		Registration registration = new Registration(name, age, password1, startdate, null);
		
		service.insert(registration);
		
		
		return "RegistrationComplete";
		
		
	}
	
	
	@RequestMapping("/backToForm")
	public String backToForm(Model m) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "社員情報登録画面（入力）");
		
		return "RegistrationFoam";
	}
	
	
	@RequestMapping("/backToSearch")
	public String backToSearch(Model m) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "テスト用メニュー");
		
		return "SearchTemporary";
	}
    
	
//	ここから下はテストの為の仮ページ
	
    

    @RequestMapping("/SearchTemporary")
    public String searchTemporary(Model m) {
    	
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "テスト用メニュー");
		
        return "SearchTemporary";
    }
    
//    ヘッダーフッターのやつ
	
    @ModelAttribute
    public void addSessionAttributes(HttpSession session, Model model) {
        // 仮のログインIDとログイン時間 実際はログインページから情報を受け取る
        String userid = "1";
        String loginTime = "20220518 1208";

//        // ログ出力
//        logger.info("Adding session attributes: userid={}, loginTime={}", userid, loginTime);

        // ここから中身
        model.addAttribute("userid", userid);
        model.addAttribute("loginTime", loginTime);
        
        
    }
	
}
