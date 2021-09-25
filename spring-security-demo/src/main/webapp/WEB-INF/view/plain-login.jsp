<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

<title>Login Page</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/error.css" />

</head>

<body>

	<h3>Login Page</h3>

	<form:form action="${pageContext.request.contextPath}/authenticateUser"
		method="POST">
		<!-- Check for Login Error and display Error Message -->
		<c:if test="${param.error != null}">
			<i class="failed">Invalid Username/Password</i>
		</c:if>

		<p>
			Username: <input type="text" name="username" />
		</p>

		<p>
			Password: <input type="password" name="password" />
		</p>

		<input type="submit" value="Login" />
	</form:form>

</body>

</html>