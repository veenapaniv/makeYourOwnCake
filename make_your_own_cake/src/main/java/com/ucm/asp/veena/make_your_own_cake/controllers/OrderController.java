package com.ucm.asp.veena.make_your_own_cake.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ucm.asp.veena.make_your_own_cake.dao.LikeDao;
import com.ucm.asp.veena.make_your_own_cake.dao.OrderDao;
import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;
import com.ucm.asp.veena.make_your_own_cake.service.OrderService;
import com.ucm.asp.veena.make_your_own_cake.service.impl.EmailSender;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	LikeDao likeDao;
	
	@Autowired
	EmailSender emailSender;

	Order order;
	private String deleteId;

	@RequestMapping(value = "/getCakePhoto/{id}", method = RequestMethod.GET)
	public void getCakePhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
		response.setContentType("image/jpeg");

		Blob ph = orderDao.getPhotoById(id);
		byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
//	@RequestMapping(value = "/getSid", method = RequestMethod.GET)
//	public void getSellerID(HttpServletResponse response, @RequestParam("cid") String cid) throws Exception {
//		String sid = orderService.getSellerId(cid);
//	}
	
	@RequestMapping(value = "/getCakePhotoByOrderId/{id}", method = RequestMethod.GET)
	public void getCakePhotoByOrderId(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
		response.setContentType("image/jpeg");

		Blob ph = orderDao.getPhotoByOrderId(id);
		byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

	@RequestMapping(value = "addProduct", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String insertProduct(HttpServletRequest request,
			@RequestParam(name = "customPhoto", required = false) MultipartFile customPhoto,
			@RequestParam String username, @RequestParam(name = "cakeName", required = false) String cakeName,
			@RequestParam(name = "cakeId", required = false) String cakeId, @RequestParam int quantity,
			@RequestParam String shipping_address, @RequestParam float amount, @RequestParam String message)
			throws IOException {
		String userId = "";
		String email = "";
		String subject ="";
		String emailContent = "";
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
		InputStream imageStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		if (customPhoto != null) {
			// obtains input stream of the upload file
			imageStream = customPhoto.getInputStream();
		}
		order = new Order();
		order.setUsername(username);
		order.setCakeId(cakeId);
		order.setQty(quantity);
		order.setShippingAddress(shipping_address);
		order.setUserId(userId);
		order.setMessage(message);
		// order.setAmount(amount);
		order.setOrder_status("NEW");
		order.setCustomImage(imageStream);
		orderService.insertOrder(order);
		
		subject = "Your Order has been placed!";
		emailContent = "Thank you for Ordering with us. <br> We are excited to deliver your cake <br>" + 
				 "Login to check the status. <br>'<a href='http://localhost:8080/login'>View Orders </a>";
		
		emailSender.sendEmailNotification(email, subject, emailContent);
		
		return "redirect:/orders";
	}

	/**
	 * This method is also a GET Request from the dashboard to load sales, channels,
	 * trending products and profit calculator.
	 * 
	 * @param request
	 * @param inventory
	 * @param model
	 * @return
	 */
	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET, value = "/*")
	public String productsData(HttpServletRequest request, @ModelAttribute("products") Order order, ModelMap model) {
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
		List<Order> userOrders = orderService.getUserOrders(userId);
		List<Order> orders = orderService.getAdminOrders(userId);
		List<Cake> cakeList = orderService.getAllCakes();
		List<Order> popularUsers = orderService.getPopularCustomers();
		List<Order> popularCakes = orderService.getPopularCakes();
		model.addAttribute("cakes", cakeList);
		model.addAttribute("orders", orders);
		model.addAttribute("userOrders", userOrders);
		model.addAttribute("popularUsers", popularUsers);
		model.addAttribute("popularCakes", popularCakes);
		//model.addAttribute("likes", likeDao.getLikes(cakeId))

		return "order";

	}

	// Prepopulating details of product to edit
	@RequestMapping(value = "/editInventory", method = RequestMethod.GET)
	public ModelAndView editInventory(@RequestParam("id") int id) {
		// List<Order> ordersList = new ArrayList<Order>();
		Order myOrder = orderService.getOrderById(id);
		// ordersList.add(myOrder);
		ModelAndView model = new ModelAndView("edit_order");
		model.addObject("order", myOrder);
		List<Cake> cakeList = orderService.getAllCakes();
		model.addObject("cakes", cakeList);
		return model;
	}

	// Updating a product in database
	@RequestMapping(value = "/editInventory", method = RequestMethod.POST)
	public String editInventory(HttpServletRequest request, @RequestParam int id,
			@RequestParam String username, @RequestParam(name = "cakeName", required = false) String cakeName, @RequestParam int quantity,
			@RequestParam String shipping_address, @RequestParam String message,
			@RequestParam(name = "customPhoto", required = false) MultipartFile customPhoto) throws IOException {
		Order order = new Order();
		order.setUsername(username);
		order.setCakeId(cakeName);
		order.setQty(quantity);
		order.setShippingAddress(shipping_address);
		order.setMessage(message);
		// order.setAmount(amount);
		InputStream imageStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		if (customPhoto != null) {
			// obtains input stream of the upload file
			imageStream = customPhoto.getInputStream();

		}
		order.setOrderId(id);
		order.setOrder_status("NEW");
		orderService.updateOrder(order);
		return "redirect:/orders";
	}

	// Prepopulating details of product to edit
	@RequestMapping(value = "/editAdminInventory", method = RequestMethod.GET)
	public ModelAndView editAdminInventory(@RequestParam("id") int id) {
		// List<Order> ordersList = new ArrayList<Order>();
		Order myOrder = orderService.getOrderById(id);
		// ordersList.add(myOrder);
		ModelAndView model = new ModelAndView("edit_order_admin");
		model.addObject("order", myOrder);
		List<Cake> cakeList = orderService.getAllCakes();
		model.addObject("cakes", cakeList);
		return model;
	}

	// Updating a product in database
	@RequestMapping(value = "/editAdminInventory", method = RequestMethod.POST)
	public String editAdminInventory(HttpServletRequest request, @RequestParam int id, @RequestParam String username,
			@RequestParam(name="cakeName", required=false) String cakeName, @RequestParam int quantity, @RequestParam String shipping_address,
			@RequestParam String message, @RequestParam float amount, @RequestParam String orderStatus) {
		Order order = new Order();
		String subject = "";
		String emailContent = "";
//		EmailSender emailSender = new EmailSender();
		order.setUsername(username);
		order.setCakeId(cakeName);
		order.setQty(quantity);
		order.setShippingAddress(shipping_address);
		order.setMessage(message);
		order.setAmount(amount);
		order.setOrderId(id);
		order.setOrder_status(orderStatus);
		orderService.updateOrder(order);
		
		subject = "Your Order #"+id +" status has been updated";
		emailContent = "Thank you for Ordering with us. <br> We are excited to deliver your cake <br>" +
		        "The staus has been moved to <b>"+ orderStatus 
		        + "</b>. Visit the site to view further details <br>"+"<a href='http://localhost:8080/login'>View Orders </a>";;
		emailSender.sendEmailNotification("veenapani.v@gmail.com", subject, emailContent);
		
		return "redirect:/admin-view-orders";
	}

	// View for delete product
	@RequestMapping("/deleteOrder")
	public ModelAndView deleteOrder(@RequestParam("id") String id) {
		deleteId = id;
		ModelAndView model = new ModelAndView("delete_order");
		return model;
	}

	// Deleting a product in database
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public String deleteOrder() {
		orderService.deleteOrder(deleteId);
		return "redirect:/orders";
	}
	
	// View for delete product
	@RequestMapping("/delete_product")
	public ModelAndView deleteProduct(@RequestParam("id") String id) {
		deleteId = id;
		ModelAndView model = new ModelAndView("deleteProduct");
		return model;
	}

	// Deleting a product in database
	@RequestMapping(value = "/delete_product", method = RequestMethod.POST)
	public String deleteProduct() {
		orderService.deleteProduct(deleteId);
		return "redirect:/products";
	}

}
