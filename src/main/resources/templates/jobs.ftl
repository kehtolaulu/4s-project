<#include "_html.ftl">

<#macro import>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" defer></script>
    <script src="/js/jobs.js" defer></script>
</#macro>

<#macro body>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="card text-center">
                <div class="card-body">
                    <@security.authorize access="hasRole('ROLE_HUMAN')">
                        <button type="button" class="btn btn-link" onclick="showAllJobs()">
                            All jobs
                        </button>
                        |
                        <button type="button" class="btn btn-link" onclick="showSavedJobs()">
                            Saved jobs
                        </button>
                        |
                        <button type="button" class="btn btn-link" onclick="showAppliedJobs()">
                            Applied jobs
                        </button>
                    </@security.authorize>
                    <@security.authorize access="hasRole('ROLE_COMPANY')">
                        <a href="/jobs/create" class="btn btn-link">Create a job</a>
                        |
                        <button type="button" class="btn btn-link" onclick="showApplications()">
                            Applications
                        </button>
                        |
                        <button type="button" class="btn btn-link" onclick="showOurJobs()">
                            Our jobs
                        </button>
                        |
                        <button type="button" class="btn btn-link" onclick="showAllJobs()">
                            All jobs
                        </button>
                    </@security.authorize>
                </div>

            </div>
        </div>
        <div class="col-1"></div>
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2">
        </div>

        <#--ALL JOBS-->

        <div class="col-6" id="jobs">
        <#if jobs?has_content>
            <#list jobs as job>
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title"><a href="/jobs/${job.id}" class="card-link">${job.position}</a></h5>
                    <p class="card-text">${job.company.name}</p>
                    <p class="card-text">${job.jobFunctions}
                    </p>
                </div>
                <div class="card-footer text-muted">
                    ${job.publishedAt}
                </div>
            </div>
            </#list>
        </#if>
        </div>

        <#--SAVED JOBS-->

        <div class="col-6" id="saved">
            <#if saved?has_content>
                <#list saved as job>
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title"><a href="/jobs/${job.id}" class="card-link">${job.position}</a></h5>
                    <p class="card-text">${job.company.name}</p>
                    <p class="card-text">${job.jobFunctions}
                    </p>
                </div>
                <div class="card-footer text-muted">
                    ${job.publishedAt}
                </div>
            </div>
                </#list>
            </#if>
        </div>

        <#--APPLIED JOBS-->

        <div class="col-6" id="applied">
            <#if applied?has_content>
                <#list applied as app>
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title"><a href="/jobs/${app.job.id}" class="card-link">${app.job.position}</a></h5>
                    <p class="card-text">${app.job.company.name}</p>
                    <p class="card-text">${app.job.jobFunctions}</p>
                    <#if app.isAccepted??>
                        <div class="alert alert-success" role="alert">
                            Accepted
                        </div>
                    </#if>
                    <#if app.isRejected??>
                    <div class="alert alert-danger" role="alert">
                        Rejected
                    </div>
                    </#if>
                </div>
                <div class="card-footer text-muted">
                    ${app.publishedAt}
                </div>
            </div>
                </#list>
            </#if>
        </div>

        <#--APPLICATIONS-->

        <div class="col-6" id="applications">
        <#if applications?has_content>
            <#list applications as application>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-10">
                            <p class="card-text">Applicant: <a href="/profile/${application.profile.id}">${application.profile.firstName}</a></p>
                            <p class="card-text">Job: <a href="/jobs/${application.job.id}">${application.job.position}</a></p>
                            <p class="card-text">Email: ${application.email}</p>
                            <p class="card-text">Phone number: ${application.number}</p>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-outline-success my-2 my-sm-1" type="submit">Accept</button>
                            <br>
                            <button class="btn btn-outline-danger my-2 my-sm-1" type="submit">Reject</button>
                        </div>
                    </div>
                </div>
                <div class="card-footer text-muted">
                    ${application.pubishedAt}
                </div>
            </div>
            </#list>
        </#if>
        </div>

        <#--OUR JOBS-->

        <div class="col-6" id="ours">
        <#if ours?has_content>
            <#list ours as job>
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title"><a href="/jobs/${job.id}" class="card-link">${job.position}</a></h5>
                    <p class="card-text">${job.company.name}</p>
                    <p class="card-text">${job.jobFunctions}
                    </p>
                </div>
                <div class="card-footer text-muted">
                    ${job.publishedAt}
                </div>
            </div>
            </#list>
        </#if>
        </div>
    </div>
</#macro>
<@page></@page>