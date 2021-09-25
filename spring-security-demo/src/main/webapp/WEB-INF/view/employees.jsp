<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "security" uri = "http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Employees</title>
</head>

<body>
	<h2>Employees</h2>

	<hr>

	<p>Welcome to Employees Page</p>
	
	<!-- Display Username and Role -->
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	
	<security:authorize access = "hasRole('MANAGER')">

		<p>
			<a href = "${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
		</p>
	
	</security:authorize>
	
	<security:authorize access = "hasRole('ADMIN')">
		
		<p>
			<a href = "${pageContext.request.contextPath}/systems">IT Meeting</a>
		</p>
	
	</security:authorize>
	
	<br>

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />
	</form:form>

</body>

</html>