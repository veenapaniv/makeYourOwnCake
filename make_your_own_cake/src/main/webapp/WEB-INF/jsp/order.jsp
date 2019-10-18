<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@page session="false"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Your Order</title>
</head>
<body>

	 		<form action="/addProduct" method="POST">
                  <div class="form-group">
                     <label for="username">Username:</label>
                      <input type="text" required class="form-control" id="username" placeholder="Enter your name" name="username">
                  </div>
                  <div class="form-group">
                     <label for="cakeName">Select the CakeName:</label>
                     <select id="cakeName" required name="cakeName" path="cakeName">
					    <option>Chocolate Cake</option>					 
					    <option>Red Velvet Cake</option>
					    <option>Pineapple Cake</option>
					    <option>Orange Cake</option>
					</select>
					
					
                  </div>
                  <div class="form-group">
                     <label for="quantity">Stock:</label>
                     <input type="text" required class="form-control" id="quantity" placeholder="Enter the quantity" name="quantity">
                  </div>
                   <div class="form-group">
                     <label for="amount">Amount:</label>
                     <input type="number" required class="form-control" id="amount" placeholder="amount" name="amount">
                  </div>
                  <div class="form-group">
                     <label for="type">Type:</label>
                     <input type="text" required class="form-control" id="type" placeholder="Enter the type of Occassion" name="type">
                  </div>
                  <div class="form-group">
                     <label for="address">Address:</label>
                     <input type="text" required class="form-control" id="address" placeholder="Enter the shipping address" name="shipping_address">
                  </div>
                   <div class="form-group">
                     <label for="message">Message:</label>
                     <input type="text" required class="form-control" id="message" placeholder="Enter the message" name="message">
                  </div>
                  <div class="col-md-12 text-center"> 
                     <button id="place-order" name="place-order" class="btn btn-primary">Order</button> 
                  </div>
           </form>
           
           
           <form action="/getAllProducts">
           	    <div class="col col-md-12 seller-stock">
			            
			         <div class="col-sm-6 border-left seller-height">
			            <h2>All Orders</h2>
			            	 <table class="table table-striped" id="trending_products">
			               <thead>
			                  <tr>
			                     <th scope="col">Cake Name</th>
			                     <th scope="col">Quantity</th>
			                     <th scope="col">Shipping Address</th>
			                     <th scope="col">Message</th>
			                     <th scope="col">Username</th>
			                     <th scope="col">Amount</th>
			                     <th scope="col">Edit</th>
			                     <th scope="col">Delete</th>
			                  </tr>
			               </thead>
			               <tbody id="top_products">
			               		
			               			<c:forEach var="orders" items="${orders}">
				               			<tr>
						               		<td class="productName"><c:out value="${orders.cakeName}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.qty}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.shippingAddress}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.message}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.username}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.amount}"/></td>
						               		<td>
						                        <c:url var="editUrl" value="/editInventory" />
						                        <a href="${editUrl}?id=${orders.orderId}">Edit</a>
						                        <c:url var="deleteUrl" value="/deleteInventory" />
						                        <a href="${deleteUrl}?id=${orders.orderId}">Delete</a>
						                    </td>
				               			</tr>
			               			</c:forEach>
			               </tbody>
			            </table>
			           
			         </div>
			         <hr />
			      </div>	
           	
           </form>
           
           
           

</body>
</html>