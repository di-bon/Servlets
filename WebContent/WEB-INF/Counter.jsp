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
	<script type="text/javascript">
		$(document).ready(function  () {
			modalDelete = new bootstrap.Modal(document.getElementById('modalDelete'), {
				backdrop: 'static',
				keybord: false,
			})
			
			formDeleteAll = document.getElementById('formDeleteAll');
			
			$(document).on('click', '#deleteAll', function () {
				modalDelete.show();
			})
			
			$(document).on('click', '#confirmDeleteAll', function () {
				formDeleteAll.submit();
				modalDelete.dismiss();
			})
			
			$(document).on('click', '#refuseDeleteAll', function () {
				modalDelete.dismiss();
			})
		})
	</script>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		if ($('.toast .toast-body').html().trim()!="") {
			$('.toast').toast('show');
		}
	})	
	
	</script>
	
	<!-- Toast -->
	<!-- For Overlay -->
	<div style="position: relative;">
	
		<!-- Flexbox container for aligning the toasts: position-fixed class do the overlay -->
		<div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center w-100 position-fixed">
		
			<!-- Container for stacking the toasts (if you show more than one) -->
			<div class="toast-container">
				<div class="toast" data-bs-delay="5000" data-autohide="false" role="alert" aria-live="assertive" aria-atomic="true">
			  		<div class="toast-header">
			    		<!-- img src="..." class="rounded me-2" alt="..." -->
			    		<strong class="me-auto">Bootstrap</strong>
			    		<small>popup</small>
			    		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
				  	</div>
				  	<div class="toast-body" value="${toastMessage}">
				    	
				  	</div>
				</div>
			</div>
		</div>
	</div>

	<button type="button" id="showtoast" data-id="123" class="btn btn-primary" onClick="$('.toast').toast('show')">Toast</button>
	
	<div id="modalDelete" class="modal" tabindex="-1" role="dialog" data-bs-backdrop="static" data-bs-keyboard="false" >>
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Confirm action</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>Do you really want to delete all?</p>
	        <form id="formDeleteAll" action="/Servlets/CounterJsp" method="get">
			<div>
				<input type="hidden" name="reset" value="yes"/>
			</div>
		</form>
	      </div>
	      <div class="modal-footer">
	        <button id="confirmDeleteAll" type="button" class="btn btn-primary">Delete all</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<h1>Counter</h1>
	<div>You are the visitor number: ${count}</div>
	<button type="button" name="deleteAll" id="deleteAll" class="btn btn-primary">Reset</button>
	<!-- <form id="formDeleteAll" action="/Servlets/CounterJsp" method="get">
		<div>
			<input type="hidden" name="reset" value="yes"/>
			<input type="button" name="deleteAll" id="deleteAll" value="Reset">
		</div>
	</form> -->
	<form action="/Servlets/CounterJsp" method="post">
	<table class="table table-striped table-hover">
		<tr><th>Address</th><th>Port</th><th>Date</th><th>Delete</th></tr>
		<c:forEach var="visitor" items="${list}">
			<tr>
				<td><c:out value="${visitor.getAddress()}" /></td>
				<td><c:out value="${visitor.getPort()}" /></td>
				<td><c:out value="${visitor.getDate()}" /></td>
				<td><input type="hidden" name="deleteUserAt" value="${visitor.id}" /><input type="submit" value="Delete" class="btn btn-primary"/><td>
			</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>