<%--
  Created by IntelliJ IDEA.
  User: Darkhan.Zhaugasharov
  Date: 2018-05-04
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book form</title>
</head>
<body>
    <ul class="breadcrumb">
        <li><a href="/"><i class="fa fa-home"></i> Главная</a></li>
        <li><i class="fa fa-book"></i> Форма</li>
    </ul>

    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Форма книга</h2>
            <form action="" method="post">
                <input type="hidden" name="id" value="${book.getId()}</#if>"/>
                <div class="form-group">
                    <label>Название книги</label>
                    <input name="title" value="${book.getTitle()}" class="form-control"/>
                    <div class="help-block"></div>
                </div>
                <div class="form-group">
                    <label>Краткое описание</label>
                    <textarea name="description" class="form-control">${book.getDescription()}</textarea>
                </div>
                <div class="form-group">
                    <label>Автор</label>
                    <input name="author" value="${book.getAuthor()}" class="form-control"/>
                    <div class="help-block"></div>
                </div>
                <div class="row">
                    <div class="form-group col-md-5">
                        <label>ISBN</label>
                        <input name="isbn" value="${book.getIsbn()}" class="form-control"/>
                        <div class="help-block"></div>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Год</label>
                        <input name="printYear" value="${book.getPrintYear()}" class="form-control"/>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" name="submit" value="Отправить" class="btn btn-primary"/>
                    <a href="/" class="btn btn-default">Отмена</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
