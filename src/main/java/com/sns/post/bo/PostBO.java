package com.sns.post.bo;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManager;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManager filemanager;
	
	public List<PostEntity> getPostList() {
		return postRepository.findAll();
	}
	
	public void addPost(int userId, String userLoginId, String contents, MultipartFile file) {

		String imagePath = null;
		imagePath = filemanager.saveFile(userLoginId, file);
		
		postRepository.save(PostEntity.builder()
				.userId(userId)
				.contents(contents)
				.imagePath(imagePath)
				.build());
	}
	
	// 글 삭제 
	@Transactional
	public void deletePostByPostIdUserId(int userId ,int postId) {
		// 기존 글   =>  To delete image
		PostEntity postEntity = postRepository.findById(postId).orElse(null);	
		
		if (postEntity == null) {
			log.info("[글 삭제] : postEntity is null  =>  postId={}",postId);
			return;
		}
		
		// db글 삭제
		postRepository.delete(postEntity);
		
		// db댓글 삭제
		commentBO.deleteByPostId(postId);
		
		// db좋아요 삭제
		likeBO.deleteByPostId(postId);
	}
	
}
