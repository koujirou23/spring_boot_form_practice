package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {
	
	private final JdbcTemplate jdbcTemplate;
	
	// Dependency Injection インスタンス化されたオブジェクトを自動で渡してくれる機能
	@Autowired
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@GetMapping("test")
	public String test(Model model) {
		String sql = "SELECT id,name,email FROM inquiry WHERE id = 1";    // inquiry テーブルのid 1からデータを取り出す
		Map<String, Object>map = jdbcTemplate.queryForMap(sql);              //  quetyForMapでデータベースの1行分のデータを取得する 引数はsqlの文字列
		model.addAttribute("title", "Inquiry Form");                        //   Map型のインスタンスを取得　キーはStringでテーブルのカラム名、オブジェクトはテーブルの値
		model.addAttribute("name",map.get("name"));
		model.addAttribute("email",map.get("email"));                      //    map.get(カラム名)でテーブルの値を取得 HTMLに埋め込むためmodelに渡している
		return "test";
	}

}
