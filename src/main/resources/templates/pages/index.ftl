<#include "../layouts/template.ftl">

<#macro content>
    <ul class="breadcrumb">
        <li><i class="fa fa-home"></i> Главная</li>
    </ul>

    <div class="panel panel-default">
        <div class="panel-body">
            <a href="/add" class="btn btn-primary pull-right">Добавить книгу</a>
            <h2>Книги</h2>

            <table class="table table-bordered table-stripped">
                <thead>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Автор</th>
                    <th>Описание</th>
                    <th>ISBN</th>
                    <th>Год печати</th>
                    <th>Прочитана</th>
                    <th></th>
                </thead>
                <tbody>
                    <#list books as book>
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.description}</td>
                        <td>${book.isbn}</td>
                        <td>${book.printYear}</td>
                        <td>
                            <#if book.isReadAlready()>
                                прочитана
                            <#else>
                                не прочитана
                            </#if>
                        </td>
                        <td align="right">
                            <a href="/edit?id=${book.id}" title="Редактировать"><i class="fa fa-edit"></i></a>
                            <a href="#" onclick="Book.remove(${book.id});return false" title="Удалить"><i class="fa fa-remove"></i></a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>


        </div>
    </div>
</#macro>