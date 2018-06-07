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
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/bodyHead.jsp"/>

    <h2>Books</h2>
    <a href="/add">Добавить</a>
    <section>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Book</th>
                <th>Author</th>
                <th>Description</th>
                <th>ISBN</th>
                <th>Print Year</th>
                <th>Read Already</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${books}" var="book">
                <jsp:useBean id="book" scope="page" type="com.books.model.Book"/>
                <tr>
                    <td><c:out value="${book.getId()}"/></td>
                    <td>${book.title}</td><%--just field name--%>
                    <td>${book.author}</td>
                    <td><%=book.getDescription()%></td>
                    <td>${book.getIsbn()}</td>
                    <td>${book.getPrintYear()}</td>
                    <td>${book.isReadAlready()}</td>
                    <td><a href="edit/${book.getId()}">Edit</a></td>
                    <td><a href="books?action=delete&id=${book.getId()}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
