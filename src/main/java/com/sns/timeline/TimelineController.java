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
		
		// 예전 방식
//		List<Post> posts = new ArrayList<>();
//		List<Comment> comments = new ArrayList<>();
//		comments = commentBO.getCommentList();
//		posts = postBO.getPostList();
//		model.addAttribute("comments", comments);
//		model.addAttribute("posts", posts);
		
		// CardView List를 만들어서 필요한 정보를 한번에 뿌린다.
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardViewList = timelineBO.generateCardViewList();  
				
		model.addAttribute("cardList",cardViewList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
	
	
	
	
	
	
	
}
