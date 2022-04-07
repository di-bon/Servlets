<%@page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="counter.jsp.CounterJsp"
    import="counter.jsp.User"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ke supersballo</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='css/bootstrapcss/bootstrap.min.css' rel='stylesheet' type='text/css' />
	<script src='js/jquery-3.6.0.min.js' type='text/javascript'></script>
	<script src='js/bootstrapjs/bootstrap.bundle.min.js' type='text/javascript'></script>
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
	<table class="table table-striped table-hover">
		<tr><th>Address</th><th>Port</th><th>Date</th></tr>
		<c:forEach var="visitor" items="${list}">
			<tr>
				<td><c:out value="${visitor.getAddress()}" /></td>
				<td><c:out value="${visitor.getPort()}" /></td>
				<td><c:out value="${visitor.getDate()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>