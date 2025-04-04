package com.example.demo.update;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

import com.example.demo.registration.Registration;
import com.example.demo.registration.RegistrationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateController {
	
	@Autowired
	private UpdateService service;
	
	
//	更新ページに行く前にIDを入力する画面をかませて更新フォームに行くID入力ページを作成する
//	検索画面でIDをクリックした際にも更新フォームに飛ぶようにする
	
	
	@RequestMapping("/UpdateLogin")
	public String UpdateLogin(Model m) {
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "社員情報更新対象者（ID入力）");
		
		return "UpdateLogin";
	}

	
//	IDクリックからのリンk
	@RequestMapping("/UpdateIdLink")
	public String UpdateIdLink(Model m, @RequestParam("userid") String userId) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "情報更新フォーム");
		
		
	    // 入力されていない
	    if (userId == null || userId.isEmpty()) {
	        m.addAttribute("response", "1");
	        System.out.println("Response: " + m.getAttribute("response"));
	        return "RetryCheckId"; // 再入力画面へ
	    }

	    // 入力された値が数値ではない
	    int id;
	    try {
	        id = Integer.parseInt(userId);
	    } catch (NumberFormatException e) {
	        m.addAttribute("response", "2");
	        System.out.println("Response: " + m.getAttribute("response"));
	        return "RetryCheckId"; // 再入力画面へ
	    }

	    // IDが存在しない
	    List<Update> user = service.findUserById(id);
	    if (user.size() == 0) {
	        m.addAttribute("response", "3");
	        System.out.println("Response: " + m.getAttribute("response"));
	        return "RetryCheckId"; // 再入力画面へ
	    }

	    // データベースにIDが存在する
	    System.out.println("4");

	    Update userInfo = user.get(0);

	    System.out.println("フォームに取得したデータを飛ばす: ID = " + userInfo.getId() + ", Name = " + userInfo.getName()
	            + ", Age = " + userInfo.getAge() + ", pass =" + userInfo.getPassword1()
	            + " Start Date = " + userInfo.getStartDate() + ", End Date = " + userInfo.getEndDate());

	    m.addAttribute("user", userInfo);

	    return "UpdateForm"; // ID確認後、フォームへ遷移
	}

//	メニュー画面からの移動
	@RequestMapping("/checkUserId")
	public String checkUserId(Model m, @RequestParam("userid") String userId) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "情報更新フォーム");
		
	    // 入力されていない
	    if (userId == null || userId.isEmpty()) {
	        m.addAttribute("response", "1");
	        System.out.println("Response: " + m.getAttribute("response"));
	        return "RetryCheckId";
	    }

	    // 入力された値が数値ではない
	    int id;
	    try {
	        id = Integer.parseInt(userId);
	    } catch (NumberFormatException e) {
	        m.addAttribute("response", "2");
	        System.out.println("Response: " + m.getAttribute("response"));
	        return "RetryCheckId";
	    }

	    // IDが存在しない
	    List<Update> user = service.findUserById(id);
	    if (user.size() == 0) {
	        m.addAttribute("response", "3");
	        System.out.println("Response: " + m.getAttribute("response"));
	        return "RetryCheckId";
	    }

	    
	    // データベースにIDが存在する
	    System.out.println("4");

	    Update userInfo = user.get(0);

	    String name = userInfo.getName() != null ? userInfo.getName() : "";
	    String age = String.valueOf(userInfo.getAge());

//	    Date startDate = userInfo.getStartDate();
//	    Date endDate = userInfo.getEndDate();

	    // デバッグメッセージ
	    System.out.println("DB読みに行ったところ諸々動き出す前: " + userInfo.getStartDate()); // デバッグ用
	    System.out.println("DB読みに行ったところ諸々動き出す前: " + userInfo.getEndDate()); // デバッグ用
//
//	    // 日時を指定フォーマットの文字列に変換
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    String startDateString = startDate != null ? dateFormat.format(startDate) : "";
//	    String endDateString = endDate != null ? dateFormat.format(endDate) : "";

	    System.out.println("フォームに取得したデータを飛ばす: ID = " + userInfo.getId() + ", Name = " + name + ", Age = " + age + ","
	    		+ "pass =" + userInfo.getPassword1() + " Start Date = " + userInfo.getStartDate() + ", End Date = " + userInfo.getEndDate());
	    
	    m.addAttribute("user", userInfo);

	    return "UpdateForm";
	    
	}
	    
	
//	@RequestMapping("/UpdateFoam")
//	public String UpdateForm() {
//		return "UpdateFoam";
//	}
	
	
	@PostMapping("/UpdateForm")
	public String UpdateForm(Model m,
	        @RequestParam(value = "id", required = false) Integer id,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "age", required = false) Integer age,
	        @RequestParam(value = "password1", required = false) String password1,
	        @RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate) {
		
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "情報更新フォーム");

	    System.out.println("受信データ:");
	    System.out.println("ID: " + id);
	    System.out.println("名前: " + name);
	    System.out.println("年齢: " + age);
	    System.out.println("パスワード1: " + password1);
	    System.out.println("開始日: " + startDate);
	    System.out.println("終了日: " + endDate);

	    // `user` オブジェクトを作成してモデルに渡す（checkUserId と統一）
	    Update user = new Update(id, name, age, password1, startDate, endDate);
	    m.addAttribute("user", user); 

	    // エラーチェック
	    String errorMessages = "";

	    if (password1 != null) { // nullチェック追加
	        if (password1.length() < 8) {
	            errorMessages += errorMessages.isEmpty() ? "1" : ",1";
	        }
	        if (!password1.matches(".*[A-Z].*")) {
	            errorMessages += errorMessages.isEmpty() ? "2" : ",2";
	        }
	        if (!password1.matches(".*[a-z].*")) {
	            errorMessages += errorMessages.isEmpty() ? "3" : ",3";
	        }
	        if (!password1.matches(".*\\d.*")) {
	            errorMessages += errorMessages.isEmpty() ? "4" : ",4";
	        }
	        if (!password1.matches(".*[@#$%^&+=!].*")) {
	            errorMessages += errorMessages.isEmpty() ? "5" : ",5";
	        }
	    }

	    // エラーがある場合はフォームへ戻る（データを維持）
	    if (!errorMessages.isEmpty()) {
	        System.out.println("アラートの中身: " + errorMessages);
	        m.addAttribute("errors", errorMessages);
	        return "UpdateForm"; 
	    }
	    
//	    m.addAttribute("id", id);
//	    m.addAttribute("name", name);
//	    m.addAttribute("age", age);
//	    m.addAttribute("password", password1);
//	    m.addAttribute("startDate", startDate);
//	    m.addAttribute("endDate", endDate);

	    return "UpdateConfirm";
	}


	    
	
//	    scriptでデータを受け取ってアラートを出せるかテスト
//	    String errorMessages = "";
//	    
//	    if (password1 == null || password1.isEmpty()) {
//	    	errorMessages = "0";
//	    } else {
//	    	
//	        if (password1.length() < 8) {
//	        	errorMessages = errorMessages +"1";
//	        }
//	        
//	        if (!password1.matches(".*[A-Z].*")) {
//	        	errorMessages = errorMessages + "2";
//	        }
//
//	        if (!password1.matches(".*[a-z].*")) {
//	        	errorMessages = errorMessages + "3";
//	        }
//
//	        if (!password1.matches(".*\\d.*")) {
//	        	errorMessages = errorMessages + "4";
//	        }
//
//	        if (!password1.matches(".*[@#$%^&+=!].*")) {
//	        	errorMessages = errorMessages + "5";
//	        }
//	        
//	        System.out.println("アラートの中身: " + errorMessages);
//	        
//	        
//            m.addAttribute("errors", errorMessages);
//            
//            return "testtest";
//            
//	    }
	    

	    // もしエラーがあればフォームに戻る
//	    if (!errorMessages.isEmpty()) {
//	        m.addAttribute("errors", errorMessages);
//	        return "testtest";
//	    }

	
	
	@PostMapping("/UpdateConfirm")
	public String UpdateConfirm(Model m,
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("age") int age,
			@RequestParam("password1") String password,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate
			) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "入力情報確認");
		
		
//		日付データをストリングに変更してみる
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    String stirngStartDate = dateFormat.format(startDate);
//	    String stirngEndDate = dateFormat.format(endDate);
		
//		Update confirm = new Update(id,name,age,password,startDate,endDate);

		
//	    System.out.println("UpdateConfirmでString型に変更直後: " + startDate + "Stirng型へ" + stirngStartDate);
//	    System.out.println("UpdateConfirmでString型に変更直後: " + endDate + "Stirng型へ" + stirngEndDate);
		
	    // Updateオブジェクトを作成
	    System.out.println("Updateオブジェクトを作成する前 - startDate: " + startDate);
	    System.out.println("Updateオブジェクトを作成する前 - endDate: " + endDate);
	    
	    Update confirm = new Update(id, name, age, password, startDate, endDate);   
	    
		m.addAttribute("updateconfirm",confirm);
		
//	    m.addAttribute("stirngStartDate", startDate);
//	    m.addAttribute("stirngEndDate", endDate);
	    
	    System.out.println("フォームで入力した値の確認 - Name: " + confirm.getName());
	    System.out.println("フォームで入力した値の確認 - Age: " + confirm.getAge());
	    System.out.println("フォームで入力した値の確認 - Password: " + confirm.getPassword1());
	    System.out.println("フォームで入力した値の確認 - Start Date: " + confirm.getStartDate());
	    System.out.println("フォームで入力した値の確認 - End Date: " + confirm.getEndDate());
		
		
		return "UpdateConfirm";
	}
	
//	フォームの入力画面に入力した情報をそのまま維持して戻る
	
	@PostMapping("/ReturnToUpdateForm")
	public String ReturnToUpdateForm(Model m,
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("age") int age,
			@RequestParam("password1") String password,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate
			) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "情報更新フォーム");
		
	    System.out.println("受信データ:");
	    System.out.println("ID: " + id);
	    System.out.println("名前: " + name);
	    System.out.println("年齢: " + age);
	    System.out.println("パスワード1: " + password);
	    System.out.println("開始日: " + startDate);
	    System.out.println("終了日: " + endDate);
		
	    Update user = new Update(id, name, age, password, startDate, endDate);
	    
	    m.addAttribute("user", user); 
		
	    return "UpdateForm"; 
	    
	}
	
	@PostMapping("/UpdateComplete")
	public String UpdateComplete(Model m,
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("age") int age,
			@RequestParam("password1") String password,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "更新完了");

	    // 直接String型でデータをUpdateオブジェクトに渡す
	    Update update = new Update(id, name, age, password, startDate, endDate);

	    // サービスにデータを渡して処理
	    service.updateinsert(update);

	    return "UpdateComplete";
	}
	
	
	
	@RequestMapping("/update/backToSearch")
	public String backToSearch(Model m) {
		
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "テスト用メニュー");
		
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
  public String searchTemporary(Model m) {
	  
//		ヘッダータイトル用
		m.addAttribute("pageTitle", "テスト用メニュー");

	  
      return "SearchTemporary";
  }
  
}
