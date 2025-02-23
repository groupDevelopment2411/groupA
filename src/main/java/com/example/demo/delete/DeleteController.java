package com.example.demo.delete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//ユーザーからのリクエストを受け取りserviceクラスを呼び出してHTMLを返す
@Controller
public class DeleteController {
    @Autowired
    private DeleteService deleteService;

    
    // 削除検索画面の表示（メインメニューから）
    @GetMapping("/delete/search")
    public String showDeleteSearchForm() {
        return "delete/search";
    }
    
    // ダミーの検索結果画面の表示
    @GetMapping("/dummy/search")
    public String showDummySearchForm() {
        return "dummy/search";
    }

    
    // 社員情報削除確認ページ
    @GetMapping("/delete/confirm")
    public String showDeleteConfirm(@RequestParam(value = "ids", required = false) List<Integer> ids,
    		                        @RequestParam(value = "id", required = false) Integer id,
    								Model model) {
    	
    	List<Integer> idList = new ArrayList<>();
    	
		if (id != null) { /*単一IDの場合*/
			
			/*IDの存在CHK*/
        	if(!deleteService.employeeExists(id)) {
        		model.addAttribute("errorMessage", "指定されたIDは存在しません");
        		return "delete/search";
        	}
        	
        	idList.add(id);
        	
		} else if (ids != null && !ids.isEmpty()) { /*複数IDの場合*/
			
			idList.addAll(ids);

        }else {  /*IDが指定されてない*/

        	model.addAttribute("errorMessage", "削除するIDが選択されていません");
        	
        }
        
		System.out.println("受け取ったID:" + idList); /*チェック用なので後で消す*/
        
		List<DeleteEmployee> employees = deleteService.getEmployeesByIds(idList);
		
		if(!employees.isEmpty()) {
			
			model.addAttribute("employees",employees);
			return "delete/confirm";
			
		}else {
			
			model.addAttribute("errorMessage","該当する社員が見つかりませんでした");
			
			return "delete/search";
		}
		
    }

    
    // 社員情報を削除する
    @PostMapping("/delete/result")
    public String deleteEmployees(@RequestParam("ids") List<Integer> ids, Model model) {
        deleteService.deleteEmployeesByIds(ids);
        model.addAttribute("message", "社員の削除が完了しました。");
        return "delete/result";
    }

}
