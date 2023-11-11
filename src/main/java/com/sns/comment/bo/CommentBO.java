package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.CommentView;
import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {
	
	@Autowired
	private UserBO userBO;    // 절대로 남의 Repository부르면 안됨    =>   에러발생 200%
	
	@Autowired
	private CommentMapper commentMapper;
	
	public void addComment (int postId, String comment, int userId) {
		commentMapper.insertComment(postId, comment, userId);
	}
	
	public List<Comment> getCommentList() {
		return commentMapper.selectCommentList();
	}
	
	// input: 삭제할 댓글번호      output: X
	public void deleteCommentById(int commentId) {
		commentMapper.deleteCommentById(commentId);
	}
	
	public void deleteByPostId(int postId) {
		commentMapper.deleteByUserIdPostId(postId);
	}
	
	
	// input: 글번호
	// output: List<CommentView>
	public List<CommentView> generateCommentViewList(int postId) {
		
		//List<Comment>   =>   List<CommentView>
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글에 해당하는 댓글들 목록 가져오기 List<Comment>
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		
		// 반복문 순회
		for(Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 내용 담기
			commentView.setComment(comment);
			
			// 댓글 쓴이 내용 담기
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
		
	}
	
}
