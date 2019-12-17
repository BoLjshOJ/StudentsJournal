<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">Users List</a></span>
    </div>
    <div>
        <form method="post">
            <input type="text" name="fullName" placeholder="Введите Фамилию и Имя" />
            <input list="group" name="group">
            <datalist id="group">
                <#list groups as group>
                    <option value="${group}"></option>
                </#list>
            </datalist>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список студентов:</div>
    <form method="get" action="/index">
        <input type="text" name="filter" value="${filter!}" placeholder="Поиск по группе">
        <button type="submit">FIND</button>
    </form>
<#list students as student>
    <div>
        <b>${student.id}</b>
        <span>${student.fullName}</span>
        <b>${student.groupName}</b>
        <span>${student.successRunsOfTest}</span>
        <strong>${student.createdBy.username}</strong>
    </div>
<#else>
No students
</#list>
</@c.page>