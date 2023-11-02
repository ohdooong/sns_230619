package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManager;
import com.sns.post.entity.Post;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManager filemanager;
	
	public List<Post> getPostList() {
		return postRepository.findAll();
	}
	
	public void addPost(int userId, String userLoginId, String contents, MultipartFile file) {
		String imagePath;
		
		if (file != null) {
			imagePath = 
		}
		
	}
	
	
}
