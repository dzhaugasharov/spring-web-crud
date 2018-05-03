<%--
  Created by IntelliJ IDEA.
  User: Darkhan.Zhaugasharov
  Date: 2018-05-03
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
    <h2>Books</h2>
    <section>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>ID</th>
                <th>Book</th>
                <th>Description</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.getId()}</td>
                    <td>${book.getTitle()}</td>
                    <td>${book.getDescription()}</td>
                    <td><a href="books?action=update&id=${book.getId()}">Update</a></td>
                    <td><a href="books?action=delete&id=${book.getId()}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
</body>
</html>
