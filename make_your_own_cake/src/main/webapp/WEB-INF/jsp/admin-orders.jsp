<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@page session="false"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Your Order-admin</title>
</head>
<body>

	 	
	 		<form action="/addCake" method="POST">
                  <div class="form-group">
                     <label for="cakeName">cakeName:</label>
                      <input type="text" required class="form-control" id="cakeName" placeholder="Enter cake name" name="cakeName">
                  </div>
                
                  <div class="form-group">
                     <label for="stock">Stock:</label>
                     <input type="text" required class="form-control" id="stock" placeholder="Enter the stock" name="stock">
                  </div>
                   <div class="form-group">
                     <label for="type">Type:</label>
                     <input type="text" required class="form-control" id="type" placeholder="Enter the type of cake most suitable for" name="type">
                  </div>
                  <div class="form-group">
                     <label for="amount">Amount:</label>
                     <input type="text" required class="form-control" id="amount" placeholder="Enter the amount" name="amount">
                  </div>
                  <div class="col-md-12 text-center"> 
                     <button id="place-order" name="place-order" class="btn btn-primary">Submit</button> 
                  </div>
           </form>
           

</body>
</html>