// JavaScript Document
$(function($){
	$("#loginbtn").on("click",function(e)
      {
		  var userName = $("#username").val();
		  var passwd = $("#userpwd").val();
		  
		  //alert(userName + " passwd:["+passwd+"]");
		  $.ajax({
				 url:'/test/login',
				 type:'post',
				 data:{
					 username:userName,
					 passwd:passwd
				 },
				 error: function()
				 {
					 alert("·þÎñÆ÷³¬Ê±!");
				 },
				 success:function(res)
				 {
					 alert(res);
				  }
				  });
				
	  });
});