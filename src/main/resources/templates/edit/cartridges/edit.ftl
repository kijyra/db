<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактировать картридж <#list cartridge as car>${car.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                    <label class="form-label">Название картриджа</label>
                    <input type="text" name="name" class="form-control" required value="${car.name}"/>
                </#list>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
        <#if models?size != 0>
            <h6>Картридж привязан к следующим моделям принтеров:</h6>
            <ul class="list-group">
                <#list models as model>
                    <li class="list-group-item">
                        <a href="/edit/printerModels/${model.id}/" target="_blank" class="btn btn-light" role="button">${model.manufacturer} ${model.name}</a>
                    </li>
                </#list>
            </ul>
        </#if>
    </div>

</@c.tabPage>