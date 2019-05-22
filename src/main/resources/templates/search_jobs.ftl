<#include "_html.ftl">

<#macro import>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" defer></script>
    <script src="/js/search.js" defer></script>
</#macro>

<#macro body>
<div class="container-fluid" id="app">


    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="form-inline">
                <input type="text" class="form-control" id="search" placeholder="Position" v-model="filter.position">
                <div class="form-group">
                    <select class="custom-select" id="industry" name="industry" v-model="filter.industry">
                        <option selected value="">Choose industry</option>
                        <#list industries as industry>
                            <option value="${industry.id}">${industry.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <select class="custom-select" id="employment" name="employment" v-model="filter.employment">
                        <option selected value="">Choose employment type</option>
                        <option value="Half-time">Half-time</option>
                        <option value="Full-Time">Full-time</option>
                        <option value="Remote">Remote</option>
                    </select>
                </div>
                <button class="btn btn-outline-success my-2 my-sm-1" type="submit" v-on:click="search()">Search</button>
            </div>
        </div>
    </div>
    <job-item
            v-for="job in jobs"
            v-bind:key="job.id"
            v-bind:job="job"
    ></job-item>
</div>
</#macro>
<@page></@page>