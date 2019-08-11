<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 11/08/2019
  Time: 11:12
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
<div class="container mt-5">
    <div class="card">
        <div class="card-header"> Add author</div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/app/create_author" method="post">
                <div class="form-group col-md-6">
                    <label for="name"> Name </label>
                    <input type="text"
                           name="name"
                           id="name"
                           class="form-control"/>
                </div>

                <div class="row">
                    <div class="col-md-6 mt-5">
                        <button type="submit" class="btn btn-primary">Create</button>
                        <a class="btn btn-info" href="#" th:href="@{/authors}"> Back to All Authors </a>
                    </div>
                </div>

            </form>
        </div>

    </div>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
