/**
 * 
 */
$(document).ready(function(){
	$("#signup-submit").click(function(){
		registerUser();
	});
});

function registerUser(){
	$.ajax({
	    url: 'localhost:8080/signup',
	    dataType: 'json',
	    type: 'GET',
	    contentType: 'application/json',
//	    data: JSON.stringify( {
//	    	"name": $('#firstname').val(),
//	    	"email": $('#emailId').val(), 
//	    	"password": $('#password'), 
//	    	"phone": $('#phone'),
//	    	"address": $('address'), 
//	    	"role": $('input[name=command]:checked').val()
//	    }),
	    success: function (jsonData) {
            window.location = '/login';

        },
        error: function(data) {
            console.log('Error loading the image');
        }
 } );
	    
}
