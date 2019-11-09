$(document).ready(function(){
	$("#cakeImageFile").click(function(){
		if($("#cakeImageFile").prop("disabled",true)){
			$("#cakeImageFile").prop("disabled",false);
		}
		$(".cakeNameRadio").prop("disabled", true);
	})
	$(".cakeNameRadio").click(function(){
		if($(".cakeNameRadio").prop("disabled",true)){
			$(".cakeNameRadio").prop("disabled",false)
		}
		$("#cakeImageFile").prop("disabled", true);
	})
});