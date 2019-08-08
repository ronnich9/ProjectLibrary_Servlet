<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 06/08/2019
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="utf-8">
    <title>Library</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" th:href="@{css/index.css}">
</head>
<body>
<jsp:include page="/navigation.jsp"/>

<c:if test="${sessionScope.userRoles.contains('ADMIN')}">
    <div class="container mt-5">
        <a class="btn btn-lg btn-success ml-5" href="${pageContext.request.contextPath}/app/create_book">Add New Book</a>
    </div>
</c:if>


<div class="container mt-5">

    <!-- Main card element -->
    <div class="card bg-light">
        <div class="card-header">
            <i class="fas fa-book"></i> Books
        </div>
        <div class="card-body">
            <!-- Page Content will wrap here -->
            <table>

                <tbody>
                <c:forEach var="book" items="${requestScope.books}">
                <tr style="border-bottom: 1px solid black">
                    <td width="5%" class="h3" style="padding-left: 15px;"></td>

                    <td width="10%">
                        <img src="${book.imgUrl}" alt="Book Cover Photo" width="100%"/>
                        <div alt="Book Cover Photo" width="100%"></div>
                        <div class="row justify-content-center">
                        </div>
                    </td>
                    <td width="100%" style="padding-left: 35px; padding-top: 15px;">
                        <h5 id="book-title">${book.title}</h5>

                        <span> by </span>
                        <span>${book.author.name}</span>
                        <br>
                        <div style="padding-top: 15px;">
                            <span> Pages: </span>
                            <span>${book.pages}</span>
                            <span class="h6"> | </span>
                            <span> Year: </span>
                            <span>${book.year}</span>
                        </div>
                        <br>

                        <c:if test="${sessionScope.userRoles.contains('USER')}">
                            <div>
                                <form th:action="@{/books/take/{id} (id=${book.id})}" method="post">
                                    <button type="submit" class="btn btn-success ml-4 mb-3">
                                        Take
                                    </button>
                                </form>
                            </div>
                        </c:if>
                    </td>


                    <td class="row justify-content-center" style="padding-top: 25px">

                        <c:if test="${sessionScope.userRoles.contains('ADMIN')}">
                            <div>

                                <button th:href="@{/books/edit/{id}(id=${book.id})}" class="btn btn-danger mr-3"
                                   style="width: 100px"> Update</button>

                                <br>

                                <button th:href="@{/books/delete/{id}(id=${book.id})}" class="btn btn-danger mt-3"
                                   style="width: 100px"> Delete</button>

                            </div>
                        </c:if>
                        <hr style="width: 250px; visibility: hidden"/>

                    </td>


                </tr>
                </c:forEach>


                </tbody>
            </table>

        </div>
    </div>
</div>

</body>

<footer></footer>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>