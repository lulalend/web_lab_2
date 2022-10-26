<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Лаба два-с</title>
    <link rel='icon' href='./img/favicon.ico'>

    <link rel='stylesheet' href='./styles/style.css' >
    <script type='text/javascript' src='./scripts/main.js' defer></script>
    <script type='text/javascript' src='./scripts/clear.js' defer></script>
    <script type='text/javascript' src='./scripts/update.js' defer></script>
    <script type='text/javascript' src='scripts/init.js' defer></script>
    <script type='text/javascript' src='scripts/draw_dots.js' defer></script>
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Pangolin&display=swap');
    </style>
</head>
<body onload='init()'>
<table id='main'>
    <tr> <!-- Row 1 -->
        <td class='horizontal-separator' colspan='7'></td>
    </tr>
    <tr> <!-- Row 2 -->
        <td class='edge-separator' rowspan='4'></td>

        <th id='about' rowspan='5'>
            <div class='about'>
                <h1>Добро пожаловать!)</h1><br>
                <h3>Функция странички:</h3>
                <p>
                    Вы можете ввести параметр R (которым задаётся масштаб) и
                    координаты точки, а затем узнать принадлежит ли введённая
                    Вами точка области на графике справа
                </p><br>
                <h3>Немного про автора сайта и вариант лабораторной работы: </h3>
                <p>Клюева Яна</p>
                <p>Группа: PЗ2101</p>
                <p>Вариант лабы: 1205</p><br>
                <nav>
                    <h3>Ниже можно ознакомиться со всеми лабами</h3>
                    <ul class='lab-work'> <!-- НЕОБХОДИМО СДЕЛАТЬ ССЫЛКИ НА ЛАБЫ -->
                        <li><a href='https://se.ifmo.ru/~s335093/web_lab_1/index.html' target='_blank'>Лаба рас</a></li>
                        <li>Лаба два-с</li>
                        <li>Лаба три-с</li>
                        <li>Лаба четыре-с</li>
                    </ul>
                </nav>
            </div>
        </th>

        <td class='vertical-separator' rowspan='4'></td>

        <td id='data'>
            <h4>
                Здесь можно ввести свои значения
                (каждый параметр необходимо задать)
            </h4><br>
            <form id='feedback-form' onsubmit='submitForm(); return false;' novalidate>
                <p>
                    <label for='R'>Выберите R:</label>
                    <select id='R' name='R' required>
                        <option value='' selected>Тык</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                    </select>
                    <br>
                    <span class='r-error'></span>
                    <br>

                    <label for='X'>
                        Введите X ( изменяется в интервале (-5 ; 3) ):
                    </label><br>
                    <input id='X' name='X' type='text' required
                           onkeyup='this.value = this.value.replace(/[^\d-+.,e]/g,"")' maxlength='14'>
                    <br>
                    <span class='x-error'></span>
                    <br>

                    <label for='Y'>Выберите Y:</label>
                    <select id='Y' name='Y' required>
                        <option value='' selected>Тык</option>
                        <option value='-4'>-4</option>
                        <option value='-3'>-3</option>
                        <option value='-2'>-2</option>
                        <option value='-1'>-1</option>
                        <option value='0'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                    </select>
                    <br>
                    <span class='y-error'></span>
                    <br>
                    <label>
                        Для того, чтобы проверить попадание точки в область,
                        нажмите на кнопку
                    </label><br>
                    <input id='check' name='check' type='submit' value='Проверить'>
                </p>
            </form>
        </td>

        <td class='vertical-separator'></td>

        <td id='area'>
            <h2>Заданная область:</h2><br>
            <section id='coordinate-system'>
                <svg id='svg' width='300' height='300'>
                    <!-- figures -->
                    <polygon points='150,150 250,150 250,100 150,100' stroke='#16161d'
                             stroke-width='2' fill='#D0C9D090'></polygon>

                    <path d='M 50,150 A 100 100 90 0 1 150,50 L 150,150 Z' stroke='#16161d'
                          stroke-width='2' fill='#D0C9D090'></path>

                    <polygon points='150,150 50,150 150,250' stroke='#16161d'
                             stroke-width='2' fill='#D0C9D090'></polygon>

                    <!-- coordinate axes -->
                    <line x1='0' x2='280' y1='150' y2='150' stroke='#16161d' stroke-width='4'></line>
                    <line x1='150' x2='150' y1='20' y2='300' stroke='#16161d' stroke-width='4'></line>

                    <polygon points='150,5 140,30 160,30' stroke='#16161d' stroke-width='1'></polygon>
                    <polygon points='295,150 270,140 270,160' stroke='#16161d' stroke-width='1'></polygon>

                    <text x='285' y='175'>X</text>
                    <text x='165' y='15'>Y</text>

                    <line x1='50' x2='50' y1='145' y2='155' stroke='#16161d' stroke-width='2'></line>
                    <line x1='100' x2='100' y1='145' y2='155' stroke='#16161d' stroke-width='2'></line>
                    <line x1='200' x2='200' y1='145' y2='155' stroke='#16161d' stroke-width='2'></line>
                    <line x1='250' x2='250' y1='145' y2='155' stroke='#16161d' stroke-width='2'></line>

                    <line x1='145' x2='155' y1='50' y2='50' stroke='#16161d' stroke-width='2'></line>
                    <line x1='145' x2='155' y1='100' y2='100' stroke='#16161d' stroke-width='2'></line>
                    <line x1='145' x2='155' y1='200' y2='200' stroke='#16161d' stroke-width='2'></line>
                    <line x1='145' x2='155' y1='250' y2='250' stroke='#16161d' stroke-width='2'></line>

                    <text x='40' y='175'>-R</text>
                    <text x='85' y='175'>-R/2</text>
                    <text x='190' y='175'>R/2</text>
                    <text x='245' y='175'>R</text>

                    <text x='160' y='55'>R</text>
                    <text x='160' y='97'>R/2</text>
                    <text x='160' y='205'>-R/2</text>
                    <text x='160' y='255'>-R</text>
            </section>
        </td>

        <td class='edge-separator' rowspan='5'></td>
    </tr>

    <tr> <!-- Row 3 -->
        <td class='horizontal-separator' colspan='3'></td>
    </tr>

    <tr> <!-- Row 4 -->
        <td colspan='3'>
            <hr>
            <input id='clear' type='button' value='Очистить таблицу' onclick='cleanTable()'>
            <table class='info'>
                <thead>
                <tr>
                    <th>R</th>
                    <th>X</th>
                    <th>Y</th>
                    <th>Результат</th>
                    <th>Время запроса</th>
                    <th>Время исполнения, мc</th>
                </tr>
                </thead>
            </table>
        </td>
    </tr>
    <tr> <!-- Row 5 -->
        <td colspan='5'>
            <div id='cookie-message'>

            </div>
        </td>
    </tr>
</table>
</body>
</html>