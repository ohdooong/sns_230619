package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글 쓰기 API
	 * @param postId
	 * @param comment
	 * @param session
	 * @return
	 */
	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment,
			HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		//DB INSERT
		commentBO.addComment(postId, comment, userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 여부 확인
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code",500);
			result.put("errorMessage", "로그인이 되지 않은 사용자 입니다.");
			return result;
		}
		
		// 삭제
		commentBO.deleteCommentById(commentId);
		
		// 응답값 => 위에서 오류 발생 안하면
		result.put("code", 200);
		result.put("result", "success");
		
		return result;
	}
	
	
	
	
}
