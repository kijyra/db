<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${title}</title>
    <link rel="shortcut icon" href="../files/img/ico.ico"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" />

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <style>
        .results tr[visible='false'],
        .no-result {
            display: none;
        }
        .results tr[visible='true'] {
            display: table-row;
        }
        .searchCount {
            padding: 8px;
            color: #ccc;
        }
        .fit-content {
            width: fit-content;
        }
    </style>
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
                <li class="nav-item">
                    <a class="nav-link" href="/files/" >Как подключиться</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/1c/" >Загрузки</a>
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

<div class="container">
    <div class="row">
        <div class="form-group pull-right">
            <input type="text" class="searchKey form-control" placeholder="Кого ищите?">
        </div>
        <span class="searchCount pull-right"></span>
        <table class="table table-striped table-hover results fit-content">
            <thead>
            <tr>
                <th>#</th>
                <th>Имя / Фамилия </th>
                <th>Телефон </th>
                <th>Кабинет </th>
            </tr>
            <tr class="warning no-result">
                <td colspan="4"><i class="fa fa-warning"></i> Совпадения не найдены</td>
            </tr>
            </thead>
            <tbody>
            <#list users as us>
                    <tr data-href="/view/${us.id}/">
                        <th scope="row">${us.id} <i class="bi bi-chevron-down"/></th>
                        <td>${us.name} ${us.surname}</td>
                        <td>
                            <#list us.phones as ph>
                                ${ph.number} <#if !ph_has_next??>, </#if>
                            </#list>
                        </td>
                        <td>
                            <#list us.computers as pc>
                                ${pc.office} <#if !pc_has_next??>, </#if>
                            </#list>
                        </td>
                    </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<script>
    function createExpr(arr) {
        var index = 0;
        var expr = [":containsiAND('" + arr[0] + "')"];
        for (var i = 1; i < arr.length; i++) {
            if (arr[i] === 'AND') {
                expr[index] += ":containsiAND('" + arr[i + 1] + "')";
                i++;
            } else if (arr[i] === 'OR') {
                index++;
                expr[index] = ":containsiOR('" + arr[i + 1] + "')";
                i++;
            }
        }
        return expr;
    }
    $(document).ready(function() {

        $('*[data-href]').on('click', function() {
            window.location = $(this).data("href");
        });

        $(".searchKey").keyup(function() {
            var searchTerm = $(".searchKey").val().replace(/["']/g, "");
            var arr = searchTerm.split(/(AND|OR)/);
            var exprs = createExpr(arr);
            var searchSplit = searchTerm.replace(/AND/g, "'):containsiAND('").replace(/OR/g, "'):containsiOR('");

            $.extend($.expr[':'], {
                'containsiAND': function(element, i, match, array) {
                    return (element.textContent || element.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
                }
            });

            $('.results tbody tr').attr('visible', 'false');
            for (var expr in exprs) {
                $(".results tbody tr" + exprs[expr]).each(function(e) {
                    $(this).attr('visible', 'true');
                });
            }

            var searchCount = $('.results tbody tr[visible="true"]').length;

            $('.searchCount').text('найдено ' + searchCount + ' человек');
            if (searchCount == '0') {
                $('.no-result').show();
            } else {
                $('.no-result').hide();
            }
            if ($('.searchKey').val().length == 0) {
                $('.searchCount').hide();
            } else {
                $('.searchCount').show();
            }
        });
    });
</script>
</body>

<footer class="footer">
    <div class="container">
        <span class="text-muted"></span>
    </div>
</footer>
</html>