<!-- References JSTL Tag Library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>

<head>
    <title> Student Confirmation Page </title>
</head>

<body>

The Student confirmed: ${student.firstName} ${student.lastName}

<br><br>

Country: ${student.country}

<br><br>

Favorite Language: ${student.favoriteLanguage}

<br><br>

Operating Systems:

<ul>
    <c:forEach var = "language" items = "${student.operatingSystems}">
    
        <li> ${language} </li>
    
    </c:forEach>
</ul>

</body>

</html>