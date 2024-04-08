<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Принтеры
        <a href="/edit/printers/add/" class="btn btn-light" role="button">Add</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">DNS-имя</th>
        <th scope="col">IP</th>
        <th scope="col">Пробег принтера</th>
        <th scope="col">Пробег сканера</th>
        <th scope="col">Расположение</th>
        <th scope="col">Модель</th>
        <th scope="col">Картридж</th>
        <th scope="col"></th>
    </tr>
    <#list printers as pr>
    <tr>
        <th scope="col">${pr.id}</th>
        <th>${pr.DNSname}</th>
        <th>${pr.IP}</th>
        <th>${pr.printCount}</th>
        <th>${pr.scanCount}</th>
        <th><a href="/edit/locations/${pr.location.id}/" class="btn btn-light" role="button"><b>${pr.location.id}.${pr.location.name}</b></a></th>
        <th><a href="/edit/printerModels/${pr.printerModel.id}/" class="btn btn-light" role="button"><b>${pr.printerModel.id}.${pr.printerModel.name}</b></a></th>
        <th><a href="/edit/cartridges/${pr.printerModel.cartridge.id}/" class="btn btn-light" role="button"><b>${pr.printerModel.cartridge.id}.${pr.printerModel.cartridge.name}</b></a></th>
        <th><a href="/edit/printers/${pr.id}/" class="btn btn-light" role="button">Edit</a> </th>
        <th><a href="/edit/printers/${pr.id}/update/" class="btn btn-light" role="button">Обновить по snmp</a></th>
    </tr>
    </#list>
    </table>


</@c.tabPage>