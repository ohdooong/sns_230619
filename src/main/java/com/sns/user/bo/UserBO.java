package com.sns.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserEntityByMemberId(String memberId) {
		return userRepository.findByMemberId(memberId);
	}
	public UserEntity getUserEntityByMemberIdPassword (String memberId, String password) {
		return userRepository.findByMemberIdAndPassword(memberId, password);
	}
	
	public UserEntity getUserEntityById (int userId) {
		return userRepository.findById(userId).orElse(null); //optional문법 null처리
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
	
	
	
	
	public List<UserEntity> getUserList() {
		return userRepository.findAll();
	}
	
	
	
	
}
