<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Management Application</title>
</head>
<body>
<center>
    <h1>Student Management</h1>
    <h2>
        <a href="students?action=Students">List All Students</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Student
                </h2>
            </caption>
            <c:if test="${student != null}">
                <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
            </c:if>
            <tr>
                <th>Student Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${student.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Student dateofBirth:</th>
                <td>
                    <input type="text" name="dateofBirth" size="45"
                           value="<c:out value='${student.dateofBirth}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>address:</th>
                <td>
                    <input type="text" name="address" size="15"
                           value="<c:out value='${student.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>phoneNumber:</th>
                <td>
                    <input type="text" name="phoneNumber" size="15"
                           value="<c:out value='${student.phoneNumber}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>email:</th>
                <td>
                    <input type="text" name="email" size="15"
                           value="<c:out value='${student.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>