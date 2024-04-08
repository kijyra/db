<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Модели принтеров<a href="/edit/printerModels/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Производитель</th>
        <th scope="col">Название</th>
        <th scope="col">Картридж</th>
        <th scope="col"></th>
    </tr>
    <#list printerModels as pm>
    <tr>
        <th scope="col">${pm.id}</th>
        <th>${pm.manufacturer}</th>
        <th>${pm.name}</th>
        <th><a href="/edit/cartridges/${pm.cartridge.id}/" class="btn btn-light" role="button"><b>${pm.cartridge.id}.${pm.cartridge.name}</b></a></th>
        <th><a href="/edit/printerModels/${pm.id}/" class="btn btn-light" role="button">Edit</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>