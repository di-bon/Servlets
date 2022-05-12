<!-- Francesco Di Bon 5BIA 12-05-2022 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='css/bootstrapcss/bootstrap.min.css' rel='stylesheet' type='text/css' />
	<script src='js/jquery-3.6.0.min.js' type='text/javascript'></script>
	<script src='js/bootstrapjs/bootstrap.bundle.min.js' type='text/javascript'></script>
	<title>Error</title>
</head>
<body>
	<div class="row d-flex justify-content-center" style="margin-top: 35px">
		<div class="col-md-6">
			<h5>${error}</h5>
			<div>${errorMessage}</div>
			<div>
				<form action="App" method="post">
					<input type="submit" id="back-to-login" class="btn btn-primary" value="Go to login page" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>