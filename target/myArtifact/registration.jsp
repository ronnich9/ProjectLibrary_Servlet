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
      xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Registration form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/navigation.jsp" />


<div class="container mt-5">
    <div class="card">
        <div class="card-header" > Registration </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/app/registration" method="post">
                <label for="username">Username </label>
                <input type="text"
                       name="username"
                       id="username"
                       class="form-control"
                />

                <label for="password">Password</label>
                <input type="password"
                       name="password"
                       id="password"
                       class="form-control"
                />

                <label for="email">Email</label>
                <input type="email"
                       name="email"
                       id="email"
                       class="form-control"
                />

                <label for="phone">Phone number</label>
                <input type="text"
                       name="phone"
                       id="phone"
                       class="form-control"
                />


                <button type="submit" class="btn btn-success mt-3">Submit</button>
            </form>
        </div>

    </div>
    <div class="card-footer" th:text="${result}"></div>
</div>

<footer ></footer>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
