<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="utf-8">
<title>Login Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>

<body>
<body>
	<section class="container">
		<div class="login">
			<h1>Login to Ticketing System</h1>
			<form:form id="loginform" action="${pageContext.request.contextPath}/user/login" method="POST">
				<p>
					<span id="span-block">User Id:</span> <input type="text" id="userId" name="userId" placeholder="Email">
				</p>
				<p>
					<span id="span-block">Password:</span> <input type="password" id="password" name="password" placeholder="Password">
				</p>
				<label class="error"><c:out value="${error}"/></label>
				<p class="submit">
					<input type="submit" id="add" value="Login" onclick="javascript:validateForm();">
				</p>
			</form:form>
		</div>

	</section>

</body>
<footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.js"></script>
	<script>
		function validateForm() {
			$("#loginform").validate({
				rules : {
					userId : "required",
					password : "required"
				},
				messages : {
					userId : "User Id is required",
					password : "Password is required"
				}
			});

		}
	</script>

</footer>
</html>