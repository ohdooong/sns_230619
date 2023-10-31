package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 회원가입 아이디 중복확인 API
	 * @param memberId
	 * @return
	 */
	@PostMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String memberId) {
		
		// db 조회
		UserEntity user = userBO.getUserEntityByMemberId(memberId);
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		
		if (user == null) {
			result.put("code", 200);
			result.put("isDuplicated", false);
		} else {
			result.put("code", 400);
			result.put("isDuplicated", true);
		}
		
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param memberId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String memberId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		//password 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		//db insert
		Integer id = userBO.addUser(memberId, hashedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if (id == null) {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 하는데에 실패했습니다.");
		} else {
			result.put("code", 200);
			result.put("result", "성공");
		}
		return result;
		
	}
	
	
}
