<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 11/08/2019
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/navigation.jsp"/>

<div class="container mt-5">

    <!-- Main card element -->
    <div class="card bg-light">
        <div class="card-header">
            <i class="fas fa-book"></i> Books
        </div>
        <div class="card-body">
            <!-- Page Content will wrap here -->
            <table>

                <thead>
                <tr>
                    <th> Book Info</th>
                    <th> Owner</th>
                    <th> Start Date</th>
                    <th> End Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="takenBook" items="${requestScope.takenBooks}">
                    <tr style="border: 1px solid black">

                        <td width="45%" style="padding-left: 35px; padding-top: 15px;border: 1px solid black">
                            <h5 id="book-title">${takenBook.book.title}</h5>

                            <span> by </span>
                            <span>${takenBook.book.author.name}</span>

                        </td>

                        <td style="border: 1px solid black; width: 20%; text-align: center">
                            <p id="user-name">${takenBook.user.username}</p>
                        </td>

                        <td style="border: 1px solid black; width: 10%; text-align: center">
                            <p>${takenBook.taken_time}</p>
                        </td>

                        <td style="border: 1px solid black; width: 10%; text-align: center">
                            <p>${takenBook.returned_time}</p>
                        </td>

                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>