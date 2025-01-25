package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model m,@RequestParam int id,@RequestParam String password) {
		
		if(loginService.authenticate(id, password)) {

			return "redirect:/success";/*仮ページ:出来上がったらメインメニューへのリンクに差し替え*/

		}else {

			m.addAttribute("errorMsg","IDまたはパスワードが正しくありません");
			return "login";

		}
	}
			
	@GetMapping("/success")
	public String showSuccessPage() {
		return "success";
	}
}
