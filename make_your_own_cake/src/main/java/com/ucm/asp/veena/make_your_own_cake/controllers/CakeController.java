package com.ucm.asp.veena.make_your_own_cake.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;
import com.ucm.asp.veena.make_your_own_cake.service.CakeService;

@Controller
public class CakeController {

	@Autowired
	CakeService cakeService;
	
	Cake cake;
	
	@RequestMapping(value="addCake",method=RequestMethod.POST)
	@ResponseBody
	public void insertCake(HttpServletRequest request,@RequestParam String cakeName,@RequestParam int stock,@RequestParam String type, @RequestParam double amount) {
		
		cake = new Cake();
		cake.setCakeName(cakeName);
		cake.setStock(stock);
		cake.setType(type);
		cake.setAmount(amount);
		cakeService.insertCake(cake);
	}
	

}
