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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "ageFrom", required = false) String ageFrom,
                         @RequestParam(value = "ageTo", required = false) String ageTo,
                         @RequestParam(value = "startDateFrom", required = false) String startDateFrom,
                         @RequestParam(value = "startDateTo", required = false) String startDateTo,
                         @RequestParam(value = "endDateFrom", required = false) String endDateFrom,
                         @RequestParam(value = "endDateTo", required = false) String endDateTo,
                         Model model,
                         HttpSession session) {  // ← セッションを受け取る

        // 🔹 セッションからログインユーザー情報を取得
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

        // バリデーションチェック
        String errorMessage = validateInput(id, ageFrom, ageTo, startDateFrom, startDateTo, endDateFrom, endDateTo);
        if (!errorMessage.isEmpty()) {
            model.addAttribute("error", errorMessage);
            return "search";
        }

        List<Employee> employees = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
        List<Object> params = new ArrayList<>();

        try {
            if (id != null && !id.isEmpty()) {
                sql.append(" AND id = ?");
                params.add(Integer.parseInt(id));
            }
            if (name != null && !name.isEmpty()) {
                sql.append(" AND name LIKE ?");
                params.add("%" + name + "%");
            }
            if (ageFrom != null && !ageFrom.isEmpty()) {
                sql.append(" AND age >= ?");
                params.add(Integer.parseInt(ageFrom));
            }
            if (ageTo != null && !ageTo.isEmpty()) {
                sql.append(" AND age <= ?");
                params.add(Integer.parseInt(ageTo));
            }
            if (startDateFrom != null && !startDateFrom.isEmpty()) {
                sql.append(" AND start_date >= ?");
                params.add(startDateFrom);
            }
            if (startDateTo != null && !startDateTo.isEmpty()) {
                sql.append(" AND start_date <= ?");
                params.add(startDateTo);
            }
            if (endDateFrom != null && !endDateFrom.isEmpty()) {
                sql.append(" AND end_date >= ?");
                params.add(endDateFrom);
            }
            if (endDateTo != null && !endDateTo.isEmpty()) {
                sql.append(" AND end_date <= ?");
                params.add(endDateTo);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "数値の入力に誤りがあります。");
            return "search";
        }

        // データ取得処理
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) params.get(i));
                } else {
                    stmt.setString(i + 1, (String) params.get(i));
                }
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
            e.printStackTrace();
            model.addAttribute("error", "データベースエラーが発生しました。");
            return "search";
        }

        model.addAttribute("employees", employees);
        return "search";
    }

    private String validateInput(String id, String ageFrom, String ageTo, String startDateFrom, String startDateTo, String endDateFrom, String endDateTo) {
        StringBuilder errorMessage = new StringBuilder();

        if (id != null && !id.isEmpty()) {
            try {
                Integer.parseInt(id);
            } catch (NumberFormatException e) {
                errorMessage.append("社員IDは数値のみ許可されています。\n");
            }
        }

        
        if (ageFrom != null && !ageFrom.isEmpty() && ageTo != null && !ageTo.isEmpty()) {
            try {
                int from = Integer.parseInt(ageFrom);
                int to = Integer.parseInt(ageTo);
                if (from > to) {
                    errorMessage.append("開始年齢は終了年齢以下でなければなりません。\n");
                }
            } catch (NumberFormatException e) {
                errorMessage.append("年齢は数値のみ許可されています。\n");
            }
        }

        // 日付フォーマットチェック
        if (!isValidDateFormat(startDateFrom) || !isValidDateFormat(startDateTo)) {
            errorMessage.append("開始日は yyyy/MM/dd の形式で入力してください。\n");
        }
        if (!isValidDateFormat(endDateFrom) || !isValidDateFormat(endDateTo)) {
            errorMessage.append("終了日は yyyy/MM/dd の形式で入力してください。\n");
        }

        return errorMessage.toString();
    }

    private boolean isValidDateFormat(String date) {
        if (date == null || date.isEmpty()) {
            return true;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
