package com.ucm.asp.veena.make_your_own_cake.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.service.CakeService;

@Controller
public class CakeController {

	@Autowired
	CakeService cakeService;
	
	
	Cake cake;
	
	@RequestMapping(value="addCake",method=RequestMethod.POST)
	public String insertCake(HttpServletRequest request,@RequestParam String cakeName,@RequestParam int stock,@RequestParam String type, @RequestParam float amount,
			@RequestParam("photo") MultipartFile photo) throws IOException {
		
		
		InputStream imageStream = null; // input stream of the upload file
		String userId = "";
		String email = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("userId")) {
					userId = c.getValue();
				}
				if (c.getName().equals("username")) {
					email = c.getValue();
				}
			}
		}
		
		
        // obtains the upload file part in this multipart request
        if (photo != null) {
            // obtains input stream of the upload file
            imageStream = photo.getInputStream();
        }
         
		
		
		cake = new Cake();
		cake.setCakeName(cakeName);
		cake.setStock(stock);
		cake.setType(type);
		cake.setAmount(amount);
		cake.setImageStream(imageStream);
		cake.setSid(userId);
		cakeService.insertCake(cake);
		return "redirect:/products";
	}
	
	//Prepopulating details of product to edit
			@RequestMapping(value="/edit_product", method=  RequestMethod.GET)
			public ModelAndView editProduct(@RequestParam("id") String id) {
				//List<Order> ordersList = new ArrayList<Order>();
				Cake myCake = cakeService.getCakeById(id);
				//ordersList.add(myOrder);
				ModelAndView model = new ModelAndView("edit_product");
				model.addObject("cake",myCake);
				List<Cake> cakeList = cakeService.getAllCakes();
				model.addObject("cakes",cakeList);
				return model;
			}
			
			//Updating a product in database
			@RequestMapping(value = "/edit_product", method = RequestMethod.POST)
			public String editProduct(HttpServletRequest request,@RequestParam String id, @RequestParam String cakeName,@RequestParam int quantity,  @RequestParam String type,
					@RequestParam float amount) {
				Cake cake = new Cake();
				cake.setCakeId(id);	
				cake.setCakeName(cakeName);
				cake.setStock(quantity);
				cake.setType(type);
				cake.setAmount(amount);
			
				cakeService.updateCake(cake);
				return "redirect:/products";
			}
			
	

}
