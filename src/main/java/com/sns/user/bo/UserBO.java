package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.mapper.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserEntityByMemberId(String memberId) {
		return userRepository.findByMemberId(memberId);
	}
	
	public Integer addUser(String memberId, String password, String name, String email) {
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.memberId(memberId)
				.password(password)
				.userName(name)
				.email(email)
				.build());
		
		return userEntity == null ? null : userEntity.getId();
	}
	
	
}
