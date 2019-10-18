package com.ucm.asp.veena.make_your_own_cake.dao;

import java.util.List;
import java.util.Map;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;

public interface OrderDao {
	public List<Cake> getAllCakes();
	public String getCakeID(String cakeName);
	public List<Order> getAllOrders();
	public void insertOrder(Order order);
	public List<Order> getNewOrders();
}
