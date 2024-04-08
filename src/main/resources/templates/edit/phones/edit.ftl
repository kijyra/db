<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование телефона <#list phone as ph>${ph.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">IP</label>
                <input type="text" name="IP" class="form-control" value="${ph.IP}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Номер</label>
                <input type="text" name="number" class="form-control" value="${ph.number}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Модель</label>
                <input type="text" name="modelPhone" class="form-control" value="${ph.model}"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете расположение</label>
                <select name="location" class="form-control" class="form-select" required>
                    <#list location as loc>
                        <option value="${loc.name}">${loc.name}</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
        </#list>
        <#if users?size != 0>
        <h6>Телефон в использовании у следующих пользователей:</h6>
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