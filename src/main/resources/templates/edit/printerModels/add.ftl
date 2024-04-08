<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Добавление модели принтера</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">Название модели</label>
                <input type="text" name="name" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Производитель:</label>
                <input type="text" name="manufacturer" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Выберете картридж</label>
                <select name="cartridge" class="form-control" class="form-select" required>
                    <#list cartridges as cart>
                        <option value="${cart.name}">${cart.name}</option>
                    </#list>
                </select>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>

</@c.tabPage>