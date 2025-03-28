//ポストで飛ぶか確認するためのコントローラー

package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteCheckController {

    // GETリクエストで選択されたIDを表示する.万が一ポストで飛ばなかった場合もあるけど、今回そこまで考慮せず。
    @GetMapping("/delete/search")
    public String showDeletePage() {
        return "delete"; // delete.html を表示
    }

    // POSTリクエストで送信されたIDを受け取る
    @PostMapping("/delete/search")
    public String confirmDelete(@RequestParam(value = "selectedIds", required = false) List<String> selectedIds, Model model) {
        if (selectedIds == null || selectedIds.isEmpty()) {
            model.addAttribute("message", "削除対象が選択されていません");
        } else {
            model.addAttribute("selectedIds", selectedIds);
        }
        return "delete"; // delete.html にデータを渡して表示
    }
}
