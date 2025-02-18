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


    // 社員情報削除確認ページ（自分の検索ページから）
    @GetMapping("/delete/confirm")
    public String showDeleteConfirm(@RequestParam("id") int id, Model model) {
    	
        DeleteEmployee deleteEmployee = employeeService.getEmployeeById(id);

        if (deleteEmployee != null) {
            model.addAttribute("employee", deleteEmployee);
            return "delete/confirm";
        } else {
            model.addAttribute("errorMessage", "該当する社員が見つかりませんでした。");
            return "delete/search";
        }
        
    }

    // 社員情報削除確認ページ（他の検索ページから:複数）
    @GetMapping("/delete/confirmMultiple")
    public String showDeleteConfirmMultiple(@RequestParam("id") List<Integer> ids, Model model) {

    	List<DeleteEmployee> employees = employeeService.getEmployeeByIds(ids);
        
        if (!employees.isEmpty()) {
            model.addAttribute("employees", employees);
            return "delete/confirmMultiple";
        } else {
            model.addAttribute("errorMessage", "該当する社員が見つかりませんでした。");
            return "delete/search";
        }

    }


    // 社員情報を削除する
    @PostMapping("/delete/result")
    public String deleteEmployee(@RequestParam("id") int id, Model model) {
        employeeService.deleteEmployeeById(id);
        model.addAttribute("message", "社員の削除が完了しました。");
        return "delete/result";
    }

    // 社員情報を削除する(複数)
    @PostMapping("/delete/resultMultiple")
    public String deleteEmployees(@RequestParam("id") List<Integer> ids, Model model) {
        employeeService.deleteEmployeeByIds(ids);
        model.addAttribute("message", "選択した社員の削除が完了しました。");
        return "delete/result";
    }
}
