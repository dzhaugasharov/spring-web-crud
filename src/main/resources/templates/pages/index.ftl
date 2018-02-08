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
                    <th style="width: 55px">ID</th>
                    <th>Название</th>
                    <th style="width: 120px">Автор</th>
                    <th>Описание</th>
                    <th style="width: 100px">ISBN</th>
                    <th style="width: 100px">Год печати</th>
                    <th style="width: 110px">Прочитана</th>
                    <th style="width: 65px"></th>
                </thead>
                <tbody>
                    <#list books as book>
                    <tr class="book-${book.id}">
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td><#if book.author??>${book.author}</#if></td>
                        <td><#if book.description??>${book.description}</#if></td>
                        <td><#if book.isbn??>${book.isbn}</#if></td>
                        <td>${book.printYear}</td>
                        <td class="status-col <#if book.isReadAlready()> text-success </#if>">
                            <#if book.isReadAlready()>
                                прочитана
                            <#else>
                                не прочитана
                            </#if>
                        </td>
                        <td align="right">
                            <#assign statusIcon = "fa-eye" >
                            <#if book.isReadAlready()>
                                <#assign statusIcon = "fa-eye-slash" >
                            </#if>
                            <a href="#" onclick="Book.readAlready(${book.id});return false" title="Изменить статус"><i class="fa status-btn-icn ${statusIcon}"></i></a>
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
                    <#assign ra="" ttl="" >
                    <#if readAlready??>
                        <#assign ra="&readAlready="+readAlready >
                    </#if>
                    <#if title??>
                        <#assign ttl="&title="+title >
                    </#if>
                    <li>
                        <#if (prevPage > 0)>
                        <a href="?page=${prevPage+ra+ttl}">«</a>
                        <#else>
                        <span>«</span>
                        </#if>
                    </li>
                    <#list 1..totalPages as i>
                        <#if (page == i)>
                        <li class="active"><span>${i}</span></li>
                        <#else>
                        <li><a href="?page=${i+ra+ttl}">${i}</a></li>
                        </#if>
                    </#list>
                    <li>
                        <#if (nextPage <= totalPages)>
                        <a href="?page=${nextPage+ra+ttl}">»</a>
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