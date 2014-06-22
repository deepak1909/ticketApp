<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="u8">
<title>Error</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>

<body style="background: #ffffff"> 
	<section class="container">
		<div class="login" style="width:700px;">
			<h1><c:out value="${error}"/><input style="float:right;" type="button" id="dashboard" value="Ticket Dashboard" onclick="javascript:ticketDashboard();"></h1>
		
		</div>

	</section>

</body>
</html>