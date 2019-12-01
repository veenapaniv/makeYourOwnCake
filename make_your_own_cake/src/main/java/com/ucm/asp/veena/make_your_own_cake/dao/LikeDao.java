package com.ucm.asp.veena.make_your_own_cake.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ucm.asp.veena.make_your_own_cake.model.Order;

public interface LikeDao {
	public void likeCake(String cakeId, String userId, int likes);
	public int getLikes(String cakeId);
	public void saveReview(String cakeId, String userId, String review);
	public List<Map<String, Object>> getAllReviews(String cakeId);

}
