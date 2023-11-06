package com.sns.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByMemberId(String memberId);
	
	public UserEntity findByMemberIdAndPassword (String memberId, String password);
	
	public List<UserEntity> findAll();
	
}
