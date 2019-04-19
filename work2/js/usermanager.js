// JavaScript Document
$(function($){
		   
	var table = $("#usertab");
	table.bootstrapTable({
			height:'600px',
			data:[
				  {
					  "id":'1',
					  "username":"黎明",
					  "sex":"男",
					  "age":'12',
					  "phone":'123567',
					  "address":"东北大学浑南校区",
					  
					  
					  
				  }
			],
			columns:[
				{
						 field:'select',
						 checkbox:true,
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'id',
						 title:'编号',
						 
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'username',
						 title:'用户名',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'sex',
						 title:'性别',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'age',
						 title:'年龄',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'phone',
						 title:'电话',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'address',
						 title:'住址',
						 align:"center",
						 valign:'middle',
                },
			  ]
            }
	);
	
});