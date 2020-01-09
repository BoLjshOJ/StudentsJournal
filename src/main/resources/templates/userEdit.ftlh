<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="alert alert-info" role="alert">
        <b>User editor</b>
    </div>
    <div class="card card-body my-2">
        <form action="/user" method="post" class="form-group">
            <input type="text" value="${user.username}" name="username" class="form-control mr-2 col-md-4">
            <#list roles as role>
                <div class="form-check my-2">
                    <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="UserID">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button class="btn btn-outline-success" type="submit">SAVE</button>
        </form>
    </div>
</@c.page>