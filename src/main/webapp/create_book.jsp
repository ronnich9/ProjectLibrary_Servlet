<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 08/08/2019
  Time: 12:48
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
        <div class="card-header"> Add book</div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/app/create_book" method="post">
                <div class="form-group col-md-6">
                    <label for="title"> Title </label>
                    <input type="text"
                           name="title"
                           id="title"
                           class="form-control"/>

                </div>


                <div class="form-group col-md-6">
                    <label for="author">
                        Author:
                    </label>

                    <select class="custom-select" id="author" name="author">
                        <c:forEach items="${authors}" var="author">
                            <option value="${author.id}">${author.name}</option>
                        </c:forEach>
                    </select>

                    <div class="input-group-append">
                        <a class="ml-1" href="/authors/create">
                            Add new author
                        </a>
                    </div>
                </div>


                <div class="form-group col-md-6">
                    <label for="pages"> Pages </label>
                    <input type="number"
                           name="pages"
                           id="pages"
                           class="form-control"
                    />

                </div>

                <div class="form-group col-md-6">
                    <label for="year"> Year </label>
                    <input type="number"
                           name="year"
                           id="year"
                           class="form-control"
                    />
                </div>


                <div class="form-group col-md-6">
                    <label for="imgUrl"> Image URL </label>
                    <input type="text"
                           name="imgUrl"
                           id="imgUrl"
                           class="form-control"
                    />
                </div>

                <div class="form-group col-md-6">
                    <label for="amount"> Amount </label>
                    <input type="number"
                           name="amount"
                           id="amount"
                           class="form-control"
                    />
                </div>

                <div class="row">
                    <div class="col-md-6 mt-5">
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <a class="btn btn-info" href="#" th:href="@{/books}"> Back to All Books </a>
                    </div>
                </div>

            </form>
        </div>

    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
