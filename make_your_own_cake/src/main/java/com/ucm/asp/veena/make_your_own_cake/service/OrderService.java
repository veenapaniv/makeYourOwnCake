package com.ucm.asp.veena.make_your_own_cake.service;

import java.util.List;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;

public interface OrderService {
	
	public List<Cake> getAllCakes();
	public String getCakeID(String cakeName);
	public List<Order> getAllOrders();
	public List<Order> getAdminOrders(String userId);
	public void insertOrder(Order order);
	public Order getOrderById(int id);
	public void updateOrder(Order order);
	public void deleteOrder(String id);
	public void deleteProduct(String id);
	public List<Order> getPopularCakes();
	public List<Order> getPopularCustomers();
	public List<Order> getUserOrders(String userId);
	public String getSellerId(String cid);
	public String getSellerName(String sid);
}
