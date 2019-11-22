<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Delete Order</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/login.css">
      <link rel="stylesheet" href="css/dashboard.css">
   </head>
   
   <body>
    <div class="jumbotron text-center">
               <i class="fa fa-birthday-cake" style="font-size:48px;"></i><h1>Make Your Own Cake</h1>
      </div>
        <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav">
		        <li class=""><a href="/admin-orders">View Placed Orders</a></li>
		      <li class=""><a href="/admin-dashboard">Back</a></li>
		        <li><a href="/popular">Popular Users</a></li>
		         <li><a href="/purchases">Popular Cakes</a></li>
		         <li class="navbar-right"><a href="/sign_out_action">Logout</a></li>
		      </ul>
		    </div>
      <div  class="inventory-container edit-container" style="padding-left:150px; padding-right:150px; padding-top:100px; padding-bottom:100px">
         <form action="/deleteOrder" method="post" id="productForm">
            <div class="form-group">
               <label>Do you really want to delete this product?</label>
            </div>
            <input type="SUBMIT" class="btn btn-default" value="Submit" />
            <a href="/products"><input type="button" id="resetBtn" class="btn btn-default" value="Cancel"/></a>
         </form>
      </div>
   </body>
</html>