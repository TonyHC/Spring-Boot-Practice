<!DOCTYPE html>
<html>

<head>

    <link rel = "stylesheet" type="text/css" 
          href = "${pageContext.request.contextPath}/resources/css/main-menu.css">

    <script src = "${pageContext.request.contextPath}/resources/js/alert.js"></script>

</head>

<body>

<h2>Spring MVC Demo - Home Page</h2>

<a href = "hello/showForm">Plain Hello World</a>

<br><br>

<a href = "student/showForm">Student Form</a>

<br><br>

<a href = "customer/showForm">Customer Form</a>

<br><br>

<img src = "${pageContext.request.contextPath}/resources/images/spring-logo.png" />

<br><br>

<input type = "button" onclick = "doSomeWork()" value = "Click Me"/>

</body>

</html>