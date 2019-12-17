<#import "parts/common.ftlh" as c>
<@c.page>
    User editor
    <form action="/user" method="post">
        <input type="text" value="${user.username}" name="username">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="UserID">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">SAVE</button>
    </form>
</@c.page>