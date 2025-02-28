package com.example.demo.update;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.registration.RegistrationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateController {
	
	@Autowired
	private UpdateService service;
	
//	更新ページに行く前にIDを入力する画面をかませて更新フォームに行くID入力ページを作成する
//	検索画面でIDをクリックした際にも更新フォームに飛ぶようにする
	
	@RequestMapping("/UpdateLogin")
	public String UpdateLogin() {
		
	        return "UpdateLogin";
	}
	
	@RequestMapping("/UpdateIdLink")
	public String UpdateIdLink() {
		
		return "UpdateIdLink";
	}
	
	@RequestMapping("/checkUserId")
	@ResponseBody
    public Map<String, Object> checkUserId(@RequestParam("userid") int userId) {
		
//		IDをHTMLから受け取ってIDをupdateserviceからupdatemapperへ送りSQLでサーバーをID検索してヒットしなかったらデータなしと返す、データがあった場合は次のupdateForamページに送る
		
		List<Update> user = service.findUserById(userId);
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("userIdFound", user != null);
		
		return response;
    }
    
	    
	
	@RequestMapping("/UpdateFoam")
	public String UpdateForm() {
		
		
		return "UpdateFoam";
	}
	
	@PostMapping("/UpdateConfirm")
	public String UpdateConfirm(Model m,
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("age") String ageString,
			@RequestParam("password1") String password
			) {
		
		
		
		
		return "UpdateConfirm";
	}
	
	
	@RequestMapping("/update/backToForm")
	public String backToForm() {
		return "RegistrationFoam";
	}
	
	
	@RequestMapping("/update/backToSearch")
	public String backToSearch() {
		return "SearchTemporary";
	}
	
//  ヘッダーフッターのやつ
	
  @ModelAttribute
  public void addSessionAttributes(HttpSession session, Model model) {
      // 仮のログインIDとログイン時間 実際はログインページから情報を受け取る
      String userid = "1";
      String loginTime = "20220518 1208";

//      // ログ出力
//      logger.info("Adding session attributes: userid={}, loginTime={}", userid, loginTime);

      // ここから中身
      model.addAttribute("userid", userid);
      model.addAttribute("loginTime", loginTime);
      
      
  }
  
  @RequestMapping("/update/SearchTemporary")
  public String searchTemporary() {
      return "SearchTemporary";
  }
  
}
