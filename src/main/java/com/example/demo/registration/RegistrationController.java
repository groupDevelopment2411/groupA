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
	public String RegistrationFoam() {
		return "RegistrationFoam";
	}
	
//	@PostMapping("/Registration")
//	public String insertEmployee(Model m,
//			@RequestParam("name") String name,
//			@RequestParam("age") String ageString,
//			@RequestParam("password1") String password1,
//			@RequestParam("password2") String password2
//			
////			@RequestParam("") Date startdate,
////			@RequestParam("") Date enddate
//			
//			) {
//		
////		ageに文字が入っていないかcheck
////		ＨＴＭＬで警告ポップを出すようにする
//		int age;
//		try {
//			age = Integer.parseInt(ageString);
//		}catch(NumberFormatException e){
//			m.addAttribute("result","数値以外が入力されてる");
//			return "result";
//		}
//		
//		
////		passwordが「半角英数」「大文字」「小文字」「8文字以上」をチェック。
////		「半角英のみ」「半角数のみ」「８文字以下」を却下する。
////		ＨＴＭＬで警告ポップを出すようにする
//		
//		PasswordCheck pc = new PasswordCheck();
//		
//		if(!pc.passwordCheck(password1)) {
//			m.addAttribute("result","正確に入力できていない");
//			return "result";
//		}
//		
////		password2がpassword1と完全一致しているか確認
////		ＨＴＭＬで警告ポップを出すようにする
//		
//		if(!password1.equals(password2)) {
//			m.addAttribute("result","パスワードと合っていない");
//			return "result";
//		}
		
		
//		データベースを更新
//		登録した日を入れる
		
//		LocalDate today = LocalDate.now();
//		Date startdate = Date.valueOf(today);
//		
//		Registration registration = new Registration(name, age, password1, password2, startdate, null);
//		
//		service.insert(registration);
//		
//		
//		m.addAttribute("result","更新成功");
//		
//		return "result";
//	}
	
	
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
	
    

    @RequestMapping("/SearchTemporary")
    public String searchTemporary() {
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
