<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Добавление телефона</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">IP</label>
                <input type="text" name="IP" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Модель</label>
                <input type="text" name="modelPhone" class="form-control"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Номер</label>
                <input type="text" name="number" class="form-control" value="1" required/>
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