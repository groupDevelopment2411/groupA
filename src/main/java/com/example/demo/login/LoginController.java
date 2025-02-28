package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model m,
						@RequestParam int id,
						@RequestParam String password,
						HttpSession session) {
		LoginEmployee employee = loginService.findEmployeeByIdAndPassword(id, password);
		
		if(employee != null) {
			session.setAttribute("loginUser", employee); /*ログインユーザー情報を保存*/
			session.setAttribute("loginTime", new java.util.Date()); /*ログイン時間を保存*/
			return "redirect:/dummy/success";/*仮ページ:出来上がったらメインメニューへのリンクに差し替え*/

		}else {

			m.addAttribute("errorMsg","IDまたはパスワードが正しくありません");
			return "login";

		}
	}
			
	@GetMapping("/dummy/success")
	public String showSuccessPage() {
		return "dummy/success";
	}
}
