<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Картриджи
    <a href="/edit/cartridges/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя</th>
        <th scope="col"></th>
    </tr>
    <#list cartridges as cart>
    <tr>
        <th scope="col">${cart.id}</th>
        <th>${cart.name}</th>
        <th><a href="/edit/cartridges/${cart.id}/" class="btn btn-light" role="button">Edit</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>