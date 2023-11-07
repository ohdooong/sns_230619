package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;


@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	
	
	// GET :    /like?postId=13			@RequestParam("postId")
	// GET :    /like/13			    @PathVariable
	@RequestMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable int postId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		
		// 로그인 여부 확인   =>   좋아요는 로그인해야 누를 수 있음
		Integer userId = (Integer) session.getAttribute("userId");
		
		if(userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 하십시오.");
			return result;
		}
		
		// 통과 => BO호출  =>  like 여부 체크
		String status = likeBO.likeCheckByPostId(userId, postId);
		
		// 응답값 리턴
		if (status == "insert") {
			result.put("code", 200);
			result.put("result", "삽입 완료");
			return result;
		}
		
		result.put("code", 300);
		result.put("result", "삭제 완료");
		
		return result;
	}
	
	
}
