<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Редактирование отдела <#list department as dep>${dep.id}</h2>
        <form method="post">
            <div class="form-group mb-3">
                    <label class="form-label">Название отдела</label>
                    <input type="text" name="name" class="form-control" required VALUE="${dep.name}"/>
                </#list>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>

</@c.tabPage>