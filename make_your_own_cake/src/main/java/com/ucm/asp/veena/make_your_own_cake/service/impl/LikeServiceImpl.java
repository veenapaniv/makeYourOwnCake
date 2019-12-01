package com.ucm.asp.veena.make_your_own_cake.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucm.asp.veena.make_your_own_cake.dao.LikeDao;
import com.ucm.asp.veena.make_your_own_cake.service.LikeService;



@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	LikeDao likeDao;

	@Override
	public void likeCake(String cakeId, String userId, int likes) {
		// TODO Auto-generated method stub
		likeDao.likeCake(cakeId, userId, likes);
	}

	@Override
	public int getLikes(String cakeId) {
		return likeDao.getLikes(cakeId);
	}

	@Override
	public void saveReview(String cakeId, String userId, String review) {
		// TODO Auto-generated method stub
		likeDao.saveReview(cakeId, userId, review);
	}

	@Override
	public List<Map<String, Object>> getAllReviews(String cakeId) {
		return likeDao.getAllReviews(cakeId);
	}
}
