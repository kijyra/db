<#import "../parts/common.ftl" as c>
<@c.page>
    
    <div class="container mt-5">
        <H1>Подключение к компьютерам одним кликом</H1>
        <p>
            На странице просмотра пользователей есть кнопки <i>Connect</i><br><br>
            <img src="../static/files/img/buttons.png" alt="Кнопки Connect"/><br><br>
            Они представляют из себя ссылки по типу
            <ul>
                <li> VNC - <a href="vnc://10.0.0.1">vnc://10.0.0.1</a> — для подключения к компьютеру через VNC с возможностью управления. Согласие не запрашивается, но на время подключения фон рабочего стола меняется на чёрный</li>
                <li>Shadow RDP - <a href="rpds://10.0.0.1">rpds://10.0.0.1</a> — для подключения к компьютеру через RDP без возможности управления. Согласие не запрашивается</li>
                <li>RDP - <a href="rpdc://10.0.0.1">rpdc://10.0.0.1</a> — для подключения к компьютеру через RDP с возможностью управления. Запрашивается согласие пользователя</li>


            </ul><br>
        Чтобы кнопки отрабатывали необходимо скачать <a href="../static/files/protocol/protocols.zip" download>ZIP-архив</a>, установить <a href="../static/files/protocol/tightvnc-2.8.81-gpl-setup-64bit.msi" download="TightVNC.msi">TightVNC</a> — VNC-клиент;<br>
        Выполнить <a href="../static/files/protocol/start.bat" download>start.bat</a> из архива для автоматического выполнения следующих шагов, либо же проделать их вручную;<br><br>
        Файлы из архива <a href="../static/files/protocol/rdp_control.cmd" download=>rdp_control.cmd</a>,
        <a href="../static/files/protocol/rdp_shadow.cmd" download=>rdp_shadow.cmd</a>,
        <a href="../static/files/protocol/vnc.cmd" download=>vnc.cmd</a> переместить в папку <b><i>%ProgramFiles%\TightVNC\web\</i></b>;<br>
        Запустить файлик <a href="../static/files/protocol/rdpc.reg" download>rdpc.reg</a> из архива для добавление в реестр информации о ссылках <i>rdpc://, rpds://, vnc://</i>
        </p>
    </div>


</@c.page>

