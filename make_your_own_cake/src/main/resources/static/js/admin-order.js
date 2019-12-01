/**
 * 
 */
$(document).ready(function(){
	$(".viewAllReviews").click(function(){
		var cakeId = $(this).prev().prev().prev().prev().val();
		var orderId = $(this).prev().prev().prev().val();
		var count = $(this).prev().val();
		$.ajax({
			url: "http://localhost:8080/getAllReviews?cakeId="+cakeId,
			success:function(result){
				$("#user-review_"+orderId).empty();
				$("#viewAllReviews_"+cakeId+"_"+count).hide();
				for(var i=0;i<result.length; i++){
					$("#user-review_"+orderId+"_"+count).append("<p>"+result[i].reviews+"</p>");
				}
			}
		});
	});
	
	$(".viewCakeLikes").click(function(){
		var cakeId = $(this).prev().prev().prev().prev().val();
		var orderId = $(this).prev().prev().prev().val();
		var count = $(this).prev().val();
		var userId = $(this).prev().prev().val();
		
		$.ajax({
			url: "http://localhost:8080/getLikes?cakeId="+cakeId+"&userId="+userId,
		    success: function(result){
		    	$("#viewCakeLikes_"+orderId+"_"+count).hide();
		    	$("#likes_"+cakeId+"_"+count).html(result);
		    }
		});
	});
});