package com.example.demo.update;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UpdateController {
	
//	前のログインページで入力しているIDパスワードですでに登録されている各種情報を表示する
//	変更点があった場合更新箇所のみ更新していいか確認してから更新する
//	IDは変えられなようにする
	
	@RequestMapping("/UpdateFoam")
	public String UpdateForm() {
		return "UpdateFoam";
	}
	
	
	
}
