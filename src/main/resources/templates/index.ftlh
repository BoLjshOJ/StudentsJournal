<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-4">
            <form method="get" action="/index" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Поиск по группе">
                <button class="btn btn-primary ml-2 my-2" type="submit">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-info" data-toggle="collapse" href="#addStudent" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add student
    </a>
    <div class="collapse" id="addStudent">
        <div class="card card-body my-2">
            <form method="post" class="form-inline">
                <input type="text" name="fullName" class="form-control mr-2 col-md-4" placeholder="Введите Фамилию и Имя" />
                <input list="group" name="group" class="form-control mr-2 my-2 col-md-3" placeholder="Группа">
                <datalist id="group">
                    <#list groups as group>
                        <option value="${group}"></option>
                    </#list>
                </datalist>
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
        </tr>
        </thead>
        <tbody>
        <#list students as student>
            <tr>
                <th scope="row" class="text-center">${student.id}</th>
                <td class="text-center">${student.fullName}</td>
                <td class="text-center">${student.groupName}</td>
                <td class="text-center">${student.successRunsOfTest}</td>
                <td class="text-center">${student.totalTest}</td>
                <td><div class="progress-bar progress-bar-striped" role="progressbar" style="width: ${student.countSuccessPercent()}%" aria-valuenow="${student.countSuccessPercent()}" aria-valuemin="0" aria-valuemax="100">${student.countSuccessPercent()}%</div></td>
            </tr>
        <#else>
            No students
        </#list>
        </tbody>
    </table>
</@c.page>