<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生注册信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	function submitData(){
		var name=$("#name").val();
		var password=$("#password").val();
		var phone=$("#phone").val();
		var address=$("#address").val();
		var cardno=$("#cardno").val();
		var realname=$("#realname").val();
		var sex=$("#sex").val();
		var ctime=$("#ctime").val();
		var stuno=$("#stuno").val();
		var memo=$("#memo").val();
		
		if(name==null || name==''){
			alert("请输入用户名！");
		}else if(password==null || password==''){
			alert("请输入密码！");
		}else{
			$.post("${pageContext.request.contextPath}/student/",{'name':name,'password':password,'phone':phone,'address':address,'cardno':cardno,'realname':realname,'sex':sex,'ctime':ctime,'stuno':stuno,'memo':memo},function(result){
				if(result){
					alert("注册成功！");
					//resetValue();
				}else{
					alert("注册失败！");
				}
			},"json");
		}
	}
	// 重置数据
	function resetValue(){
		$("#title").val("");
		UE.getEditor('editor').setContent("");
	}
</script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="添加信息" style="padding: 10px">
 	<table cellspacing="20px">
   		<tr>
   			<td width="80px">用户名：</td>
   			<td><input type="text" id="name" name="name" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">密码：</td>
   			<td><input type="password" id="password" name="password" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">电话：</td>
   			<td><input type="text" id="phone" name="phone" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">住址：</td>
   			<td><input type="text" id="address" name="address" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">身份证号：</td>
   			<td><input type="text" id="carno" name="cardno" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">真实姓名：</td>
   			<td><input type="text" id="realname" name="realname" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">性别：</td>
   			<td><input type="text" id="sex" name="sex" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">学号：</td>
   			<td><input type="text" id="stuno" name="stuno" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">创建时间：</td>
   			<td><input type="text" id="ctime" name="ctime" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td width="80px">备注：</td>
   			<td><input type="text" id="memo" name="memo" style="width: 400px;"/></td>
   		</tr>
   		
   		<tr>
   			<td></td>
   			<td>
   				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">保存</a>
   			</td>
   		</tr>
   	</table>
 </div>
</body>
</html>