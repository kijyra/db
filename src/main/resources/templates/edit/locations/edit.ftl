<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование расположения <#list location as loc>${loc.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                    <label class="form-label">Название расположения</label>
                    <input type="text" name="name" class="form-control" required VALUE="${loc.name}"/>
                </#list>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
        <div class="row">
            <#if computers?size != 0>
                <div class="col-sm">
                    <h6>В этом расположении находятся компьютеры:</h6>
                    <ul class="list-group">
                        <#list computers as pc>
                            <li class="list-group-item">
                                <a href="/edit/computers/${pc.id}/" target="_blank" class="btn btn-light" role="button">${pc.IP} в ${pc.office}</a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </#if>
            <#if printers?size != 0>
                <div class="col-sm">
                    <h6>В этом расположении находятся принтеры:</h6>
                    <ul class="list-group">
                        <#list printers as pr>
                            <li class="list-group-item">
                                <a href="/edit/printers/${pr.id}/" target="_blank" class="btn btn-light" role="button">${pr.id}.${pr.printerModel.name} в ${pr.location.name}</a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </#if>
            <#if phones?size != 0>
                <div class="col-sm">
                    <h6>В этом расположении находятся телефоны:</h6>
                    <ul class="list-group">
                        <#list phones as ph>
                            <li class="list-group-item">
                                <a href="/edit/phones/${ph.id}/" target="_blank" class="btn btn-light" role="button">${ph.model} IP: ${ph.IP} номер ${ph.number}</a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </#if>
        </div>
    </div>

</@c.tabPage>