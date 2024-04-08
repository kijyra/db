<#import '../../parts/common.ftl' as c>

<@c.tabPage>
    <div class="container mt-5 mb-5">
        <h2>Добавление расположения</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">Название расположения</label>
                <input type="text" name="name" class="form-control" required/>
                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>

</@c.tabPage>