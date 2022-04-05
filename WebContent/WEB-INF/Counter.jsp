<%@page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="counter.jsp.CounterJsp"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ke supersballo</title>
</head>
<body>
	<h1>Counter</h1>
	<div>Visitors: ${count}</div>
	<form action="/Servlets/CounterJsp" method="get">
		<div>
			<input type="hidden" name="reset" value="yes"/>
			<input type="submit" value="Reset">
		</div>
	</form>
	<table>
		<tr><th>Boh</th></tr>
		<c:forEach var="visitor" items="${list}">
			<tr>
			<td><c:out value="${visitor}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>