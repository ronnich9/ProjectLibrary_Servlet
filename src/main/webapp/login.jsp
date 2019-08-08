<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 05/08/2019
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/navigation.jsp" />
<div class="col-md-8 col-md-offset-2">

</div>
<div class="container mt-5">
    <div class="card">
        <div class="card-header" > Login </div>
        <div class="card-body">

            <form method="post" action="/app/login">
                <label for="username">Username</label>
                <input type="text"
                       id="username"
                       name="username"
                       class="form-control"
                />
                <label for="password">Password</label>
                <input type="password"
                       name="password"
                       id="password"
                       class="form-control"
                />

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-success mt-3">Login</button>

            </form>

        </div>
    </div>
</div>


</body>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
