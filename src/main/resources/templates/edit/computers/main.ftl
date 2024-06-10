<#import '../../parts/common.ftl' as c>
    <#import '../../parts/svg.ftl' as svg>

<@c.tabPage>
    <h5 class="m-5">Компьютеры
    <a href="/edit/computers/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">DNS-имя</th>
        <th scope="col">Домен</th>
        <th scope="col">Тонкий клиент</th>
        <th scope="col">IP</th>
        <th scope="col">Кабинет</th>
        <th scope="col">Расположение</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <#list computers as pc>
    <tr>
        <th scope="col">${pc.id}</th>
        <th>${pc.DNSname}</th>
        <th><#if pc.domain??>
                <@svg.toggleOn/>
            <#else>
                <@svg.toggleOff/>
            </#if></th>
        <th><#if pc.thinClient??>
                <@svg.toggleOn/>
            <#else>
                <@svg.toggleOff/>
            </#if></th>
        <th>${pc.IP}</th>
        <th>${pc.office}</th>
        <th><a href="/edit/locations/${pc.location.id}/" class="btn btn-light" role="button"><b>${pc.location.id}.${pc.location.name}</b></a></th>
        <th><a href="/edit/computers/${pc.id}/getDNSname/" class="btn btn-light" role="button">DNS-имя</a></th>
        <th><a href="/edit/computers/${pc.id}/" class="btn btn-light" role="button">Edit</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>