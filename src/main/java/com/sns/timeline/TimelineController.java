package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.Post;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline-view")
	public String timelineView(HttpSession session,Model model) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/user/sign-in-view";
		}
		
		List<Post> posts = new ArrayList<>();
		
		posts = postBO.getPostList();
		
		
		model.addAttribute("posts", posts);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
	
	
	
}
