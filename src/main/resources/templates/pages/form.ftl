<#include "../layouts/template.ftl">


<#macro content>
    <ul class="breadcrumb">
        <li><a href="/"><i class="fa fa-home"></i> Главная</a></li>
        <li><i class="fa fa-book"></i> Форма</li>
    </ul>

    <div class="panel panel-default">
       <div class="panel-body">
            <h2>Форма книга</h2>
            <form action="" method="post">
                <input type="hidden" name="id" value="<#if id??>${id}</#if>" />
                <div class="form-group<#if titleError??> has-error</#if>">
                    <label>Название книги</label>
                    <input name="title" value="${title}" class="form-control" />
                    <div class="help-block">
                        <#if titleError??>
                            ${titleError}
                        </#if>
                    </div>
                </div>
                <div class="form-group<#if descriptionError??> has-error</#if>">
                    <label>Краткое описание</label>
                    <textarea name="description" class="form-control"><#if description??>${description}</#if></textarea>
                </div>
                <div class="form-group<#if authorError??> has-error</#if>">
                    <label>Автор</label>
                    <input name="author" value="<#if author??>${author}</#if>" class="form-control" />
                    <div class="help-block">
                        <#if authorError??>
                            ${authorError}
                        </#if>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-5<#if isbnError??> has-error</#if>">
                        <label>ISBN</label>
                        <input name="isbn" value="<#if isbn??>${isbn}</#if>" class="form-control" />
                        <div class="help-block">
                        <#if isbnError??>
                            ${isbnError}
                        </#if>
                        </div>
                    </div>
                    <div class="form-group col-md-4<#if printYearError??> has-error</#if>">
                        <label>Год</label>
                        <input name="printYear" value="${printYear}" class="form-control" />
                        <div class="help-block">
                        <#if printYearError??>
                            ${printYearError}
                        </#if>
                        </div>
                    </div>
                </div>
                <#if success??>
                    <h4 class="text-success">Успешно сохранено!</h4>
                </#if>
                <div class="form-group">
                    <input type="submit" name="submit" value="Отправить" class="btn btn-primary"/>
                    <a href="/" class="btn btn-default">Отмена</a>
                </div>
            </form>
        </div>
    </div>
</#macro>