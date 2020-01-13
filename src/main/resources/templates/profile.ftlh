<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="alert alert-success" role="alert">
        <h5>${username}</h5>
    </div>
    ${message!}
    <form method="post">
        <div class="form-group row mx-auto">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Enter password"/>
            </div>
        </div>
            <div class="form-group row mx-auto">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-5">
                    <input type="email" name="email" class="form-control" placeholder="example@example.com" value="${email!''}"/>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-outline-success" type="submit">Save</button>
    </form>
</@c.page>