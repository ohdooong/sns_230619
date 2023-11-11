package com.sns.like.bo;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.domain.Like;
import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	
	// input : postId	output:X
	public String likeCheckByPostId(int userId,int postId) {
		
		Like like = new Like();
		like = likeMapper.likeCheckByPostId(userId,postId);
		
		if (like == null) {
			likeMapper.insertLikeByUserIdPostId(userId, postId);
			return "insert";
		} else {
			likeMapper.deleteLikeByUserIdPostId(userId, postId);
			return "delete";
		}
	}
	
	public int getLikeCountByPostId (int postId) {
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
	public int getLikeBooleanByUserId(
			int userId,
			int postId) {
		return likeMapper.selectLikeBooleanByUserId(userId);
	}
	
	public void deleteByPostId(int postId) {
		likeMapper.deleteByPostId(postId);
	}
	
}
