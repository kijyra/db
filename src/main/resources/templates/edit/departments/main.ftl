<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Отделы
    <a href="/edit/departments/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя</th>
        <th scope="col"></th>
    </tr>
    <#list departments as dp>
    <tr>
        <th scope="col">${dp.id}</th>
        <th>${dp.name}</th>
        <th><a href="/edit/departments/${dp.id}/" class="btn btn-light" role="button">Edit</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>