<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功页面</title>
</head>
<body>

<c:if test="${ empty existUser }">
	<h3><a href="${ pageContext.request.contextPath }/demo4/login.jsp">亲，请登陆！</a></h3>
</c:if>

<c:if test="${ not empty existUser }">
	<h3>亲！欢迎访问：${ existUser.nickname }，角色是：${ existUser.type }</h3>
	
	<h3>
		<a href="${ pageContext.request.contextPath }/admin/add.jsp">添加商品</a>
		<a href="${ pageContext.request.contextPath }/admin/update.jsp">修改商品</a>
		<a href="${ pageContext.request.contextPath }/admin/delete.jsp">删除商品</a>
		<a href="${ pageContext.request.contextPath }/user/show.jsp">查看商品</a>
	</h3>
	
</c:if>

</body>
</html>