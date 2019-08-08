<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 05/08/2019
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/navigation.jsp"/>

<c:if test="${sessionScope.userRoles.contains('ADMIN')}">
    <div class="container mt-5">
        <button class="btn btn-lg btn-success ml-5 mt-5">Add New Author</button>
    </div>
</c:if>


<div class="container mt-5">
    <div class="card">
        <table class="table table-striped">
            <thead>
            <tr>
                <th> Author</th>
                <c:if test="${sessionScope.userRoles.contains('ADMIN')}">
                    <th> Delete</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="author" items="${requestScope.authors}">
                <tr>
                    <td><span> ${author.name} </span></td>
                    <c:if test="${sessionScope.userRoles.contains('ADMIN')}">
                        <td><button class="btn btn-primary">Delete</button></td>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<%--<div th:switch="${authors}" class="container mt-5">--%>
<%--    <div class="card">--%>
<%--        <table class="table table-striped">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th> Author</th>--%>
<%--                <c:if test="${sessionScope.userRoles.contains('ADMIN')}">--%>
<%--                    <th> Delete</th>--%>
<%--                </c:if>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <tr th:each="author : ${authors}">--%>
<%--                <td><span th:text="${author.name}"> Title </span></td>--%>
<%--                <c:if test="${sessionScope.userRoles.contains('ADMIN')}">--%>
<%--                    <td><a class="btn btn-primary">Delete</a></td>--%>
<%--                </c:if>--%>

<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </div>--%>
<%--</div>--%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>