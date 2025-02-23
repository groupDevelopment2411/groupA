package com.example.demo.delete;

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
    public String showDeleteConfirm(@RequestParam("ids") List<Integer> ids, Model model) {
    	System.out.println("受け取ったID: " + ids); // チェック用後で消す
    	
        if (ids == null || ids.isEmpty()) {
            model.addAttribute("errorMessage", "削除する社員が選択されていません。");
            return "dummy/search";
        }
        
        List<DeleteEmployee> employees = deleteService.getEmployeesByIds(ids);
        
        if (!employees.isEmpty()) {
            model.addAttribute("employees", employees);
            return "delete/confirm";
        } else {
            model.addAttribute("errorMessage", "該当する社員が見つかりませんでした。");
            return "dummy/search";
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
