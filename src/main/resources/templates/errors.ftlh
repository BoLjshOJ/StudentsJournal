<#import "parts/common.ftlh" as c>
<@c.page>
    <table class="table table-striped my-3">
        <thead>
        <tr>
            <th scope="col" class="text-center">Задача</th>
            <th scope="col" class="text-center">Расположение</th>
            <th scope="col" class="text-center">Stacktrace</th>
            <th scope="col" class="text-center">LastCommit</th>
            <th scope="col" class="text-center">LastCommitDate</th>
        </tr>
        </thead>
        <tbody>
        <#list errors! as error>
            <tr>
                <th scope="row" class="text-center">${error.getTaskName()}</th>
                <td class="text-center">${error.getFileLocation()}</td>
                <td class="text-center">${error.getStackTrace()!}</td>
                <td class="text-center">${error.getLastCommitMessage()!}</td>
                <td class="text-center">${error.getCommitDate()!}</td>
            </tr>
        <#else>

        </#list>
        </tbody>
    </table>
</@c.page>