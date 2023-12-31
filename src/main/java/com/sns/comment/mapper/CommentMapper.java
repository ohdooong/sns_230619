package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.domain.Comment;

@Repository
public interface CommentMapper {
	
	public void insertComment (
			@Param("postId") int postId,
			@Param("comment")  String comment,
			@Param("userId") int userId);
	
	public void deleteCommentById(int commentId);
	
	public void deleteByPostId(int postId);
	
	public List<Comment> selectCommentList();
	
	public List<Comment> selectCommentListByPostId(int postId);
}
