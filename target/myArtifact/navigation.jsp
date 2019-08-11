<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  com.liba.model.entity.User: John
  Date: 01/08/2019
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8">
    <title>Navigation Bar</title>
</head>
<nav th:fragment="navigation" class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!--    <span class="navbar-brand">Main</span>-->
    <div class="collapse navbar-collapse " id="navbarNavAltMarkup">

        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Main <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/app/books">Books</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/app/authors">Authors</a>
            </li>

            <c:if test="${sessionScope.userRoles.contains('USER')||sessionScope.userRoles.contains('ADMIN')}">
                <li class="nav-item">
                    <a class="nav-link" href="/app/profile">Profile</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.userRoles.contains('ADMIN')}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Administration
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/app/taken_books">All taken books</a>
                        <a class="dropdown-item" href="/admin/books">Sort by Book</a>
                        <a class="dropdown-item" href="/admin/users">Sort by User</a>
                    </div>
                </li>
            </c:if>

        </ul>


        <div class="row ml-auto">
            <c:if test="${sessionScope.userRoles==null}">
                <div class="navbar-nav">
                    <a class="nav-item nav-link active " href="${pageContext.request.contextPath}/app/login">
                        <button class="btn btn-sm btn-outline-secondary " type="submit">Login</button>
                    </a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/app/registration">
                        <button class="btn btn-sm btn-outline-secondary" type="submit">Registration</button>
                    </a>
                </div>
            </c:if>
        </div>


        <c:if test="${sessionScope.userRoles.contains('USER')||sessionScope.userRoles.contains('ADMIN')}">

            <span class="navbar-text">Hello,&nbsp;</span>
            <span class="navbar-text">${sessionScope.username}</span>


            <form action="/app/logout" method="post">
                <a class="nav-item nav-link active ">
                    <button class="btn btn-sm btn-outline-secondary " type="submit">Logout</button>
                </a>
            </form>
        </c:if>

    </div>
</nav>
<body>
</body>
</html>