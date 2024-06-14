<#import "../parts/common.ftl" as c>
<@c.page>
    
    <div class="container mt-5">
        <H1>Подключение к компьютерам одним кликом</H1>
        <p>
            На странице просмотра пользователей есть кнопки <i>Connect</i><br><br>
            <img src="../files/img/buttons.png" alt="Кнопки Connect"/><br><br>
            Они представляют из себя ссылки по типу
            <ul>
                <li><a href="rpdc://10.0.0.1">rpdc://10.0.0.1</a> — для подключения к компьютеру через RDP с возможностью управления. Запрашивается согласие пользователя</li>
                <li><a href="rpds://10.0.0.1">rpds://10.0.0.1</a> — для подключения к компьютеру через RDP без возможности управления. Согласие не запрашивается</li>
                <li><a href="vnc://10.0.0.1">vnc://10.0.0.1</a> — для подключения к компьютеру через VNC с возможностью управления. Согласие не запрашивается, но на время подключения фон рабочего стола меняется на чёрный</li>
            </ul><br>
        Чтобы кнопки отрабатывали необходимо скачать <a href="../files/protocols/protocols.zip" download>ZIP-архив</a>, установить <a href="../files/protocols/tightvnc-2.8.81-gpl-setup-64bit.msi" download="TightVNC.msi">TightVNC</a> — VNC-клиент;<br>
        Выполнить <a href="../files/protocols/start.bat" download>start.bat</a> из архива для автоматического выполнения следующих шагов, либо же проделать их вручную;<br><br>
        Файлы из архива <a href="../files/protocols/rdp_control.cmd" download=>rdp_control.cmd</a>,
        <a href="../files/protocols/rdp_shadow.cmd.cmd" download=>rdp_shadow.cmd</a>,
        <a href="../files/protocols/vnc.cmd" download=>vnc.cmd</a> переместить в папку <b><i>%ProgramFiles%\TightVNC\web\</i></b>;<br>
        Запустить файлик <a href="../files/protocols/rdpc.reg" download>rdpc.reg</a> из архива для добавление в реестр информации о ссылках <i>rdpc://, rpds://, vnc://</i>
        </p>
    </div>


</@c.page>

