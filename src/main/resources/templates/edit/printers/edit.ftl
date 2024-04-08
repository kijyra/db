<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование принтера <#list printer as prin>${prin.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">DNS-имя <a href="/edit/printers/${prin.id}/hostnameUpdate/" class="btn btn-secondary" role="button">Обновить через snmp</a></label>
                <input type="text" name="dnsname" class="form-control" value="${prin.DNSname}"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">IP</label>
                <input type="text" name="IP" class="form-control" value="${prin.IP}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Отпечатано страниц <a href="/edit/printers/${prin.id}/printUpdate/" class="btn btn-secondary" role="button">Обновить через snmp</a></label>
                <input type="text" name="countPrinter" class="form-control" value="${prin.printCount}"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Отсканировано страниц <a href="/edit/printers/${prin.id}/scanUpdate/" class="btn btn-secondary" role="button">Обновить через snmp</a></label>
                <input type="text" name="countScanner" class="form-control" value="${prin.scanCount}"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете расположение</label>
                <select name="location" class="form-control" class="form-select" required>
                    <#list location as loc>
                        <option value="${loc.name}" <#if loc.name == prin.location.name>selected = "selected"</#if>>${loc.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете модель</label>
                <select name="printerModel" class="form-control" class="form-select">
                    <option ></option>
                    <#list printerModel as pModel>
                        <option value="${pModel.name} " <#if pModel.name == prin.printerModel.name>selected = "selected"</#if>>${pModel.manufacturer} ${pModel.name} (картридж <i>${pModel.cartridge.name}</i>)</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
        </#list>
        <#if users?size != 0>
            <h6>Принтер в использовании у следующих пользователей:</h6>
            <ul class="list-group">
                 <#list users as us>
                    <li class="list-group-item">
                        <a href="/edit/users/${us.id}/" target="_blank" class="btn btn-light" role="button">${us.surname} ${us.name}</a>
                    </li>
                 </#list>
            </ul>
        </#if>

    </div>

</@c.tabPage>