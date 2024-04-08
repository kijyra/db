<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Телефоны<a href="/edit/phones/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">IP-адрес</th>
        <th scope="col">Модель</th>
        <th scope="col">Номер</th>
        <th scope="col">Расположение</th>
        <th scope="col"></th>
    </tr>
    <#list phones as ph>
    <tr>
        <th scope="col">${ph.id}</th>
        <th>${ph.IP}</th>
        <th>${ph.model}</th>
        <th>${ph.number}</th>
        <th><a href="/edit/locations/${ph.location.id}/" class="btn btn-light" role="button"><b>${ph.location.id}.${ph.location.name}</b></a></th>
        <th><a href="/edit/phones/${ph.id}/" class="btn btn-light" role="button">Edit</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>