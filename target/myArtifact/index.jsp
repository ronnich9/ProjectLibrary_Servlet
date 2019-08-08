<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="/navigation.jsp"/>
<div class="container">
    <br/>
    <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8">
            <form class="card card-sm" name="frm" action="Search" method="post">
                <div class="card-body row no-gutters align-items-center">
                    <div class="col-auto">
                        <i class="fas fa-search h4 text-body"></i>
                    </div>
                    <!--end of col-->
                    <div class="col">
                        <input class="form-control form-control-lg form-control-borderless" name="bookTitle"
                               value="searchValue" type="search"
                               placeholder="Search topics or keywords">
                    </div>
                    <!--end of col-->
                    <div class="col-auto">
                        <button class="btn btn-lg btn-success" type="submit">Search</button>
                    </div>
                    <!--end of col-->
                </div>
            </form>
        </div>
        <!--end of col-->
    </div>
</div>

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
                        <div class="row justify-content-center">
                        </div>
                    </td>
                    <td width="100%" style="padding-left: 35px; padding-top: 15px;">
                        <h5 id="book-title">${book.title}</h5>

                        <span> by </span>
                        <span>${book.author.name}</span>
                        <span>${book.author.id}</span>
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

                                <a th:href="@{/books/edit/{id}(id=${book.id})}" class="btn btn-danger mr-3"
                                   style="width: 100px"> Update</a>

                                <br>

                                <a th:href="@{/books/delete/{id}(id=${book.id})}" class="btn btn-danger mt-3"
                                   style="width: 100px"> Delete</a>

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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>