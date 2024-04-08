<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Добавление компьютера</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">DNS</label>
                <input type="text" name="DNSname" class="form-control"/>
            </div>
            <div class="form-check form-switch mb-3">
                <label class="form-label">В домене?</label>
                <input class="form-check-input" type="checkbox" value="off" name="domain" id="flexSwitchCheckDefault">
            </div>
            <div class="form-check form-switch mb-3">
                <label class="form-label">Тонкий клиент?</label>
                <input class="form-check-input" type="checkbox" value="off" name="thinClient" id="flexSwitchCheckDefault">
            </div>
            <div class="form-group mb-3">
                <label class="form-label">IP</label>
                <input type="text" name="IP" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Кабинет</label>
                <input type="text" name="office" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете расположение</label>
                <select name="location" class="form-control" class="form-select" required>
                    <#list locations as loc>
                        <option value="${loc.name}">${loc.name}</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>

</@c.tabPage>