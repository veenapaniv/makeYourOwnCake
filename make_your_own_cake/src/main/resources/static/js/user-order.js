/**
 * 
 */
$(document).ready(function(){
	$(".sellerName").click(function(){
		var cakeId = $(this).prev().prev().prev().val();
		var orderId = $(this).prev().prev().val();
		$.ajax({
			url: "http://localhost:8080/getSellerId?cid="+cakeId,
			success: function(result){
				var sid = result;
				$.ajax({
					url: "http://localhost:8080/getSellerName?userId="+sid,
					success: function(result){
						$("#seller_"+orderId).html(result);
					}
				});
			}
		});
	});
	$(".likeCake").click(function(){
		var cakeId = $(this).prev().prev().prev().prev().val();
		var orderId = $(this).prev().prev().prev().val();
		var userId = $(this).prev().prev().val();
		var count = $(this).prev().val();
		
		$.ajax({
			url: "http://localhost:8080/likeCake?cakeId="+cakeId+"&userId="+userId, 
			success: function(result){
				getLikeForCakeId(cakeId, userId,orderId,count);
		    
		  }
		});
		//window.location.replace("http://localhost:8080/likeCake/"+cakeId);
	});
	$(".viewAllReviews").click(function(){
		var cakeId = $(this).prev().prev().prev().prev().prev().prev().val();
		var orderId = $(this).prev().prev().prev().prev().prev().val();
		var count = $(this).prev().prev().prev().val();
		displayReviews(cakeId, orderId,count);
	});	
	$(".review").click(function(){
		var cakeId = $(this).prev().prev().prev().prev().val();
		var orderId = $(this).prev().prev().prev().val();
		var userId = $(this).prev().prev().val();
		var count = $(this).prev().val();
		$(this).next().html("<tr><td><input type='text' /> " +
				"<input type='hidden' name='save_review_count' value='"+count+"'><input type='button' id='save_review_"+cakeId+'_'+count+"' class='save_review' value='save'/></td></tr>")
	
				$(".save_review").click(function(){
					var cakeId= $(this).parent().parent().parent().prev().prev().prev().prev().prev().val();
					var orderId= $(this).parent().parent().parent().prev().prev().prev().prev().val();
					var userId= $(this).parent().parent().parent().prev().prev().prev().val();
					var review = $(this).prev().prev().val();
					var count = $(this).prev().val();
					$.ajax({
						url: "http://localhost:8080/reviewCake?cakeId="+cakeId+"&userId="+userId+"&msg="+review,
						success: function(result){
							$("#save_review_"+cakeId+"_"+count).parent().hide();
							//$("#save_review_"+cakeId).parent().html("<p class='viewAllReviews' id='viewAllReviews_"+cakeId+"'>view all reviews</p>");
							$(".viewAllReviews").click(function(){
								displayReviews(cakeId, orderId,count);
							});	
						}
					
					
					});
				});			
	
	
	
	});
	
	function displayReviews(cakeId, orderId, count){
		$.ajax({
			url: "http://localhost:8080/getAllReviews?cakeId="+cakeId,
			success:function(result){
				$("#user-review_"+orderId+"_"+count).empty();
				for(var i=0;i<result.length; i++){
					$("#reviewCake_"+orderId+"_"+count).hide();
					$("#viewAllReviews_"+cakeId+"_"+count).hide();
					$("#user-review_"+orderId+"_"+count).append("<p>"+result[i].reviews+"</p>");
				}
			}
		});
	}
	
	
});




function getLikeForCakeId(cakeId, userId, orderId,count){
	$.ajax({
		url: "http://localhost:8080/getLikes?cakeId="+cakeId+"&userId="+userId,
	    success: function(result){
	    	console.log("result="+result);
	    	$("#likeCake_"+orderId+"_"+count).next().html(result);
	    }
	});
}

