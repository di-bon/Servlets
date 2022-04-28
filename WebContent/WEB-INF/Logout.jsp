<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body>
	<div>
		<h2>Welcome, ???!</h2>
	</div>
	<div>
		<form action="/Servlets/Loginator" method="post">
			<input type="hidden" name="logout" value="yes" />
			<input type="hidden" name="source" value="logout.jsp" />
			<input type="submit" value="logout" />
		</form>
	</div>
</body>
</html>