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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
  <script type="text/javascript" src="/js/user-order.js"></script>
	
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
		        <li class="active"><a href="/orders">View Orders</a></li>
		        <li><a href="/order">Place Order</a></li>
		        <li class="navbar-right"><a href="/sign_out_action">Logout</a></li>
		       <!--  <li><a href="/popular">Top Top Purchases</a></li>
		         <li><a href="/purchases">Top Purchaser</a></li> -->
		      </ul>
		    </div>
		  </div>
		</nav>
        <form action="/getAllProducts">
           	 <div class="col col-md-12 seller-stock" style="padding-left:250px">
			            
			         <div class="col-sm-6 border-left seller-height">
			            <h2>All Orders</h2>
			            	 <table class="table table-striped" id="trending_products" style="width:750px">
				               <thead>
				                  <tr>
				                     <th scope="col">CakeName</th>
				                     <th scope="col">Image</th>
				                     <th scope="col">Quantity</th>
				                     <th scope="col">Shipping Address</th>
				                     <th scope="col">Message</th>
				                     <th scope="col">Username</th>
				                     <th scope="col">Amount</th>
				                     <th scope="col">Order Status</th>
				                     <th scope="col">Edit</th>
				                     <th scope="col">Delete</th>
				                     <th scope="col">Review</th>
				                     <th scope="col">Like</th>
				                     <!-- <th scope="col">Seller</th> -->
				                  </tr>
				               </thead>
			               <tbody id="top_products" >
			               		<c:set var="count" value="1" scope="page" />
			               			<c:forEach var="userOrders" items="${userOrders}">
				               			<tr>
						               		<td class="prodAmt row"><c:out value="${userOrders.cakeName}"/></td>
						               		<td class="prodAmt row"><img width="100" height="100" src="getCakePhotoByOrderId/<c:out value='${userOrders.orderId}'/>"></td>
						               		<td class="prodAmt row"><c:out value="${userOrders.qty}"/></td>
						               		<td class="prodAmt row"><c:out value="${userOrders.shippingAddress}"/></td>
						               		<td class="prodAmt row"><c:out value="${userOrders.message}"/></td>
						               		<td class="prodAmt row"><c:out value="${userOrders.username}"/></td>
						               		<td class="prodAmt row"><c:out value="${userOrders.amount}"/></td>
						               		<td class="prodAmt row"><c:out value="${userOrders.order_status}"/></td>
						               			<td class="prodAmt row">
						               			
						               				<c:choose>
													    <c:when test="${ userOrders.cakeName == 'customCake' }">
													       <label>Can't Edit</label>
													    </c:when>  
													    <c:when test="${ userOrders.order_status != 'NEW' }">
													       <label>Can't Edit</label>
													    </c:when>    
													    <c:otherwise>
													    	<c:url var="editUrl" value="/editInventory" />
							                        	   	<a href="${editUrl}?id=${userOrders.orderId}">Edit</a>
													    </c:otherwise>
													</c:choose>
						               			
							                    </td>
						                        <td class="prodAmt row">
							                        <c:url var="deleteUrl" value="/deleteOrder" />
							                        <a href="${deleteUrl}?id=${userOrders.orderId}"><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Delete</button></a>
						                        </td>
						                        <td class="prodAmt row">
						                        	<input type="hidden" name="reviewCakeCid" value="${userOrders.cakeId}"/>
						                         	<input type="hidden" name="reviewCakeOid" value="${userOrders.orderId}"/>
						                         	<input type="hidden" name="reviewCakeUid" value="${userOrders.userId}"/>
						                         	<input type="hidden" name="count_reviews" value="${count}"/>
						                         	<c:choose>
							                         	<c:when test="${ userOrders.cakeName != 'customCake' }">
										                       <p class="review" id="reviewCake_${userOrders.orderId}_${count}" > Review your order </p>
										                       <p id="user-review_${userOrders.orderId}_${count}"></p>
										                       <p class='viewAllReviews' id='viewAllReviews_${userOrders.cakeId}_${count}'>view all reviews</p>
										                </c:when>
									                </c:choose>       
						                        </td>
						                         <td class="prodAmt row">
						                         	<input type="hidden" name="likeCakeCid" value="${userOrders.cakeId}"/>
						                         	<input type="hidden" name="likeCakeOid" value="${userOrders.orderId}"/>
						                         	<input type="hidden" name="likeCakeUid" value="${userOrders.userId}"/>
						                         	<input type="hidden" name="count_reviews" value="${count}"/>
						                         	<c:choose>
							                         	<c:when test="${ userOrders.cakeName != 'customCake' }">
									                       <i class="fas fa-thumbs-up likeCake" id="likeCake_${userOrders.orderId}_${count}"></i>
									                       <p class="likes"></p>
									                    </c:when>
								                    </c:choose>   
						                        </td>
						                       <%--  <td class="prodAmt row">
						                        	<input type="hidden" name="reviewCakeCid" value="${userOrders.cakeId}"/>
						                         	<input type="hidden" name="reviewCakeOid" value="${userOrders.orderId}"/>
						                         	<input type="hidden" name="reviewCakeUid" value="${userOrders.userId}"/>
						                       		<a href="#" class="sellerName" id="seller_${userOrders.orderId}">View the Seller</a>
						                        </td> --%>
						                    
				               			</tr>
				               			<c:set var="count" value="${count + 1}" scope="page"/>
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