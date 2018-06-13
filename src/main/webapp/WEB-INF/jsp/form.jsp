<%--
  Created by IntelliJ IDEA.
  User: Darkhan.Zhaugasharov
  Date: 2018-05-04
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/bodyHead.jsp"/>
    <ul class="breadcrumb">
        <li><a href="/"><i class="fa fa-home"></i> <fmt:message key="app.home"/></a></li>
        <li><i class="fa fa-book"></i> Форма</li>
    </ul>

    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Форма книга</h2>
            <form action="" method="post">
                <input type="hidden" name="id" value="${book.getId()}"/>
                <div class="form-group${errors.containsKey('title') ? " has-error" : ""}">
                    <label><fmt:message key="app.book_title"/></label>
                    <input name="title" value="${book.getTitle()}" class="form-control"/>
                    <div class="error-block">
                        <c:if test="${errors.containsKey('title')}">
                            <ul>
                            <c:forEach items="${errors.get('title')}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>
                <div class="form-group${errors.containsKey('description') ? " has-error" : ""}">
                    <label><fmt:message key="app.book_description"/></label>
                    <textarea name="description" class="form-control">${book.getDescription()}</textarea>
                    <div class="help-block">
                        <c:if test="${errors.containsKey('description')}">
                            <ul>
                                <c:forEach items="${errors.get('description')}" var="error">
                                    <li>${error}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>
                <div class="form-group${errors.containsKey('author') ? " has-error" : ""}">
                    <label><fmt:message key="app.book_author"/></label>
                    <input name="author" value="${book.getAuthor()}" class="form-control"/>
                    <div class="help-block">
                        <c:if test="${errors.containsKey('author')}">
                            <ul>
                                <c:forEach items="${errors.get('author')}" var="error">
                                    <li>${error}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-5${errors.containsKey('isbn') ? " has-error" : ""}">
                        <label>ISBN</label>
                        <input name="isbn" value="${book.getIsbn()}" class="form-control"/>
                        <div class="help-block">
                            <c:if test="${errors.containsKey('isbn')}">
                                <ul>
                                    <c:forEach items="${errors.get('isbn')}" var="error">
                                        <li>${error}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group col-md-4${errors.containsKey('printYear') ? " has-error" : ""}">
                        <label><fmt:message key="app.book_print_year"/></label>
                        <input name="printYear" value="${book.getPrintYear()}" class="form-control"/>
                        <div class="help-block">
                            <c:if test="${errors.containsKey('printYear')}">
                                <ul>
                                    <c:forEach items="${errors.get('printYear')}" var="error">
                                        <li>${error}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" name="submit" value="<fmt:message key="app.submit"/>" class="btn btn-primary"/>
                    <a href="/" class="btn btn-default"><fmt:message key="app.cancel"/></a>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
