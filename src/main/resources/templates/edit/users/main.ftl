<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <h5 class="m-5">Пользователи<a href="/edit/users/add/" class="btn btn-light" role="button">Добавить пользователя</a></h5>
    <table class="table container m-4" style="width: max-content">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Битрикс</th>
        <th scope="col">Отдел</th>
        <th scope="col">Компьютеры</th>
        <th scope="col">Телефоны</th>
        <th scope="col">Принтеры</th>
        <th scope="col"></th>
    </tr>
    <#list users as us>
    <tr>
        <th scope="col">${us.id}</th>
        <th><a href="https://dallari.bitrix24.ru/company/personal/user/${us.bitrix}/" class="btn btn-light"
               role="button"
               target="_blank">
                ${us.surname} ${us.name}
            </a></th>
        <th><a href="/edit/departments/${us.department.id}/" class="btn btn-light" role="button"><b>${us.department.name}</b></a></th>
        <th><table><#list us.computers as pc>
            <tr>
                <th><a href="/edit/computers/${pc.id}/" class="btn btn-light" role="button" target="_blank"><b>${pc.IP}</b></a></th>
            </tr>
        </#list></table></th>
        <th><table><#list us.phones as pc>
                    <tr>
                        <th><a href="/edit/phones/${pc.id}/" class="btn btn-light" role="button" target="_blank"><b>${pc.IP}</b></a></th>
                    </tr>
                </#list></table></th>
        <th><table><#list us.printers as pc>
                    <tr>
                        <th><a href="/edit/printers/${pc.id}/" class="btn btn-light" role="button" target="_blank"><b>${pc.printerModel.name} ${pc.IP}</b></a></th>
                    </tr>
                </#list></table></th>
        <th><a href="/edit/users/${us.id}/" class="btn btn-light" role="button">Редактировать</a> </th>
    </tr>
    </#list>
    </table>


</@c.tabPage>