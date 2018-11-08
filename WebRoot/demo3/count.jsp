<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>欢迎大家</h3>
<%--从ServletContext来获取map集合，循环遍历显示ip和数量 --%>

<table border="1" width="70%">
	<c:forEach var="entry" items="${ countMap }">
		<tr>
			<td>${ entry.key }</td>
			<td>${ entry.value }</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>







