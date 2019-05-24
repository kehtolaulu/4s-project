<#include "_html.ftl">

<#macro navbar></#macro>

<#macro body>
<div class="login-wrapper">
    <div class="row form-login">
        <div class="card">
            <div class="card-body">
                <div class="col-sm-12">
                    <form method="POST">
                        <#if RequestParameters.error??>
                            <h4>wrong username or password</h4>
                        </#if>
                        <div class="form-group row">
                            <label for="emailForm"><b>Email</b></label>
                            <input id="emailForm" type="email" class="form-control" placeholder="Enter Email" name="email"
                                   required>
                        </div>
                        <div class="form-group row">
                            <label for="passwordForm"><b>Password</b></label>
                            <input id="passwordForm" type="password" class="form-control" placeholder="Enter Password" name="password"
                                   required>
                        </div>
                        <div class="form-check row">
                            <label>
                                <input type="checkbox" class="form-check-input" checked="checked" name="remember-me">
                                Remember
                                me
                            </label>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn btn-primary" name="submit">Login</button>
                            <div class="col-5"></div>
                            <button type="button" class="btn btn-link"><a href="/register">Sign up</a></button>
                        </div>
                    </form>

                    <p>Sign in with <a href="/login">Google</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<@page></@page>