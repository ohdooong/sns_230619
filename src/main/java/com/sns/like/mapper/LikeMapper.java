package com.sns.like.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.domain.Like;

@Repository
public interface LikeMapper {
	
	public Like likeCheckByPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public void insertLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public void deleteLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public void deleteByPostId(int postId);
	
	public int selectLikeCountByPostId(int postId);
	
	public int selectLikeBooleanByUserId(int userId);
}
