<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование пользователя <#list user as us>${us.id}</h2>
            <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">Имя</label>
                <input type="text" name="name" class="form-control" value="${us.name}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Фамилия</label>
                <input type="text" name="surname" class="form-control" value="${us.surname}" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Битрикс</label>
                <input type="text" name="bitrix" class="form-control" value="${us.bitrix}"/>
            </div>

            <div class="form-group mb-3">
                <label class="form-label">Выберете компьютер</label>
                <select name="computer" class="form-control" class="form-select" required>
                    <#list computers as pc>
                        <option value="${pc.IP}"
                                <#list us.computers as comp>
                                    <#if comp.id == pc.id>selected = "selected"</#if>
                                </#list>>${pc.id}. ${pc.IP} в ${pc.location.name} ${pc.office}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете отдел</label>
                <select name="department" class="form-control" class="form-select" required>
                    <#list departments as dp>
                        <option value="${dp.name}" <#if us.department.name == dp.name>selected = "selected"</#if>>${dp.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете телефон</label>
                <select name="phone" class="form-control" class="form-select">
                    <#list phones as ph>
                        <option value="${ph.IP}"
                            <#list us.phones as phone>
                                <#if phone.id == ph.id>selected = "selected"</#if>
                            </#list>>${ph.id}. ${ph.IP} ${ph.model} номер <i>${ph.number}</i></option>
                    </#list>
                </select>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете принтер</label>
                <select name="printer" class="form-control" class="form-select">
                    <#list printers as print>
                        <option value="${print.IP}"
                            <#list us.printers as pr>
                                <#if pr.id == print.id>selected = "selected"</#if>
                            </#list>>${print.id}. ${print.IP} ${print.printerModel.manufacturer} ${print.printerModel.name} в ${print.location.name}</i></option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
            </#list></form>
    </div>

</@c.tabPage>