package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;

@Controller
public class TestController {
	
	@Autowired
	private PostMapper postMapper;
	
	//URL : http://localhost8050/test1
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "Hello World";
	}
	
	//URL : http://localhost8050/test2
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 1);
		map.put("c", 1);
		
		return map;
	}
	
	//URL : http://localhost8050/test3
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	// 4. DB연동 reponse body -> json
	// SnsApplication DB 설정 안 보는 설정 제거
	// DatabaseConfig 클래스 추가
	// application.yml DB 접속 정보 추가
	// logback-spring.xml 추가
	
	//URL : http://localhost:8050/test4
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postMapper.selectPost();
	}
	
}
