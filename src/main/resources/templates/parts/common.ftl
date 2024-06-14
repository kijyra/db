<#macro page>
    <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="shortcut icon" href="../files/img/ico.ico"/>
        <title>${title}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    </head>
    <body>

    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <div class="container-fluid">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/"><h4>Главная</h4></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/view/" >Просмотр пользователей</a>
                    </li>
                    <li class="nav-item">
                        <#if currentRole == "ROLE_ADMIN">
                            <a class="nav-link" href="/edit/">Редактирование</a>
                        </#if>
                    </li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/user">${currentUsername}</a>
                        <#if currentUsername == "anonymous">
                            <a class="nav-link" href="/login">Войти</a>
                        <#else>
                            <a class="nav-link" href="/logout">Выйти</a>
                        </#if>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

        <#nested>

    </body>

    <footer class="footer">
        <div class="container">
            <span class="text-muted"></span>


        </div>
    </footer>
</html>
</#macro>

<#macro tabPage>
    <!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="shortcut icon" href="../files/img/ico.ico"/>
        <title>${title}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    </head>
    <body>

    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <div class="container-fluid">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/"><h4>Главная</h4></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/view/" >Просмотр пользователей</a>
                    </li>
                    <li class="nav-item">
                        <#if currentRole == "ROLE_ADMIN">
                            <a class="nav-link" href="/edit/">Редактирование</a>
                        </#if>
                    </li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/user">${currentUsername}</a>
                        <#if currentUsername == "anonymous">
                            <a class="nav-link" href="/login">Войти</a>
                        <#else>
                            <a class="nav-link" href="/logout">Выйти</a>
                        </#if>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div>
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/users/")>active</#if>" aria-current="page" href="/edit/users/">Пользователи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/computers/")>active</#if>" href="/edit/computers/">Компьютеры</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/phones/")>active</#if>" href="/edit/phones/">Телефоны</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/printers/")>active</#if>" href="/edit/printers/">Принтеры</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/printerModels/")>active</#if>" href="/edit/printerModels/">Модели принтеров</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/cartridges/")>active</#if>" href="/edit/cartridges/">Картриджи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/locations/")>active</#if>" href="/edit/locations/">Расположения</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/departments/")>active</#if>" href="/edit/departments/">Отделы</a>
            </li>
        </ul>
    </div>
    <#nested>

    </body>

    <footer class="footer">
        <div class="container">
            <span class="text-muted"></span>
        </div>
    </footer>
    </html>
</#macro>
