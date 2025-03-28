package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.SearchRequest;

@Controller
public class SearchController {

    //  検索フォームの初期表示（GET）
    @GetMapping("/search")
    public String showSearchPage(Model model, HttpSession session) {
        if (!model.containsAttribute("searchRequest")) {
            model.addAttribute("searchRequest", new SearchRequest());
        }

        //  セッション情報を取得
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "ゲスト";
        }
        String loginTime = (String) session.getAttribute("loginTime");
        if (loginTime == null) {
            loginTime = "不明";
        }

        model.addAttribute("username", username);
        model.addAttribute("loginTime", loginTime);

        return "search";
    }

    //  検索処理（POST）
    @PostMapping("/search")
    public String search(@ModelAttribute SearchRequest searchRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        List<Employee> employees = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
        List<Object> params = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);

        try {
            if (searchRequest.getId() != null) {
                sql.append(" AND id = ?");
                params.add(searchRequest.getId());
            }

            if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
                String name = searchRequest.getName().replaceAll("[ 　]", "");
                sql.append(" AND REPLACE(REPLACE(name, ' ', ''), '　', '') LIKE ?");
                params.add("%" + name + "%");
            }
            if (searchRequest.getAgeFrom() != null) {
                sql.append(" AND age >= ?");
                params.add(searchRequest.getAgeFrom());
            }
            if (searchRequest.getAgeTo() != null) {
                sql.append(" AND age <= ?");
                params.add(searchRequest.getAgeTo());
            }

            // 日付の形式と存在チェックを厳密に行う
            if (searchRequest.getStartDateFrom() != null && !searchRequest.getStartDateFrom().isEmpty()) {
                sdf.parse(searchRequest.getStartDateFrom()); // 存在しない日付はここでエラー
                sql.append(" AND start_date >= ?");
                params.add(searchRequest.getStartDateFrom());
            }
            if (searchRequest.getStartDateTo() != null && !searchRequest.getStartDateTo().isEmpty()) {
                sdf.parse(searchRequest.getStartDateTo());
                sql.append(" AND start_date <= ?");
                params.add(searchRequest.getStartDateTo());
            }
            if (searchRequest.getEndDateFrom() != null && !searchRequest.getEndDateFrom().isEmpty()) {
                sdf.parse(searchRequest.getEndDateFrom());
                sql.append(" AND end_date >= ?");
                params.add(searchRequest.getEndDateFrom());
            }
            if (searchRequest.getEndDateTo() != null && !searchRequest.getEndDateTo().isEmpty()) {
                sdf.parse(searchRequest.getEndDateTo());
                sql.append(" AND end_date <= ?");
                params.add(searchRequest.getEndDateTo());
            }
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("error", "日付の形式が正しくないか、存在しない日付が入力されています。");
            return "redirect:/search";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "検索条件の入力に誤りがあります。");
            return "redirect:/search";
        }

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("password"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("department")
                ));
            }
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("error", "データベースエラーが発生しました。");
            return "redirect:/search";
        }

        session.setAttribute("searchResults", employees);
        redirectAttributes.addFlashAttribute("searchRequest", searchRequest);
        redirectAttributes.addFlashAttribute("username", session.getAttribute("username"));
        redirectAttributes.addFlashAttribute("loginTime", session.getAttribute("loginTime"));

        return "redirect:/search/results";
    }

 // 検索結果ページの表示（GET）
    @GetMapping("/search/results")
    public String showSearchResults(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Employee> employees = (List<Employee>) session.getAttribute("searchResults");

        if (employees == null) {
            model.addAttribute("error", "検索結果が見つかりませんでした。");
            employees = new ArrayList<>(); // 空リストをセットしておく
        }

        model.addAttribute("employees", employees);

        // フォーム再表示用にSearchRequestを必ず追加する ※更新押したときにエラー起こさないように
        if (!model.containsAttribute("searchRequest")) {
            model.addAttribute("searchRequest", new SearchRequest());
        }

        // ログイン情報をモデルにセット
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "ゲスト";
        }
        String loginTime = (String) session.getAttribute("loginTime");
        if (loginTime == null) {
            loginTime = "不明";
        }

        model.addAttribute("username", username);
        model.addAttribute("loginTime", loginTime);

        return "search";
    }
    
 // 検索結果ページで選択した社員IDを更新画面にPOST送信する処理
    @PostMapping("/search/toUpdate")
    public String toUpdatePage(@ModelAttribute("id") Integer id, RedirectAttributes redirectAttributes) {
        // 送信された社員IDをRedirectAttributesにセット
        redirectAttributes.addFlashAttribute("selectedId", id);

        // updateページにリダイレクト
        return "redirect:/update";
    }




}