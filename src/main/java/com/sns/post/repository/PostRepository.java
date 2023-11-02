package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{
	
}
