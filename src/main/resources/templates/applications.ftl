<#include "_html.ftl">

<#macro body>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2"></div>
        <div class="col-6" id="applications">
        <#if applications?has_content>
            <#list applications as application>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-10">
                            <p class="card-text">Applicant: <a
                                    href="/profile/${application.profile.id}">${application.profile.firstName}</a></p>
                            <p class="card-text">Job: <a
                                    href="/jobs/${application.job.id}">${application.job.position}</a></p>
                            <p class="card-text">Email: ${application.email}</p>
                            <p class="card-text">Phone number: ${application.number}</p>
                        </div>
                        <div class="col-2">
                            <form action="/applications/${application.id}/accept" method="post">
                                <button class="btn btn-outline-success my-2 my-sm-1" type="submit">Accept</button>
                            </form>
                            <br>
                            <form action="/applications/${application.id}/reject" method="post">
                                <button class="btn btn-outline-danger my-2 my-sm-1" type="submit">Reject</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="card-footer text-muted">
                    ${application.publishedAt}
                </div>
            </div>
            </#list>
        </#if>
        </div>
    </div>
</#macro>
<@page></@page>