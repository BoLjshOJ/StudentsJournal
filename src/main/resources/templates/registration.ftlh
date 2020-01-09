<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div class="alert alert-info" role="alert">
    <h3>Create new user</h3>
    </div>
<@l.login "/registration" />
    ${message!}
</@c.page>