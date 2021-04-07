package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

 	@GetMapping("/form")
 	// inquiryForm 入力した値やバリデーションエラーメッセージを取得
 	// @ModelAttributeはフラッシュスコープを使うための記述
	public String form(InquiryForm inquiryForm, Model model, @ModelAttribute("complete")String complete) {      // フォームクラスに引数を加えるとフォームの入力欄と紐付けられて自動で初期化してくれる
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
 	
 	// 完了処理
 	@PostMapping("/complete")
 	// RedirectAttributes flashスコープを使用するため　ページ感を飛び越えてデータを保持するセッション
 	public String complete(@Validated InquiryForm inquiryForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
 		if(result.hasErrors()) {
 			model.addAttribute("title", "InquiryForm");
 			return "inquiry/form";
 		}
 		// registered! セッションが一度表示されると削除される
 		redirectAttributes.addFlashAttribute("complete", "Registered!");
 		return "redirect:/inquiry/form";
 	}
}
