package com.sns.post.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	
	public List<Map<String, Object>> selectPost();
	
	
}
