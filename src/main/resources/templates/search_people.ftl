<#include "_html.ftl">

<#macro import>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" defer></script>
    <script src="/js/by_skill.js" defer></script>
</#macro>

<#macro body>
<div class="container-fluid" id="app">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="text" class="form-control" id="search" placeholder="Search..." v-model="filter.skills">
            <button class="btn btn-outline-success my-2 my-sm-1" type="submit" v-on:click="search()">Search</button>
            <person-item
                    v-for="person in people"
                    v-bind:key="person.id"
                    v-bind:person="person"
            ></person-item>
        </div>
    </div>
</div>
</#macro>
<@page></@page>