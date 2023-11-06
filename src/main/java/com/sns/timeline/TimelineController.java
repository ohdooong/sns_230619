package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private TimelineBO timelineBO;
	
	
	
	@GetMapping("/timeline-view")
	public String timelineView(HttpSession session,Model model) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardViewList = timelineBO.generateCardViewList();  
				
		
		
		
//		List<Post> posts = new ArrayList<>();
//		List<Comment> comments = new ArrayList<>();
		
		
		
//		comments = commentBO.getCommentList();
//		posts = postBO.getPostList();
		
//		model.addAttribute("comments", comments);
//		model.addAttribute("posts", posts);
				
				
				
		model.addAttribute("cardList",cardViewList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
	
	
	
	
	
	
	
}
