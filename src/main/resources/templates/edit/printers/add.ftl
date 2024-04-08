<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Добавление принтера</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">DNS-имя</label>
                <input type="text" name="dnsname" class="form-control"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">IP</label>
                <input type="text" name="IP" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Число отпечатанных страниц</label>
                <input type="text" name="countPrinter" class="form-control" value="1"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Число отсканированных страниц</label>
                <input type="text" name="countScanner" class="form-control" value="1"/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете расположение</label>
                <select name="location" class="form-control" class="form-select">
                    <option value=""></option>
                    <#list locations as loc>
                        <option value="${loc.name}">${loc.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете модель</label>
                <select name="printerModel" class="form-control" class="form-select">
                    <option value=""></option>
                    <#list printerModel as pModel>
                        <option value="${pModel.name}">${pModel.manufacturer} ${pModel.name} (картридж <i>${pModel.cartridge.name}</i>)</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>

</@c.tabPage>