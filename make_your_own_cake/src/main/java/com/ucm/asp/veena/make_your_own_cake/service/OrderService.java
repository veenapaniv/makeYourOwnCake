package com.ucm.asp.veena.make_your_own_cake.service;

import java.util.List;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;

public interface OrderService {
	
	public List<Cake> getAllCakes();
	public String getCakeID(String cakeName);
	public List<Order> getAllOrders();
	public void insertOrder(Order order);
}
