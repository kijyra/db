<#import '../parts/common.ftl' as c>
<#import '../parts/svg.ftl' as svg>

<@c.page>
<div class="mx-4">
    <a href="https://dallari.bitrix24.ru/company/personal/user/${user.bitrix}/" target="_blank" class="btn btn-light" role="button"><h4>${user.surname} ${user.name}</h4></a>
    ${user.department.name}
    <table>
        <tr>
            <th>
            <#list user.computers as pc>
                <table>
                    <tr>
                        <th>Компьютер</th>
                        <th>
                            <@svg.wifi pcIsOnline/>
                        </th>
                    </tr>
                    <tr>
                        <th>Connect: </th>
                        <th>
                            <a href="vnc://${pc.IP}" class="btn btn-light" role="button"><b>VNC</b></a>
                            <#if pc.thinClient??>
                                <a href="rdps://${pc.IP}" class="btn btn-light" role="button"><b>WTRC</b></a>
                            </#if>
                        </th>
                    </tr>
                    <#if pc.domain??>
                        <tr>
                            <th><a href="rdps://${pc.IP}" class="btn btn-light" role="button"><b>Shadow RDP</b></a></th>
                            <th><a href="rdpc://${pc.IP}" class="btn btn-light" role="button"><b>RDP</b></a></th>
                        </tr>
                    </#if>
                    <tr>
                        <th>HostName: </th>
                        <th>${pc.DNSname}</th>
                    </tr>
                    <tr>
                        <th>IP: </th>
                        <th>${pc.IP}</th>
                    </tr>
                    <tr>
                        <th>В домене?</th>
                        <th>
                            <@svg.toogle pc.domain??/>
                        </th>
                    </tr>
                    <tr>
                        <th>Тонкий клиент?</th>
                        <th>
                            <@svg.toogle pc.thinClient??/>
                        </th>
                    </tr>
                    <tr>
                        <th>Расположение: </th>
                        <th> ${pc.location.name}. ${pc.office}</th>
                    </tr>
                    <tr>
                        <th><a href="/edit/computers/${pc.id}/" class="btn btn-light" role="button"><b>Редактировать компьютер</b></a></th>
                    </tr>
                </table>
            </#list></th>

            <th><#list user.phones as phone>
                <table>
                    <tr>
                        <th>Телефон</th>
                        <th>
                            <@svg.wifi phIsOnline/>
                        </th>
                    </tr>
                    <tr>
                        <th>Номер:</th>
                        <th>${phone.number}</th>
                    </tr>
                    <tr>
                        <th>IP: </th>
                        <th><a href="http://${phone.IP}" target="_blank">${phone.IP}</a> </th>
                    </tr>
                    <tr>
                        <th>Модель:</th>
                        <th>${phone.model}</th>
                    </tr>
                    <tr>
                        <th>Расположение:</th>
                        <th>${phone.location.name}</th>
                    </tr>
                    <tr>
                        <th><a href="/edit/phones/${phone.id}/" class="btn btn-light" role="button"><b>Редактировать телефон</b></a></th>
                    </tr>
                </table>
            </#list></th>

            <th><#list user.printers as printer>
                <table>
                    <tr>
                        <th>Принтер</th>
                        <th>
                            <@svg.wifi prIsOnline/>
                        </th>
                    </tr>
                    <tr>
                        <th>IP: </th>
                        <th><a href="http://${printer.IP}" target="_blank">${printer.IP}</a> </th>
                    </tr>
                    <tr>
                        <th>HostName:</th>
                        <th>${printer.DNSname}</th>
                    </tr>
                    <tr>
                        <th>Напечатано страниц: </th>
                        <th>${printer.printCount}</th>
                    </tr>
                    <tr>
                        <th>Отсканировано страниц: </th>
                        <th> ${printer.scanCount}</th>
                    </tr>
                    <tr>
                        <th>Модель: </th>
                        <th>${printer.printerModel.manufacturer} ${printer.printerModel.name}</th>
                    </tr>
                    <tr>
                        <th>Картридж: </th>
                        <th>${printer.printerModel.cartridge.name}</th>
                    </tr>
                    <tr>
                        <th><a href="/edit/printers/${printer.id}/update/" target="_blank" class="btn btn-light" role="button">Обновить по SNMP</a></th>
                    </tr>
                    <tr>
                        <th><a href="/edit/printers/${printer.id}/" class="btn btn-light" role="button"><b>Редактировать принтер</b></a></th>
                    </tr>
                </table>
            </#list></th>
        </tr>
    </table>
</div>
</@c.page>