package com.sns.comment;

import com.sns.comment.domain.Comment;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentView {
	
	// 댓글 내용, 댓글 쓴 사람
	private Comment comment;
	
	private UserEntity user; 
	
}
