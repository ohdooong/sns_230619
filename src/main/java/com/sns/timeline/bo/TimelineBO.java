package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.CommentView;
import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// input : X  
	// output : List<CardView>
	
	public List<CardView> generateCardViewList() {
		
		// 카드 정보 넣을 리스트 초기화
		List<CardView> cardViewList = new ArrayList<>(); // []
		
		// 글 목록을 가져온다. List<Post>
		List<PostEntity> postList = postBO.getPostList();
		
		// 글 목록 반복문 순회, 글쓴 유저정보 삽입
		Iterator<PostEntity> iter = postList.iterator();
		while (iter.hasNext()) {
			
			CardView card = new CardView();
			PostEntity post = iter.next();    // iterator로 반복할땐 이렇게 담아놓자
			
			// 글 정보 세팅
			card.setPost(post);
			
			// 글쓴이 정보 세팅
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			card.setUser(user);
			
			// 좋아요
			
			// 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewList(post.getId());
			card.setCommentList(commentList);
			
			// ★★★★★★★★★★★★★★★★★ 마지막에 CardViewList에 Card를 넣는다.  !!!
			cardViewList.add(card);
		}
		
		// postEntity => CardView     =>   cardViewList에 담는다.
		return cardViewList;
	}
	
}
