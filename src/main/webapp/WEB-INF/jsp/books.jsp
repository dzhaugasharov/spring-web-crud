<%--
  Created by IntelliJ IDEA.
  User: Darkhan.Zhaugasharov
  Date: 2018-05-03
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHead.jsp"/>

<div class="panel panel-default">
    <div class="panel-body">
        <h2><fmt:message key="app.books"/></h2>
        <a href="/add"><fmt:message key="app.add_book"/></a>
        <section>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="app.book_title"/></th>
                    <th><fmt:message key="app.book_author"/></th>
                    <th><fmt:message key="app.book_description"/></th>
                    <th>ISBN</th>
                    <th><fmt:message key="app.book_print_year"/></th>
                    <th><fmt:message key="app.book_read_already"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${books}" var="book">
                    <jsp:useBean id="book" scope="page" type="com.books.model.Book"/>
                    <tr>
                        <td><c:out value="${book.getId()}"/></td>
                        <td>${book.title}</td>
                            <%--just field name--%>
                        <td>${book.author}</td>
                        <td><%=book.getDescription()%>
                        </td>
                        <td>${book.getIsbn()}</td>
                        <td>${book.getPrintYear()}</td>
                        <td>${book.isReadAlready()}</td>
                        <td><a href="edit/${book.getId()}"><fmt:message key="app.edit"/></a></td>
                        <td><a href="books?action=delete&id=${book.getId()}"><fmt:message key="app.delete"/></a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</div>

<div class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Book</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
                    <input type="hidden" name="id" value=""/>
                    <div class="form-group">
                        <label><fmt:message key="app.book_title"/></label>
                        <input name="title" value="" class="form-control"/>
                        <div class="error-block">
                        </div>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="app.book_description"/></label>
                        <textarea name="description" class="form-control"></textarea>
                        <div class="help-block">
                        </div>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="app.book_author"/></label>
                        <input name="author" value="" class="form-control"/>
                        <div class="help-block"></div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-5">
                            <label>ISBN</label>
                            <input name="isbn" value="" class="form-control"/>
                            <div class="help-block"></div>
                        </div>
                        <div class="form-group col-md-4">
                            <label><fmt:message key="app.book_print_year"/></label>
                            <input name="printYear" value="" class="form-control"/>
                            <div class="help-block">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" value="<fmt:message key="app.submit"/>" class="btn btn-primary"/>
                        <a href="/" class="btn btn-default"><fmt:message key="app.cancel"/></a>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Save</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
