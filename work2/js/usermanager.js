// JavaScript Document
$(function($){
		   
	var table = $("#usertab");
	table.bootstrapTable({
			height:'600px',
			data:[
				  {
					  "id":'1',
					  "username":"����",
					  "sex":"��",
					  "age":'12',
					  "phone":'123567',
					  "address":"������ѧ����У��",
					  
					  
					  
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
						 title:'���',
						 
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'username',
						 title:'�û���',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'sex',
						 title:'�Ա�',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'age',
						 title:'����',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'phone',
						 title:'�绰',
						 align:"center",
						 valign:'middle',
                },
				{
						 field:'address',
						 title:'סַ',
						 align:"center",
						 valign:'middle',
                },
			  ]
            }
	);
	
});