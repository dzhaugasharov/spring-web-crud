<#include "../layouts/template.ftl">


<#macro content>
    <ul class="breadcrumb">
        <li><a href="/"><i class="fa fa-home"></i> Главная</a></li>
        <li><i class="fa fa-book"></i> Форма</li>
    </ul>
    <h2>Форма книга</h2>
    <form action="" method="post">
        <input type="hidden" name="id" value="" />
        <div class="form-group">
            <label>Название книги</label>
            <input name="title" value="${title}" class="form-control" />
        </div>
        <div class="form-group">
            <label>Краткое описание</label>
            <textarea name="description" class="form-control">${description}</textarea>
        </div>
        <div class="form-group">
            <label>Автор</label>
            <input name="author" value="${author}" class="form-control" />
        </div>
        <div class="row">
            <div class="form-group col-md-5">
                <label>ISBN</label>
                <input name="isbn" value="${isbn}" class="form-control" />
            </div>
            <div class="form-group col-md-4">
                <label>Год</label>
                <input name="printYear" value="${printYear}" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <input type="submit" name="submit" value="Отправить" class="btn btn-primary"/>
        </div>
    </form>
</#macro>