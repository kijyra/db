<#import "parts/common.ftl" as c>
<@c.page>
    
    <div class="container mt-5">
        <H3>${currentUsername}</H3>
        <p>
            <#if currentRole == "ROLE_USER">
                Ваша роль <b><i>Пользователь</i></b>. Вам доступен только просмотр.
            <#elseif currentRole == "ROLE_ADMIN">
                Ваша роль <b><i>Администратор</i></b>. Вам доступны все функции.
            <#elseif currentRole == "ROLE_ANONYMOUS">
                Вы не выполнили вход. Вам доступны только <a href="/">главная страница</a> и эта.
            <#else>
                Кто ты?
            </#if>
        </p>
    </div>

</@c.page>

