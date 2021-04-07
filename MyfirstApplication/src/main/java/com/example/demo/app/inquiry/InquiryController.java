package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

 	@GetMapping("/form")
 	// inquiryForm 入力した値やバリデーションエラーメッセージを取得
	public String form(InquiryForm inquiryForm, Model model) {      // フォームクラスに引数を加えるとフォームの入力欄と紐付けられて自動で初期化してくれる
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}
 	
 	// 戻るボタンを押したときの画面遷移
 	@PostMapping("/form")
	public String formGoBack(InquiryForm inquiryForm, Model model) {      
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}
 	
 	// Postでなにかデータを送信したときのみアクセスするページ
 	@PostMapping("/confirm")
 	// @validated inquiryFormにバリデーションがかかる
 	// @BindingResult バリデーションの結果が入る
 	public String confirm(@Validated InquiryForm inquiryForm, BindingResult result, Model model) {
 		// バリデーションエラーが有ったときの処理
 		if(result.hasErrors()) {
 			model.addAttribute("title", "Inquiry Form");
 		return "inquiry/form";
 		}
 		// エラーが無かった場合
 		model.addAttribute("title", "Confirm Page");
 		return "inquiry/confirm";
	}
}
