<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Your Order</title>
</head>
<body>

	 		<form action="/order" method="POST">
                  <div class="form-group">
                     <label for="emailId">Username:</label>
                     <INPUT TYPE="radio" name="image" value="image1"/>Image1
                     <INPUT TYPE="radio" name="image" value="image2"/>Image2
                  </div>
                  <div class="form-group">
                     <label for="cakeName">CakeName:</label>
                     <select id="cakeName" required name="cakeName" path="cakeName">
					    <option>Chocolate Cake</option>					 
					    <option>Red Velvet Cake</option>
					    <option>Pineapple Cake</option>
					    <option>Orange Cake</option>
					</select>
					
					
                  </div>
                  <div class="form-group">
                     <label for="stock">Stock:</label>
                     <input type="text" required class="form-control" id="stock" placeholder="Enter the quantity" name="stock">
                  </div>
                   <div class="form-group">
                     <label for="amount">Amount:</label>
                     <input type="number" required class="form-control" id="amount" placeholder="amount" name="dob">
                  </div>
                  <div class="form-group">
                     <label for="type">Type:</label>
                     <input type="text" required class="form-control" id="type" placeholder="Enter the type of Occassion" name="phone">
                  </div>
                  <div class="form-group">
                     <label for="type">Address:</label>
                     <input type="text" required class="form-control" id="type" placeholder="Enter the shipping address" name="address">
                  </div>
                   <div class="form-group">
                     <label for="type">Message:</label>
                     <input type="text" required class="form-control" id="type" placeholder="Enter the message" name="message">
                  </div>
                  <div class="col-md-12 text-center"> 
                     <button id="place-order" name="place-order" class="btn btn-primary">Order</button> 
                  </div>
           </form>

</body>
</html>