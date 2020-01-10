<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-4">
            <form method="get" action="/index" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Поиск по группе">
                <button class="btn btn-primary ml-2 my-2" type="submit">Поиск</button>
            </form>
        </div>
    </div>
    <a class="btn btn-info" data-toggle="collapse" href="#addStudent" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить студента
    </a>
    <div class="collapse" id="addStudent">
        <div class="card card-body my-2">
            <form method="post" class="form-inline">
                <input type="text" name="fullName" class="form-control mr-2 col-md-4" placeholder="Введите Фамилию и Имя" />
                <div class="input-group">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="group">Группа</label>
                    </div>
                    <select class="custom-select" id="group" name="group">
                        <#list groups as group>
                            <option value="${group}">${group}</option>
                        </#list>
                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-info ml-2 my-2" type="submit">Добавить</button>
            </form>
        </div>
    </div>

    <table class="table table-hover my-5">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="text-center">ФИО</th>
            <th scope="col" class="text-center">Группа</th>
            <th scope="col" class="text-center">Решено задач</th>
            <th scope="col" class="text-center">Всего задач</th>
            <th scope="col" class="text-center">Процент</th>
            <th scope="col" class="text-center">Ошибки</th>
            <th scope="col" class="text-center">Обновить результаты</th>
        </tr>
        </thead>
        <tbody>
        <#list students as student>
            <tr>
                <th scope="row" class="text-center">${student.getId()}</th>
                <td class="text-center">${student.getFullName()}</td>
                <td class="text-center">${student.getGroupName()}</td>
                <td class="text-center">${student.getSuccessRunsOfTest()}</td>
                <td class="text-center">${student.getTotalTest()}</td>
                <td><div class="progress-bar progress-bar-striped" role="progressbar" style="width: ${student.countSuccessPercent()}%" aria-valuenow="${student.countSuccessPercent()}" aria-valuemin="0" aria-valuemax="100">${student.countSuccessPercent()}%</div></td>
                <td class="text-center"><a href="/errors/${student.id}">${student.getFailedRunsOfTest()}</a></td>
                <td class="text-center">
                    <a href="/index/${student.getId()}" class="btn btn-outline-success">Update</a>
                </td>
            </tr>
        <#else>

        </#list>
        </tbody>
    </table>
</@c.page>