package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    @GetMapping("/mainmenu")
    public String showMainMenuPage() {
        return "mainmenu";
    }

    @GetMapping("/RegistrationFoam")//スラックで共有されたスペルにしています。
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/UpdateLogin")
    public String showUpdatePage(Model model) {
        // selectedIdが渡っていない場合も考慮してnullチェック
        if (!model.containsAttribute("selectedId")) {
            model.addAttribute("selectedId", "ID未選択");
        }

        return "update";
    }
    
    // POST送信を受け取った後にリダイレクトして、IDを渡す
    @PostMapping("/UpdateLogin")
    public String processUpdatePage(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {

        // RedirectAttributesで、selectedIdとして渡す
        redirectAttributes.addFlashAttribute("selectedId", id);

        // GETリクエストにリダイレクト（リロード時のフォーム再送信を防止するPRGパターン）
        return "redirect:/UpdateLogin";
    }
}
