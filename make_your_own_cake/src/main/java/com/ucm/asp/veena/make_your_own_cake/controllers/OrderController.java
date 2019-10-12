package com.ucm.asp.veena.make_your_own_cake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;
import com.ucm.asp.veena.make_your_own_cake.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	Order order;
	
	@RequestMapping(value="getAllProducts",method=RequestMethod.GET)
	@ResponseBody
	public String getAllCakes() {
			
		//return dashboardService.getTrendingProductsThisMonth().toString();
		return null;
		
	}
	@RequestMapping(value="addProduct",method=RequestMethod.GET)
	@ResponseBody
	public void insertCake(@RequestParam String cakeName,@RequestParam int quantity, @RequestParam String shipping_address, @RequestParam String message,
			@RequestParam float amount) {
		order = new Order();
		order.setCakeName(cakeName);
		order.setQty(quantity);
		order.setShippingAddress(shipping_address);
		order.setMessage(message);
		order.setAmount(amount);
		
		
		
		
	}

}
