<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>


<body>

    <form:form action = "processForm" modelAttribute = "student">
    
    First name: <form:input path = "firstName" />
    
    <br><br>
    
    Last name: <form:input path = "lastName" />
    
    <br><br>
    
    <!-- Load the Data Using a Properties File -->
    Country:
    
    <form:select path = "country">
    
        <form:options items = "${countryOptions}" />
    
    </form:select>
    
    <br><br>
      
    <!-- Load the Data Using a DS already populated with correct data-->  
    Favorite Language: 
      
    <form:radiobuttons path = "favoriteLanguage" items = "${student.favoriteLanguageOptions}" />
    
    <br><br>  
    
    <!-- Load the Data Using a empty DS and hard code the values in JSP to be populated in DS -->
    Operating Systems:
    
    Windows <form:checkbox path = "operatingSystems" value = "Windows" />
    Mac <form:checkbox path = "operatingSystems" value = "Mac" />
    Linux <form:checkbox path = "operatingSystems" value = "Linux" />        
    
    <br><br> 
    
    <input type = "submit" value = "Submit" />
    
    </form:form>

</body>

</html>