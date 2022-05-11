<!-- Francesco Di Bon 5BIA 28-4-2022 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='css/bootstrapcss/bootstrap.min.css' rel='stylesheet' type='text/css' />
	<script src='js/jquery-3.6.0.min.js' type='text/javascript'></script>
	<script src='js/bootstrapjs/bootstrap.bundle.min.js' type='text/javascript'></script>
	<title>Login</title>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function  () {
			passwordinput = document.getElementById('password');
			
			$(document).on('click', '#show-password', function () {
				passwordinput.type === "password" ? passwordinput.type = "text" : passwordinput.type = "password";
			})
			
/* 	 		if ($('.toast .toast-body').html().trim()!="") { */
		 	if ($('.toast #wrongcredentials').html().trim()!="") {
				$('.toast').toast('show');
			}
		})
	</script>

	<!-- Toast -->
	<!-- For Overlay -->
	<div style="position: relative;">
		<!-- Flexbox container for aligning the toasts: position-fixed class do the overlay -->
		<div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center w-100">
		
			<!-- Container for stacking the toasts (if you show more than one) -->
			<div class="toast-container">
				<div class="toast" data-bs-delay="5000" data-autohide="false" role="alert" aria-live="assertive" aria-atomic="true">
			  		<div class="toast-header">
			    		<!-- img src="..." class="rounded me-2" alt="..." -->
			    		<strong class="me-auto">Credenziali errate</strong>
			    		<!-- <small>popup</small> -->
			    		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
				  	</div>
				  	<div id="wrongcredentials" style="display: none;">${wrongcredentials}</div>
				  	<!-- <div class="toast-body">
				    	Credenziali errate
				  	</div> -->
				</div>
			</div>
		</div>
	</div>
	
	<div class="row d-flex justify-content-center"  style="margin-top: 35px">
		<div class="col-md-6">
			<form action="/Servlets/Loginator" method="post">
				<div>
					<h2>Login</h2>
				</div>
				<div class="form-group">
					<label for="email">Email</label>
				    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" required>
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				</div>
				<div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
				</div>
				<div class="form-check">
				    <input type="checkbox" class="form-check-input" id="show-password" name=""show-password"">
				    <label class="form-check-label" for=""show-password"">Show password</label>
				</div>
				<input type="hidden" name="source" value="login.jsp" />
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
	</div>
</body>
</html>