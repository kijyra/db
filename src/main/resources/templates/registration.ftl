<#import 'parts/common.ftl' as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h2>Регистрация аккаунта</h2>
        <form method="post">
            <div class="form-group mb-3">
                <label class="form-label">Логин</label>
                <input type="text" name="username" class="form-control" required/>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Пароль</label>
                <input type="password" name="password" class="form-control" required/>

                <br><button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>

</@c.page>