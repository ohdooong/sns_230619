package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.CommentView;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// View 용
// 글 하나와 Mapping
@ToString
@Data
public class CardView {
	
	// 글 1개
	private PostEntity post;
	
	// 글쓴이 정보
	private UserEntity user;
	
	// 댓글들  :  글에 해당하는 댓글들과 댓글단 사용자 정보
	private List<CommentView> commentList;
	
	// 좋아요 개수
	private int likeCount;
	
	// 좋아요 클릭했는지 여부
	private boolean filledLike;
	
}
