package com.example.demo.delete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.LoginEmployee;

import jakarta.servlet.http.HttpSession;

//ユーザーからのリクエストを受け取りserviceクラスを呼び出してHTMLを返す
@Controller
public class DeleteController {
    @Autowired
    private DeleteService deleteService;

    
    // 削除検索画面の表示（メインメニューから）
    @GetMapping("/delete/search")
    public String showDeleteSearchForm(@RequestParam(value = "id", required = false) String id, Model model) {
       if(id != null && !id.isEmpty()) {
    		model.addAttribute("searchId", id); // IDを保持して削除検索画面に戻る
       }
        return "delete/search";
    }    
    
    // ダミーの検索結果画面の表示
    @GetMapping("/dummy/search")
    public String showDummySearchForm() {
        return "dummy/search";
    }
    
    
    // 社員情報削除確認ページ
    @GetMapping("/delete/confirm")
    public String showDeleteConfirm(@RequestParam(value = "ids", required = false)List<Integer> ids,
    								@RequestParam(value = "id", required = false) String id,
    								@RequestParam(value = "source", required = false) String source,
    								HttpSession session,
    								Model model) {

    	LoginEmployee loginUser = (LoginEmployee) session.getAttribute("loginUser");
    	Integer loggedInUserId = loginUser != null ? loginUser.getId() : null;
    	
    	List<Integer> idList = new ArrayList<>();
    	List<String> errorMsgs = new ArrayList<>();
    	

		if (id != null && !id.isEmpty()) { /*単一IDの場合*/
			if(!id.matches("\\d+")) {
				errorMsgs.add("IDは半角数字で入力してください");
				
			} else {
				int numId = Integer.parseInt(id);
				if(!deleteService.employeeExists(numId)) {
					errorMsgs.add("指定されたID:"+ numId + "は存在しません");
				} 
				
				if(loggedInUserId != null && numId == loggedInUserId.intValue()){
					errorMsgs.add("自分自身のID:" + numId + "は削除できません");
				}
				
				if(errorMsgs.isEmpty()) {
					idList.add(numId);
					
				}
			}
		}
		
		else if (ids != null && !ids.isEmpty()) { /*複数IDの場合*/
			for(Integer targetId : ids) {
				
				if(!deleteService.employeeExists(targetId)) {
					errorMsgs.add("指定されたID:"+ targetId + "は存在しません");
				}

				if(targetId.equals(loggedInUserId)) {
					errorMsgs.add("自分自身のID:" + targetId + "は削除できません");
				}
			}
			if(errorMsgs.isEmpty()) {
				idList.addAll(ids);							
			}
		}
		
		else { /*IDが指定されていない場合*/
			errorMsgs.add("削除するIDが指定されていません");
			
		}
		
//			エラーがある場合元の画面に
		if(!errorMsgs.isEmpty()) {
			model.addAttribute("errorMsgList", errorMsgs);
			return source != null && source.equals("dummy") ? "dummy/search" : "delete/search";
		}
		
	
		System.out.println("受け取ったID:" + idList); /*チェック用*/
		System.out.println("エラーメッセージリスト:" + errorMsgs);
		
		List<DeleteEmployee> employees = deleteService.getEmployeesByIds(idList);
    	
//    	社員データがないIDの検索がされた場合のエラー
               
        if (employees.isEmpty()) {
            errorMsgs.add("該当する社員が見つかりませんでした。");
        	model.addAttribute("errorMsgList", errorMsgs);
            return source != null && source.equals("dummy") ? "dummy/search" : "delete/search";
        }
        
        model.addAttribute("employees", employees);
		model.addAttribute("source", source); /*source判定*/
		model.addAttribute("searchId", id); /*IDをキープして戻る*/
		
        return "delete/confirm";
        
    }

    
    // 社員情報を削除する
    @PostMapping("/delete/result")
    public String deleteEmployees(@RequestParam("ids") List<Integer> ids, Model model) {
        deleteService.deleteEmployeesByIds(ids);
        model.addAttribute("message", "社員の削除が完了しました。");
        return "delete/result";
    }

}
