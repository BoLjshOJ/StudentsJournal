<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <#if isAdmin>
        <a class="btn btn-info" data-toggle="collapse" href="#addTask" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить задачу
        </a>
        <div class="collapse" id="addTask">
            <div class="card card-body my-2">
                <form method="post" class="form-inline">
                    <input type="text" name="taskName" class="form-control mr-2 col-md-4" placeholder="Задача" />
                    <input type="text" name="fileLocation" class="form-control mr-2 col-md-4" placeholder="Расположение" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-info ml-2 my-2" type="submit">Добавить</button>
                </form>
            </div>
        </div>
    </#if>

    <table class="table table-borderless my-3">
        <thead>
        <tr>
            <th scope="col" class="text-center">Задача</th>
            <th scope="col" class="text-center">Расположение</th>
        </tr>
        </thead>
        <tbody>
        <#list tasks as task>
            <tr>
                <th scope="row" class="text-center">${task.getTaskName()}</th>
                <td class="text-center">${task.getFileLocation()}</td>
            </tr>
        <#else>
            <h3>No tasks</h3>
        </#list>
        </tbody>
    </table>
</@c.page>