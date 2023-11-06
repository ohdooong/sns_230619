package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManager;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
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
	
	
}
