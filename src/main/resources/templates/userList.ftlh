<#import "parts/common.ftlh" as c>
<@c.page>
    <table class="table table-hover my-5">
    <tbody>
    <#list users as user>
        <tr>
            <th scope="row" class="text-center">${user.username}</th>
            <td class="text-center"><#list user.roles as role>${role}<#sep>, </#list></td>
            <td class="text-center"><a href="/user/${user.id}" class="btn btn-warning stretched-link">edit</a></td>
        </tr>
    </#list>
    </tbody>
    </table>
</@c.page>