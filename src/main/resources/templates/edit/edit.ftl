<#import '../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mx-4">
    ${authUser}
    <table>
        <tr>
            <th>
            <#list list as name, link>
                <a href="/edit/${link}/" class="btn btn-light" role="button"><h5>${name}</h5></a><br>
            </#list>
            </th>
            <th>
                <img src="/img/map.png"/>
            </th>
        </tr>
    </table>
    </div>
</@c.tabPage>