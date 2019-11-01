<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@page session="false"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Your Order</title>
</head>
<body>

	 		<form action="/editInventory" method="POST">
	 			  <div class="form-group">
	 			  	 <label for="id">Order Id:</label>	
                     <input type="text" required class="form-control" id="id" name="id" value="${order.orderId}">
                  </div>	
                  <div class="form-group">
                     <label for="username">Username:</label>
                      <input type="text" required class="form-control" id="username" placeholder="enter your name" name="username" value="${order.username}">
                  </div>
                  <div class="form-group">
                     <label for="cakeName">Select the CakeName:</label>
                     <%-- <input TYPE="radio" name="cakeName" value="${cakes.cakeName}"/>${cakes.cakeName}<img width="100" height="100" src="getCakePhoto/<c:out value='${cakes.cakeId}'/>"> --%>
					<c:forEach var="cakes" items="${cakes}">
					<c:out value="0"/>test
					<c:out value="${cakes.cakeName }"/>
						<input TYPE="radio" name="cakeName" value="${cakes.cakeName}"/>${cakes.cakeName}<img width="100" height="100" src="getCakePhoto/<c:out value='${cakes.cakeId}'/>">
					</c:forEach>
                  </div>  
                  <div class="form-group">
                     <label for="quantity">Stock:</label>
                     <input type="text" required class="form-control" id="quantity" placeholder="quantity" name="quantity" value="${order.qty}">
                  </div>
                   <div class="form-group">
                     <label for="amount">Amount:</label>
                     <input type="number" required class="form-control" id="amount" placeholder="amount" name="amount" value="${order.amount}">
                  </div>
               <%--    <div class="form-group">
                     <label for="type">Type:</label>
                     <input type="text" required class="form-control" id="type" placeholder="Enter the type of Occassion" name="type" value="${order.username}">
                  </div> --%>
                  <div class="form-group">
                     <label for="address">Address:</label>
                     <input type="text" required class="form-control" id="address" placeholder="Enter the shipping address" name="shipping_address" value="${order.shippingAddress}">
                  </div>
                   <div class="form-group">
                     <label for="message">Message:</label>
                     <input type="text" required class="form-control" id="message" placeholder="Enter the message" name="message" value="${order.message}">
                  </div>
                  <div class="col-md-12 text-center"> 
                     <button id="place-order" name="place-order" class="btn btn-primary">Order</button> 
                  </div>
           </form>
           
</body>
</html>