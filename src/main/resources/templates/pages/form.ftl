<#include "../layouts/template.ftl">


<#macro content>
    <h2>Форма книга</h2>
    <form action="" method="post">
        <div class="form-group">
            <label>Название книги</label>
            <input name="book" class="form-control" />
        </div>
        <div class="form-group">
            <label>Автор</label>
            <input name="book" class="form-control" />
        </div>
        <div class="form-group">
            <label>Год</label>
            <input name="book" class="form-control" />
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary"/>
        </div>
    </form>


    <p th:text="'Hello, ' + ${name} + '!'" />


</#macro>