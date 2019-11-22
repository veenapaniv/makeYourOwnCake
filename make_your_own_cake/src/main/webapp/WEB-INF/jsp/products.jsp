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
<title>Make Your Order</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

 <div class="jumbotron text-center">
        <i class="fa fa-birthday-cake" style="font-size:48px;"></i><h1>Make Your Own Cake</h1>
      </div>
      
      <nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>                        
		      </button>
		      <a class="navbar-brand" href="#">Make Your Own Cake</a>
		    </div>
		    <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav">
		         <li class=""><a href="/admin-view-orders">View Placed Orders</a></li>
		        <li class=""><a href="/products">View Inventory</a></li>
		         <li><a href="/popular">Popular Users</a></li>
		         <li><a href="/purchases">Popular Cakes</a></li>
		         <li class="navbar-right"><a href="/sign_out_action">Logout</a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
		<div class="form-group" style="text-align: center;margin: 0;">
        	<a href="/admin-orders" class="btn btn-info" role="button" id="addNewCake">Add New Cake</a>
        </div>
		
        <form action="/getAllCakes">
           	 <div class="col col-md-12 seller-stock" style="padding-left:250px">
			            
			         <div class="col-sm-6 border-left seller-height">
			            <h2>All Cakes</h2>
			            	 <table class="table table-striped" id="trending_products" style="width:750px">
				               <thead>
				                  <tr>
				                     <th scope="col">Cake Name</th>
				                     <th scope="col">Stock</th>
				               			<th scope="col">Image</th>
				                     <th scope="col">Type</th>
				            
				                     <th scope="col">Amount</th>
				                     <th scope="col">Edit</th>
				                     <th scope="col">Delete</th>
				                  </tr>
				               </thead>
			               <tbody id="top_products" >
			               		
			               			<c:forEach var="cakes" items="${cakes}">
				               			<tr>
						               		<td class="prodAmt row"><c:out value="${cakes.cakeName}"/></td>
						               		<td class="prodAmt row"><c:out value="${cakes.stock}"/></td>
						   					<td class="prodAmt row"><img width="100" height="100" src="getCakePhoto/<c:out value='${cakes.cakeId}'/>"></td>
						               		<td class="prodAmt row"><c:out value="${cakes.type}"/></td>
						               	
						               		<td class="prodAmt row"><c:out value="${cakes.amount}"/></td>
						               		<td class="prodAmt row">
						                        <c:url var="editUrl" value="/edit_product" />
						                        <a href="${editUrl}?id=${cakes.cakeId}">Edit</a></td>
						                        <td class="prodAmt row">
						                        <c:url var="deleteUrl" value="/delete_product" />
						                        <a href="${deleteUrl}?id=${cakes.cakeId}"><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal">Delete</button></a>
						                        </td>
						                    
				               			</tr>
			               			</c:forEach>
			               			<%-- <c:forEach var="popularUsers" items="${popularUsers}">
				               			<tr>
						               		<td class="productName"><c:out value="${orders.cakeName}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.username}"/></td>
						               		<td class="prodAmt row"><c:out value="${orders.cid}"/></td>
				               			</tr>
			               			</c:forEach> --%>
			               			
			               </tbody>
			            </table>
			            
			          		
			         </div>
			         <hr />
			      </div>	
  </form>
           
                
      
</body>
</html>