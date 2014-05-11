jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('#loginDiv').slideDown(200);
	});
	
	if(error == "-1"){
		$('#loginDiv').slideDown(200);
		debugger;
		$("#loginErrorDiv").removeClass("loginErrorHidden");
		$("#loginErrorDiv").addClass("loginErrorDisplay");
	}else{
		$("#loginErrorDiv").removeClass("loginErrorDisplay");
		$("#loginErrorDiv").addClass("loginErrorHidden");	
	}
//	$('.theme-poptit .close').click(function(){
		//$('.theme-popover-mask').fadeOut(100);
		//$('#loginDiv').slideUp(200);
//	})
})

function showRegister(){
	//$('.theme-popover-mask').fadeOut(100);
	$('#loginDiv').slideUp(200);
	$('#registerDiv').slideDown(200);
}

function cancelLoginDiv(){
	$('.theme-popover-mask').fadeOut(100);
	$('#loginDiv').slideUp(200);
}

function cancelRegisterDiv(){
	$('.theme-popover-mask').fadeOut(100);
	$('#registerDiv').slideUp(200);
}

