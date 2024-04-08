<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Расположения<a href="/edit/locations/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя</th>
        <th scope="col"></th>
    </tr>
    <#list locations as loc>
    <tr>
        <th scope="col">${loc.id}</th>
        <th>${loc.name}</th>
        <th><a href="/edit/locations/${loc.id}/" class="btn btn-light" role="button">Edit</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>