<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование модели принтера <#list printerModel as pr>${pr.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">ID</label>
                <input type="text" disabled class="form-control" value="${pr.id}">
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Название модели</label>
                <input type="text" name="name" class="form-control" value="${pr.name}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Производитель:</label>
                <input type="text" name="manufacturer" class="form-control" value="${pr.manufacturer}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете картридж</label>
                <select name="cartridge" class="form-control" class="form-select" required>
                    <#list cartridges as cart>
                        <option value="${cart.name}" <#if cart.name == pr.cartridge.name>selected = "selected"</#if>>${cart.name}</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
        </#list>
        <#if printers?size != 0>
            <h6>Модель привязана к следующим принтерам:</h6>
            <ul class="list-group">
                <#list printers as pr>
                    <li class="list-group-item">
                        <a href="/edit/printers/${pr.id}/" target="_blank" class="btn btn-light" role="button">${pr.id}.${pr.printerModel.name} в ${pr.location.name}</a>
                    </li>
                </#list>
            </ul>
        </#if>
    </div>

</@c.tabPage>