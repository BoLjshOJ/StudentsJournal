<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row mx-auto">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="Enter username"/>
            </div>
        </div>
        <div class="form-group row mx-auto">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Enter password"/>
            </div>
        </div>
        <#if isRegisterForm>
        <div class="form-group row mx-auto">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-5">
                <input type="email" name="email" class="form-control" placeholder="example@example.com"/>
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-outline-success" type="submit">Log In</button>
        <#if !isRegisterForm>
        <a class="btn btn-outline-primary" href="/registration" role="button">Sign Up</a>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-outline-danger" type="submit">Log Out</button>
    </form>
</#macro>