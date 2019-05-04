<#include "_html.ftl">

<#macro body>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <div class="card text-center">
            <div class="card-body">
                <a href="/editor" class="card-link">Write an article</a>
            </div>
        </div>
    <#--posts list-->
            <#list posts as p>
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/posts/${p.id}" class="card-link">${p.headline}</a></h5>
                        <p class="card-text">Author: ${p.author.name}</p>
                    </div>
                    <div class="card-footer text-muted">
                        ${p.publishedAt}
                    </div>
                </div>
            <#else>
                <b>There are no posts yet.</b>
            </#list>
    </div>
</div>
</#macro>
<@page></@page>