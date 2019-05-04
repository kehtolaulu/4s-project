<#include "_html.ftl">

<#macro body>
<div class="row">
    <div class="col-1"></div>
    <div class="col-2">
    </div>
    <div class="col-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center" id="headline">${post.headline}</h5>
                <p class="card-text text-center" id="author">Author: <a href=#>${post.author.name}</a></p>
                <p class="card-text" id="content">${post.content}</p>
            </div>
            <div class="card-footer text-muted" id="datetime">
                ${post.publishedAt}
            </div>
        </div>
        <div id="comments-list">
            <#if comments?has_content>
                <#list comments as comment>
                    <div id="comment-item-${comment.id}" class="card">
                        <div class="card-body">
                            <p class="card-text" id="commentAuthor"><a href=#>${comment.author.name}</a></p>
                            <p class="card-text" id="commentContent">${comment.content}</p>
                        </div>
                        <div class="card-footer text-muted">
                            ${comment.publishedAt}
                        </div>
                    </div>
                </#list>
            <#else>
                <b id="no-comments">There are no comments yet.</b>
            </#if>
        </div>
        <div class="form-group comment">
            <label for="commentForm">Comment:</label>
            <textarea class="form-control" rows="3" id="commentForm" maxlength="160"></textarea>
            <br>
            <button class="btn btn-outline-success my-2 my-sm-0" id="comment" type="submit" onclick="newComment()">Send
                comment
            </button>
        </div>
    </div>
</div>
</#macro>
<@page></@page>