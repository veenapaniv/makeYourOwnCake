package com.ucm.asp.veena.make_your_own_cake.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@RequestMapping(value="addProduct",method=RequestMethod.POST)
	@ResponseBody
	public void insertCake(HttpServletRequest request,@RequestParam String username,@RequestParam String cakeName,@RequestParam int quantity, @RequestParam String shipping_address, @RequestParam String message,
			@RequestParam float amount) {
		String userId = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("userId")) {
					userId = c.getValue();					
				}
				
			}
		}
		order = new Order();
		order.setUsername(username);
		order.setCakeName(cakeName);
		order.setQty(quantity);
		order.setShippingAddress(shipping_address);
		order.setUserId(userId);
		order.setMessage(message);
		order.setAmount(amount);
		order.setOrder_status("NEW");
		
		orderService.insertOrder(order);
		
	}
	
	/**
	 * This method is also a GET Request from the dashboard to load sales, channels, trending products and profit calculator.
	 * @param request
	 * @param inventory
	 * @param model
	 * @return
	 */
	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public String productsData(HttpServletRequest request,@ModelAttribute("products") Order order,ModelMap model) {
		
		List<Order> orders = orderService.getAllOrders();
		
		model.addAttribute("orders", orders);
		
		
		
		return "order";
		
	}

}
