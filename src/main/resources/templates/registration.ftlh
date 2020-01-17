<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div class="alert alert-info" role="alert">
    <h3>Create new user</h3>
    </div>
<@l.login "/registration" true/>
    <#if message??>
        <div class="alert alert-danger my-3" role="alert">
          ${message!}
        </div>
    </#if>
</@c.page>