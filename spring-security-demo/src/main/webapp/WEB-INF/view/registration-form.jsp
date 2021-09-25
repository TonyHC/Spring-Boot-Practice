<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
	<title>Registration Form</title>
	
	<link rel='stylesheet'
		href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
	
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/login.css"
		media="screen" type="text/css" />
	
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/message.css"
		type="text/css" />	  		
</head>

<body>

	<div class="login-card">
	
		<h1>Registration</h1>
		
		<br>
		
		<form:form
			action="${pageContext.request.contextPath}/register/processRegistrationForm"
				modelAttribute = "registerUser" method = "POST">
			<!-- Check for registraionError and display Error Message -->
			<c:if test="${registrationError != null}">
				<i class="failed">${registrationError}</i>
			</c:if>

			<form:input path = "userName" name="username" placeholder="Username" />
			
			<form:password path = "password" name="password" placeholder="Password" />
			
			<form:select path = "formRole" items = "${roles}" class = "drop-down"/>
			
			<input type="submit" name="login" class="login login-submit"
				value="Register" />
		</form:form>
		
	</div>

</body>

</html>