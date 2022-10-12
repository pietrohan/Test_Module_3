<%--
  Created by IntelliJ IDEA.
  Student: Dell
  Date: 9/30/2022
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="5">
    <caption><h2> Result </h2></caption>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>date of Birth</th>
        <th>address</th>
        <th>phoneNumber</th>
        <th>email</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="student" items="${StudentsbyName}">
        <tr>
            <td><c:out value="${student.g()}"/></td>
            <td><c:out value="${student.getName()}"/></td>
            <td><c:out value="${student.getEmail()}"/></td>
            <td><c:out value="${student.getCountry()}"/></td>
        </tr>
    </c:forEach>
</table><br>

</body>
</html>
