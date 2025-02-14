package com.example.demo.delete;

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
    private DeleteService employeeService;

    // 削除検索画面の表示（メインメニューから）
    @GetMapping("/delete/search")
    public String showDeleteSearchForm() {
        return "delete/search";
    }

    // 削除検索処理（社員ID入力⇒削除確認画面）
    @PostMapping("/delete/search")
    public String searchEmployeeForDelete(@RequestParam("id") int id, Model model) {
        DeleteEmployee deleteEmployee = employeeService.getEmployeeById(id);
		if (deleteEmployee != null) { /*存在する場合*/
            model.addAttribute("employee", deleteEmployee);
			return "delete/confirm"; /*確認画面へ*/
		} else { /*存在しない場合*/
            model.addAttribute("errorMessage", "該当する社員が見つかりませんでした。");
			return "delete/search";	/*削除検索ページへ*/
        }
    }

    // 削除確認ページ（検索ページから）
    @GetMapping("/delete/confirm")
    public String showDeleteConfirm(@RequestParam("id") int id, Model model) {
        DeleteEmployee deleteEmployee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", deleteEmployee);
        return "delete/confirm";
    }

    // 削除処理
    @PostMapping("/delete/result")
    public String deleteEmployee(@RequestParam("id") int id, Model model) {
    	//RequestParamからidを取得し、Modelオブジェクトでビューにデータを渡す
        employeeService.deleteEmployeeById(id);
        model.addAttribute("message", "社員の削除が完了しました。");
        return "delete/result";
    }
}
