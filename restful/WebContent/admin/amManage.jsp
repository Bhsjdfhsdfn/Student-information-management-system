<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	function formatState(val,row){
	    if('1' == val)
	    {
	     return '发布中';
	    }
	    if('0' == val)
        {
         return '草稿';
        }
	}
	
	function searchBlog(){
		$("#dg").datagrid('load',{
			"name":$("#s_title").val() 
		});
	}
	
	function deleteAM(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要停用的数据！");
			 return;
		 }
		 $.messager.confirm("系统提示","您确定要停用这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					for(var i=0;i<selectedRows.length;i++){
						 $.ajax({  
							    url: "${pageContext.request.contextPath}/student/"+selectedRows[i].id+"",    
							    type: "DELETE",  
							    dataType: "json",  
							    success: function (data) {  
							    	if(data)
							    	{
							    		alert("删除成功");
							    	}else
							    	{
							    		alert("删除失败");
							    	}
							    }  
							});  
					 }
				} 
	   });
	}
	
	function send(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要发布的数据！");
			 return;
		 }
	
		 $.messager.confirm("系统提示","您确定要发布这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					
					 for(var i=0;i<selectedRows.length;i++){
						 $.ajax({  
							    url: "${pageContext.request.contextPath}/student/"+selectedRows[i].id+"",    
							    type: "DELETE",  
							    dataType: "json",  
							    success: function (data) {  
							    	
							    	if(data)
							    		{
							    		   alert("删除成功");
							    		}
							    	
							    }  
							});  
					 }
				} 
	   });
	}
	
	function add(){
		/*  var selectedRows=$("#dg").datagrid("getSelections");
		 if(selectedRows.length!=1){
			 $.messager.alert("系统提示","请选择一！");
			 return;
		 }
		 var row=selectedRows[0]; */
		 window.parent.openTab('添加学生信息','addStu.jsp','icon-writeblog');
	}
	function edit(){
		 var selectedRows=$("#dg").datagrid("getSelections");
		 if(selectedRows.length!=1){
			 $.messager.alert("系统提示","请选择一！");
			 return;
		 }
		 var row=selectedRows[0];
		 window.parent.openTab('修改学生信息','editStu.jsp?id='+row.id,'icon-writeblog');
	}
	
</script>
</head>
<body style="margin: 1px">
<table id="dg" title="学生信息管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/student/list" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="id" width="20" align="center">编号</th>
   		<th field="name" width="200" align="center">姓名</th>
   		<th field="phone" width="200" align="center">电话</th>
   		<th field="address" width="200" align="center">住址</th>
   		<th field="cardno" width="200" align="center">身份证号</th>
   		<th field="realname" width="200" align="center">真实姓名</th>
   		<th field="stuno" width="200" align="center">真实姓名</th>
   		<th field="sex" width="200" align="center">性别</th>
   		<th field="ctime" width="200" align="center">创建时间</th>
   		<th field="memo" width="50" align="center">备注</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 	   <a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 	    <a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteAM()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 	<div>
 		&nbsp;姓名：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) search()"/>
 		<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
</div>
</body>
</html>