package com.ucm.asp.veena.make_your_own_cake.dao;

import java.util.List;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;

public interface CakeDao {
	
	public void insertCake(Cake cake);

	List<Cake> getAllCakes();
	
	public void deleteCake(String cakeId);
	
	public Cake getCakeById(String Id);
	
	public void updateCake(Cake cake);

}
