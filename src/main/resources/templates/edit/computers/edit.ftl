<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование компьютера <#list computer as pc>${pc.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">DNS</label>
                <input type="text" name="DNSname" class="form-control" value="${pc.DNSname}"/>
            </div>
            <div class="form-check form-switch mb-3">
                <label class="form-label">В домене?</label>
                <input class="form-check-input" type="checkbox" name="domain" role="switch" <#if pc.domain??>checked</#if>>
            </div>
            <div class="form-check form-switch mb-3">
                <label class="form-label">Тонкий клиент?</label>
                <input class="form-check-input" type="checkbox" name="thinClient" role="switch" <#if pc.thinClient??>checked</#if>>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">IP</label>
                <input type="text" name="IP" class="form-control" required value="${pc.IP}"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Кабинет</label>
                <input type="text" name="office" class="form-control" required value="${pc.office}"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете расположение</label>
                <select name="location" class="form-control" class="form-select" required>
                    <#list location as loc>
                        <option value="${loc.name}" <#if pc.location.name == loc.name>selected = "selected"</#if>>${loc.name}</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </#list></form>
        <#if users?size != 0>
            <h6>Компьютер в использовании у следующих пользователей:</h6>
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