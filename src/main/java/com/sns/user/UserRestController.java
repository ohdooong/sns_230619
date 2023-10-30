package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String memberId) {
		
		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(memberId);
		
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
}
