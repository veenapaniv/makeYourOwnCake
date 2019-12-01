package com.ucm.asp.veena.make_your_own_cake.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucm.asp.veena.make_your_own_cake.dao.LikeDao;
import com.ucm.asp.veena.make_your_own_cake.service.LikeService;
import com.ucm.asp.veena.make_your_own_cake.service.OrderService;

@Controller
public class LikeController {
	@Autowired
	LikeService likeService;

	@Autowired
	LikeDao likeDao;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/likeCake", method = RequestMethod.GET)
	public void likeCake(HttpServletResponse response, @RequestParam(value="cakeId", required=true) String cakeId, @RequestParam(value="userId", required=true) String userId) throws Exception {

		int currentLikes = likeService.getLikes(cakeId);
		currentLikes+=1;
		likeService.likeCake(cakeId, userId, currentLikes);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLikes", method = RequestMethod.GET)
	public int getCakeLikes(HttpServletResponse response, @RequestParam(value="cakeId", required=true) String cakeId, @RequestParam(value="userId", required=true) String userId) throws Exception {

		int currentLikes = likeService.getLikes(cakeId);
		return currentLikes;
		
	}
	
	@RequestMapping(value = "/reviewCake", method = RequestMethod.GET)
	public void reviewCake(HttpServletResponse response, @RequestParam(value="cakeId", required=true) String cakeId, @RequestParam(value="userId", required=true) String userId, @RequestParam(value="msg", required=true) String review) throws Exception {

		likeService.saveReview(cakeId, userId, review);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllReviews", method = RequestMethod.GET)
	public List<Map<String, Object>> getAllReviews(HttpServletResponse response, @RequestParam(value="cakeId", required=true) String cakeId) throws Exception {

		List<Map<String, Object>> reviewsArray = new ArrayList<Map<String, Object>>();
		reviewsArray = likeService.getAllReviews(cakeId);
		return reviewsArray;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSellerId", method = RequestMethod.GET)
	public String getSellerId(@RequestParam("cid") String cid ) {
		String sid = orderService.getSellerId(cid);
		return sid;
		 
	}	
	
	@ResponseBody	
	@RequestMapping(value = "/getSellerName", method = RequestMethod.GET)
	public String getSellerName(@RequestParam("userId") String userId ) {
		return orderService.getSellerName(userId);
	}	
	

}
