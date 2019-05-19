<#include "_html.ftl">

<#macro body>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2">
        </div>
        <div class="col-6">
            <div class="card">
                <div class="card-body">
                    <form method="post" action="/jobs/create" id="createJobForm">
                        <div class="form-group">
                            <input type="text" class="form-control" id="position" name="position"
                                   placeholder="Position">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="seniority" name="seniorityLevel"
                                   placeholder="Seniority level">
                        </div>
                        <div class="form-group">
                            <select class="custom-select" id="industry" name="industry">
                                <option selected value="" name="industry">Choose industry</option>
                                <#list industries as industry>
                                    <option value="${industry.id}">${industry.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <select class="custom-select" id="employment" name="employmentType">
                                <option selected value="1">Choose employment type</option>
                                <option value="2">Half-time</option>
                                <option value="3">Full-time</option>
                                <option value="4">Remote</option>
                            </select>
                        </div>
                        <textarea class="form-control" id="text" rows="10" name="jobFunctions"
                                  placeholder="Describe job functions here..."></textarea>
                    </form>
                </div>
                <button type="submit" class="btn btn-light" form="createJobForm">Save</button>
            </div>
        </div>
    </div>
</#macro>
<@page></@page>