package com.example.demo;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {

    @GetMapping("/mainmenu")
    public String mainMenu(HttpSession session, Model model) {
        // セッションからユーザー名を取得
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "ゲスト"; // 未ログイン時のデフォルト値
        }

        String loginTime = (String) session.getAttribute("loginTime");
        if (loginTime == null) {
            loginTime = "不明"; // 未ログイン時のデフォルト値
        }

        model.addAttribute("username", username);
        model.addAttribute("loginTime", loginTime);
        return "mainmenu";
    }
}


//仮表示のときのコード
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class MainMenuController {
//
//    @GetMapping("/mainmenu")
//    public String mainMenu(Model model) {
//        // サンプルデータとしてユーザー名とログイン日時を追加
//        String username = "John Doe"; // ここは実際のログインユーザー名に置き換え
//        String loginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//
//        
//        //将来的にログイン機能と連携する場合は、セッション情報から取得する予定
//        //Spring Securityを導入していれば @AuthenticationPrincipal を使って取得って感じ(｀・ω・´)
//        
//        model.addAttribute("username", username);
//        model.addAttribute("loginTime", loginTime);
//        return "mainmenu";
//    }
//}
