<#include "../layouts/template.ftl">

<#macro content>
    <ul class="breadcrumb">
        <li><i class="fa fa-home"></i> Главная</li>
    </ul>

    <div class="panel panel-default">
        <div class="panel-body">
            <a href="/add" class="btn btn-primary pull-right">Добавить книгу</a>
            <h2>Книги</h2>
            <label><i class="fa fa-filter"></i> Фильтр</label>
            <form action="" method="get">
                <div class="row">
                    <div class="col-md-4 form-group">
                        <input type="text" name="title" value="${title}" class="form-control" placeholder="По названию" />
                    </div>
                    <div class="col-md-2 form-group">
                        <select name="readAlready" class="form-control">
                            <option value="">все</option>
                            <option value="0" ${one}>не прочтенные</option>
                            <option value="1" ${two}>прочитанные</option>
                        </select>
                    </div>
                    <div class="col-md-4 form-group">
                        <input type="submit" value="Применить" class="btn btn-primary" />
                    </div>
                </div>

            </form>
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

            <div class="text-center">
                <#if (totalPages > 1)>
                <ul class="pagination pagination-sm">
                    <#assign prevPage=page-1 >
                    <#assign nextPage=page+1 >
                    <li>
                        <#if (prevPage > 0)>
                        <a href="?page=${prevPage}">«</a>
                        <#else>
                        <span>«</span>
                        </#if>
                    </li>
                    <#list 1..totalPages as i>
                        <#if (page == i)>
                        <li class="active"><span>${i}</span></li>
                        <#else>
                        <li><a href="?page=${i}">${i}</a></li>
                        </#if>
                    </#list>
                    <li>
                        <#if (nextPage <= totalPages)>
                        <a href="?page=${nextPage}">»</a>
                        <#else>
                        <span>»</span>
                        </#if>
                    </li>
                </ul>
                </#if>
            </div>
        </div>
    </div>
</#macro>